package com.example.actuator_influx_grafana_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        while (true){
//            new Thread(() -> {
//                try {
//                    while (true){
//                        Thread.sleep(5000);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).run();
//        }
    }

}
