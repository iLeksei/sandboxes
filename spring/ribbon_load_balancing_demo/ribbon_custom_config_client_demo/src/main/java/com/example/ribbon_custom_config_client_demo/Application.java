package com.example.ribbon_custom_config_client_demo;

import com.example.ribbon_custom_config_client_demo.configs.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.ConfigurableApplicationContext;

@RibbonClients(
        @RibbonClient(name = "demo-services", configuration = RibbonConfig.class)
)
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(Application.class, args);
    }

}
