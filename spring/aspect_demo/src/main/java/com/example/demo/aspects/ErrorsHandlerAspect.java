package com.example.demo.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

//@Aspect
public class ErrorsHandlerAspect {

//    @AfterThrowing(pointcut = "execution(* com.example.demo..*.*(..))",throwing = "ex")
    private void exceptionsHandler(Exception ex) {
        System.out.println("i am aspect terminator, i've caught you!");
    }

}
