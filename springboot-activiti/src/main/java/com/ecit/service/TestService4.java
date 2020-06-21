package com.ecit.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TestService4 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("==================================================================================");
        System.out.println(Thread.currentThread().getName() + " test4 处理");
        System.out.println("==================================================================================");
        //throw new NullPointerException("处理异常");
    }
}
