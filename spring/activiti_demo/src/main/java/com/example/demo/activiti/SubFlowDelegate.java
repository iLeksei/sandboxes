package com.example.demo.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Scope(scopeName = SCOPE_PROTOTYPE)
@Component("subFlowDelegate")
public class SubFlowDelegate implements JavaDelegate {

    // url from postman mock server
    private final String uidUrl = "http://127.0.0.1:8088/activiti/uid";

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    RuntimeService runtimeService;

    /**
     * check activiti:inheritVariables="true" <- need for getting variables from parent pricess
     */
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("SUB_FLOW_DELEGATE IS WORKING");
        execution.getExecutions().forEach(System.out::println);
        System.out.println("PROCESS INSTANCE ID: " + execution.getProcessInstanceId());
        System.out.println("CURRENT_ACTIVITI_ID: " + execution.getSuperExecutionId());
        Long user_id = (Long) execution.getVariable("USER_ID");
        System.out.println("SUB_FLOW_DELEGATE got user with id: " + user_id);
        try {
            System.out.println("Will sleep a few seconds...");
            Thread.sleep(   5000);
            System.out.println("Awaking...");
            String uid = restTemplate.getForObject(uidUrl, String.class);
            System.out.println("SUB_FLOW_DELEGATE called url: " + uidUrl + "\nand got uid: " + uid);
            Map<String, Object> params = new HashMap<>();
            execution.setVariable("uid", "123456");
            runtimeService.createExecutionQuery()
                    .signalEventSubscriptionName("COMPLETE_SUBFLOW")
                    .list()
                    .forEach(System.out::println);
            runtimeService.signalEventReceived("COMPLETE_SUBFLOW");
//            runtimeService.messageEventReceived("CONTINUE_PROCESS"); //try to send by executionId

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}