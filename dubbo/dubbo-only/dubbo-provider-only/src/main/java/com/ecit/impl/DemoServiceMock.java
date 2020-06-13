package com.ecit.impl;

import com.ecit.DemoService;

public class DemoServiceMock implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("mock");
        return "null";
    }
}
