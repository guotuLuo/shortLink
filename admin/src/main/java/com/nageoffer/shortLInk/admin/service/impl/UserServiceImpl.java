package com.nageoffer.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shortlink.admin.common.convention.exception.ClientException;
import com.nageoffer.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.nageoffer.shortlink.admin.dao.entity.UserDO;
import com.nageoffer.shortlink.admin.dao.mapper.UserMapper;
import com.nageoffer.shortlink.admin.dto.req.UserLoginReqDTO;
import com.nageoffer.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.nageoffer.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserRespDTO;
import com.nageoffer.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.nageoffer.shortlink.admin.common.constants.RedisCacheRegisterLock.LOCK_USER_REGISTER_KEY;
import static com.nageoffer.shortlink.admin.common.enums.UserErrorCodeEnum.*;

/**
 * 用户接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public UserRespDTO getByUserName(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers
                .lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO userRespDTO = new UserRespDTO();
        BeanUtils.copyProperties(userDO, userRespDTO);
        return userRespDTO;
    }
    @Override
    public Boolean isAvailableUserName(String username) {
        boolean contains = userRegisterCachePenetrationBloomFilter.contains(username);
        return !contains;
    }

    @Override
    public void register(UserRegisterReqDTO requestDTO) {
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestDTO.getUsername());
        lock.lock();
        try{
            if(lock.tryLock()){
                if(!isAvailableUserName(requestDTO.getUsername())){
                    throw new ClientException(USER_NAME_EXIST);
                }
                UserDO bean = BeanUtil.toBean(requestDTO, UserDO.class);
                int insert = baseMapper.insert(bean);
                if(insert < 1){
                    throw new ClientException(USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestDTO.getUsername());
            }else{
                throw new ClientException(USER_EXIST);
            }
        }finally {
            lock.unlock();;
        }
    }

    @Override
    public void update(UserUpdateReqDTO userUpdateReqDTO) {
        // TODO 验证当前用户名是否为登录用户
        LambdaQueryWrapper<UserDO> updateWrapper = Wrappers.lambdaQuery(UserDO.class)
                                                .eq(UserDO::getUsername, userUpdateReqDTO.getUsername());
        baseMapper.update(BeanUtil.toBean(userUpdateReqDTO, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO) {
        // 查看数据库中是否已注册
        LambdaQueryWrapper<UserDO> eq = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userLoginReqDTO.getUsername())
                .eq(UserDO::getPassword, userLoginReqDTO.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(eq);
        if(userDO == null){
            throw new ClientException("用户名或密码错误或未注册");
        }
        Boolean hasLogin = stringRedisTemplate.hasKey(userLoginReqDTO.getUsername());
        if(Boolean.TRUE.equals(hasLogin)){
            throw new ClientException("用户已登录");
        }
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + userLoginReqDTO.getUsername(), uuid, JSON.toJSONString(userDO));
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String uuid) {
        return stringRedisTemplate.opsForHash().get("login_" + username, uuid) != null;
    }

    @Override
    public void logout(String username, String uuid) {
        if(checkLogin(username, uuid)){
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException("用户未登录");
    }
}
