package com.ecit.task;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestTask {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private RepositoryService repositoryService;

    public void run(){
        runtimeService.startProcessInstanceByKey("test");
    }
}
