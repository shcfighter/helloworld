package com.ecit.service.impl;

import com.ecit.service.IUserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Override
    public String getUser(String name) {
        return "hello " + name;
    }
}
