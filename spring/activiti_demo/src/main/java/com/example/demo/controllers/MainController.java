package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.ActivitiProcessService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/activiti/")
public class MainController {

    private final RuntimeService runtimeService;
    private final ActivitiProcessService activitiProcessService;

    
    @Autowired
    public MainController(RuntimeService runtimeService,
                          ActivitiProcessService activitiProcessService) {
        this.runtimeService = runtimeService;
        this.activitiProcessService = activitiProcessService;
    }


    @GetMapping("/health-check")
    public String healthCheck() {
        System.out.println("alive");
        return "alive";
    }
    
    @GetMapping("/uid")
    public String getUid() {
        return UUID.randomUUID().toString();
    }

    //todo example with parallel activiti processes
    @GetMapping("/concurrent-flow")
    public void startConcurrentActivitiProcess() {
      System.out.println("Concurrent Activiti process will be starting!");
      runtimeService.startProcessInstanceByKey("concurrent-activiti-process-demo");
    }

    @GetMapping("/flow")
    public void startActivitiProcess() {
        User user = new User(1, "Bob");
        System.out.println("Activiti process is starting with user: " + user);
        Map<String, Object> variables = new HashMap<>();
        variables.put("USER", user);
        runtimeService.startProcessInstanceByKey("activiti-signal-process-demo", variables);
    }

    /**
     * curl http://127.0.0.1:8088/activiti/executor-flow/activiti-signal-process-demo
     */
    @GetMapping("/executor-flow/{flowName}")
    public void startExecutorActivitiProcess(@PathVariable String flowName) {
        User user = new User(1, "Bob");
        Map<String, Object> params = new HashMap<>();
        params.put("USER", user);
        activitiProcessService.startProcess(flowName, params);
    }
}
