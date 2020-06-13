package com.ecit.controller;

import com.ecit.FirstTccAction;
import com.ecit.SecondTccAction;
import com.ecit.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @GetMapping("/transfer")
    public String transfer(){
        businessService.transfer();
        return "success";
    }

    @GetMapping("/transfer2")
    public String transfer2(){
        businessService.transfer2();
        return "success";
    }
}
