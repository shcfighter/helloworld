package com.ecit.controller;

import com.ecit.domain.Employee;
import com.ecit.domain.User;
import com.ecit.mapper.EmployeeMapper;
import com.ecit.mapper.UserMapper;
import com.ecit.service.IEmployeeService;
import com.ecit.service.IUserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IUserService userService;


    @GetMapping("/query")
    public List<User> queryUser(){
        User user = new User();
        user.setName("zhangsan");
        return userMapper.queryUser(user);
    }

    @Transactional
    @GetMapping("/save")
    public String saveUser(){
        User user = new User();
        //user.setName(new Random().nextInt(999999999) + "");
        user.setName("wanglaowu");
        user.setIdcard(new Random().nextInt(999999999) + "");
        user.setAge(new Random().nextInt(99));
        user.setSex(new Random().nextInt(2) == 0 ? new Byte("0"): new Byte("1"));
        userService.save(user);

        Employee employee = new Employee();
        employee.setName("wanglaowu");
        employeeService.save(employee);
        return "success";
    }
}
