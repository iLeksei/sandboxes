package com.example.demo.activiti;

import com.example.demo.entities.User;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Scope(scopeName = SCOPE_PROTOTYPE)
@Component("prepareSubActivitiFlowDelegate")
public class PrepareSubActivitiFlowDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("PREPARE_SUB_ACTIVITI_FLOW_DELEGATE IS WORKING");
        User user = (User) execution.getVariable("USER");
        System.out.println("preparing user id for sub-flow. user: "  + user);
        execution.setVariable("USER_ID", user.getId());

//        execution.getVariables().forEach((k,v)->{System.out.println(k);});
//        execution.setVariableLocal("USER_ID", user.getId());
    }
}
