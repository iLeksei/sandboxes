package com.example.demo.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("signalLogger")
public class SignalLogger implements ExecutionListener {
//    @Override
//    public void execute(DelegateExecution execution) {
//        System.out.println("GOT SIGNAL:" + execution.getEventName());
//    }

    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("GOT SIGNAL:" + execution.getEventName());
    }
}
