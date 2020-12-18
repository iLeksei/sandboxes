package com.example.demo;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MainService {

    @InjectList({Wrestler.class, Runner.class, Shooter.class})
    private List<AbilityHandler> handlers;

    public void showAbilities() {
        handlers.stream().forEach(AbilityHandler::showAbility);
    }

    @PostConstruct
    private void init() {
        showAbilities();
    }

}
