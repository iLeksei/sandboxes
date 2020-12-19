package com.example.demo.configs;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

class ExceptionsHandlerAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            return methodInvocation.proceed();
        } catch (Throwable exc) {
            System.out.println("Oh my gosh! Something was wrong! I will catch you error!");
            System.out.println(methodInvocation.getMethod().getDeclaringClass().getName());
            System.out.println(methodInvocation.getMethod().getName());
            throw exc; // <- important, cause we can damaged business logic without that
        }
    }
}
