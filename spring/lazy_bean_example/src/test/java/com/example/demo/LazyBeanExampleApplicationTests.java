package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TestEnvConfig.class})
class LazyBeanExampleApplicationTests {

    @Autowired
    private Terminator terminator;

    @Autowired
    private LazyTerminator lazyTerminator;

    @Test
    void testTerminators() {
        terminator.sayHi();
    }

}
