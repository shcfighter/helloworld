package com.ecit.service.impl;

import com.ecit.aspectj.MyInfoAnnotation;
import com.ecit.aspectj.MyInfoAnnotation2;
import com.ecit.service.IUserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    //@MyInfoAnnotation2
    @MyInfoAnnotation
    @Override
    public String getUser(String name) {
        System.out.println("UserServiceImpl hello " + name);
        return "hello " + name;
    }

    @MyInfoAnnotation
    @Override
    public String getUser2(String name) {
        System.out.println("UserServiceImpl hello " + name);
        return "hello " + name;
    }
}
