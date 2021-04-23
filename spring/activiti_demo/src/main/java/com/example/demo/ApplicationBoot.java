package com.example.demo;

import com.example.demo.configs.SecurityConfig;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.db.DbSchemaCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationBoot {

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    ProcessEngine processEngine;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
        new ApplicationBoot().login();
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcUrl("jdbc:h2:mem://127.0.0.1:8088/TESTDB;DB_CLOSE_DELAY=1000")
                .setDatabaseSchema("PUBLIC")
                .setAsyncExecutorActivate(true)
                .buildProcessEngine();
    }

    private void login() {
        securityConfig.logInAs("system");
    }

}
