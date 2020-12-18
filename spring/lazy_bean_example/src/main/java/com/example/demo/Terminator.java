package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Terminator {

    @PostConstruct
    private void init() {
        System.out.println("I am ready!!!");
    }

    @Lazy  // <- very important !!!
    @Autowired
    private Blaster blaster;

    private int stamina = 3;

    @Scheduled(fixedDelay = 1000)
    private void killEnemies() {
        if (!this.isTired()) {
            System.out.println("Attack with club");
        } else {
            blaster.shoot();
        }
        stamina--;
    }

    public void sayHi() {
        System.out.println("I am Arnold Schwarzenegger");
    }

    private boolean isTired() {
        return this.stamina <= 0;
    }
}
