package com.ecit.controller;

import com.ecit.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Reference(version = "1.0.0",
            mock = "com.ecit.service.mock.DemoServiceMock",
            timeout = 1000,
            retries = 0)
    private DemoService demoService;

    @GetMapping("/index")
    public String index() {
        RpcContext.getContext().setAttachment("span_id", "123456");
        return demoService.sayHello("mercyblitz");
    }
}
