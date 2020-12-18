package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LazyTerminator {

    @PostConstruct
    private void init() {
        System.out.println("Lazy Terminator is waking up");
    }

//    @Scheduled(fixedDelay = 1200)
    public void killEnemies() {
        System.out.println("sorry, i am lazy, not today :)");
    }

}
