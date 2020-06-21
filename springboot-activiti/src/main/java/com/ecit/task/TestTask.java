package com.ecit.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestTask {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private ProcessEngine processEngine;

    public void run(){
        Map<String, Object> variables = new HashMap<>();
        variables.put("day", 2);
        runtimeService.startProcessInstanceByKey("test", variables);

        TaskService taskService = processEngine.getTaskService();

        TaskQuery query = taskService.createTaskQuery();

        List<Task> tasks = query.list();

        for(Task task : tasks)
        {
            System.out.println(task.getId() + "," + task.getName());
            taskService.complete(task.getId(), variables);
        }

        System.out.println("task end");
    }
}
