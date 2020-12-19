package com.example.demo.configs;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Method;

public class CustomPointcut extends DynamicMethodMatcherPointcut {

    private String DEFAULT_PACKAGES_PATH = "com.example.demo.controllers";

    @Value("${package.scan.path}")
    private String packageScanPath;

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        return true;
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                return aClass.getPackage().getName().contains(DEFAULT_PACKAGES_PATH);
            }
        };
    }
}
