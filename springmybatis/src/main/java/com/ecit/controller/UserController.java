package com.ecit.controller;

import com.ecit.domain.Employee;
import com.ecit.domain.User;
import com.ecit.service.IEmployeeService;
import com.ecit.service.IUserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
public class UserController {

    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IUserService userService;


    @GetMapping("/query")
    public List<User> queryUser(){
        long start = System.currentTimeMillis();
        User user = new User();
        user.setName("zhangsan");
        List<User> list = userService.queryUser(user);
        System.out.println("查询耗时：" + (System.currentTimeMillis() - start));
        return list;
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable int id){
        long start = System.currentTimeMillis();
        userService.delete(id);
        System.out.println("查询耗时：" + (System.currentTimeMillis() - start));
        return "success";
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable int id){
        long start = System.currentTimeMillis();
        User user = userService.get(id);
        System.out.println("查询耗时：" + (System.currentTimeMillis() - start));
        return user;
    }

    @Transactional
    @GetMapping("/save")
    public String saveUser(){
        User user = new User();
        //user.setId(new Random().nextInt(999999999));
        //user.setName(new Random().nextInt(999999999) + "");
        user.setName("wanglaowu");
        user.setIdcard(new Random().nextInt(999999999) + "");
        user.setAge(new Random().nextInt(99));
        user.setSex(new Random().nextInt(2) == 0 ? new Byte("0"): new Byte("1"));
        User dbUser = userService.save(user);

        /*Employee employee = new Employee();
        employee.setName("wanglaowu");
        employeeService.save(employee);*/
        return dbUser.getId().toString();
    }
}
