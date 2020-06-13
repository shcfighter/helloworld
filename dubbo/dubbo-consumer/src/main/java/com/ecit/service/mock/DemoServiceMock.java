package com.ecit.service.mock;

import com.ecit.DemoService;

public class DemoServiceMock implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("mock");
        System.out.println("====================================================================");
        return "null";
    }
}
