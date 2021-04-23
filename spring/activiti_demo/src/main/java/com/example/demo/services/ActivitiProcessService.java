package com.example.demo.services;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ActivitiProcessService {

    private RuntimeService runtimeService;

    @Autowired
    ActivitiProcessService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void startProcess(String processName, Map<String, Object> params) {
        System.out.println("Start activiti process service!");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            System.out.println("Submit activiti process");
            runtimeService.startProcessInstanceByKey(processName, params);
        });
        executorService.shutdown();
    }

}
