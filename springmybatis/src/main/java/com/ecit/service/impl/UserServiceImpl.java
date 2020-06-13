package com.ecit.service.impl;

import com.ecit.domain.User;
import com.ecit.mapper.UserMapper;
import com.ecit.service.IUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;


    //@Transactional
    @Override
    public void save(User user) {
        save2(user);
    }


    @Override
    public void save2(User user) {
        userMapper.insert(user);
        //int i = 1/0;
    }
}
