package com.ecit.service.impl;

import com.ecit.aspectj.MyMonitor;
import com.ecit.service.ISomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("someService")
public class SomeServiceImpl implements ISomeService {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void hello(String someParam) {
        logger.info("---SomeService: hello invoked, param: {}---", someParam);
        System.out.println(this.hashCode());
        System.out.println(this.getClass().getName());
        //test();
    }

    @MyMonitor
    @Override
    public void test() {
        logger.info("---SomeService: test invoked---");
    }
}