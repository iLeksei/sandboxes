package com.example.demo.activiti;

import com.example.demo.entities.User;
import com.example.demo.services.ActivitiProcessService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Scope(scopeName = SCOPE_PROTOTYPE)
@Component("concurrentSubFlowDelegate")
public class ConcurrentSubFlowDelegate implements JavaDelegate {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Override
    public void execute(DelegateExecution execution) {
        try {
        User user = (User) execution.getVariable("USER");
        System.out.println("CONCURRENT_SUBFLOW_DELEGATE: preparing user id for sub-flow. user: "  + user);
        System.out.println("PROCESS INSTANCE ID: " + execution.getProcessInstanceId());
        System.out.println("CURRENT_ACTIVITI_ID: " + execution.getCurrentActivityId());
        execution.setVariable("USER_ID", user.getId());
        Thread.sleep(5000);
        Map<String, Object> params = new HashMap<>();
        params.put("USER_ID", user.getId());
        execution.setTransientVariable("uid", "123456");
        execution.setVariable("uid", "123456");
        activitiProcessService.startProcess("activiti-sub-flow-demo",params);

//        Map<String, String> params = new HashMap<>();
//        ActivitiEventBuilder.createActivitiySignalledEvent(execution, "COMPLETE_SUBFLOW", params)
//                .getSignalName();

//            runtimeService.createExecutionQuery()
//                    .processInstanceId(execution.getProcessInstanceId())
//                    .signalEventSubscriptionName("COMPLETE_SUBFLOW")
//                    .singleResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
