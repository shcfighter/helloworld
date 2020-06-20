package com.ecit.service.impl;

import com.ecit.domain.User;
import com.ecit.mapper.UserMapper;
import com.ecit.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;


    //@Transactional
    @Override
    @CachePut(value = "userCache", key = "#user.id")
    public User save(User user) {
        return save2(user);
    }


    @Override
    public User save2(User user) {
        userMapper.insert(user);
        System.out.println(user.getId());
        //int i = 1/0;
        return user;
    }

    @Override
    @Cacheable(value = "userCache", key = "#user.name")
    public List<User> queryUser(User user) {
        return userMapper.queryUser(user);
    }

    @CacheEvict(value = "userCache", key = "#id")
    @Override
    public int delete(int id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Cacheable(value = "userCache", key = "#user.id")
    public User get(User user) {
        return userMapper.selectByPrimaryKey(user.getId());
    }
}
