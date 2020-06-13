package com.ecit.service.impl;

import com.ecit.UserService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUser() {
        return "zhangsan";
    }

    @Override
    public boolean saveUser() {
        return false;
    }
}
