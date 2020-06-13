package com.ecit.controller;

import com.ecit.aspectj.MyInfoAnnotation;
import com.ecit.service.IJdbcService;
import com.ecit.service.ISomeService;
import com.ecit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ISomeService someService;
    @Resource
    private IUserService userService;
    @Resource
    private IJdbcService jdbcService;

    @MyInfoAnnotation(value = "FCC")
    @RequestMapping("/user")
    public String getUser(){
        System.out.println("Hello World!");
        someService.hello("zhansan");
        return "hello";
    }

    @RequestMapping("/index")
    public String getIndex(){
        System.out.println("Hello World!");
        userService.getUser("zhansan");
        return "hello";
    }

    @RequestMapping("/save")
    public String save(){
        System.out.println("Hello World!");
        jdbcService.save();
        return "hello";
    }

    @RequestMapping("/saveOrder")
    public String saveOrder(){
        System.out.println("Hello World!");
        jdbcService.save2();
        return "hello";
    }

    @GetMapping("/query")
    public List query(){
        return jdbcService.query();
    }
}
