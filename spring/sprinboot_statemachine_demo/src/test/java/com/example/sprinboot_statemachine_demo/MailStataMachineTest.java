package com.example.sprinboot_statemachine_demo;

import com.example.sprinboot_statemachine_demo.enums.MailEvent;
import com.example.sprinboot_statemachine_demo.enums.MailState;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class MailStataMachineTest {

    @Autowired
    StateMachineFactory<MailState, MailEvent> factory;

    @Test
    public void shouldCreateStateMachine() {
        StateMachine<MailState, MailEvent> stateMachine = factory.getStateMachine(UUID.randomUUID());
        String expectedState = "NEW";
        stateMachine.start();
        assertEquals(expectedState, stateMachine.getState().getId().toString());
        stateMachine.stop();
    }

    @Test
    public void shouldChangeStates() {
        StateMachine<MailState, MailEvent> stateMachine = factory.getStateMachine(UUID.randomUUID());
        stateMachine.start();
        assertEquals(MailState.NEW.toString(), stateMachine.getState().getId().toString());
        Message msg = null;

        stateMachine.sendEvent(MailEvent.ACCEPT);
        assertEquals(MailState.ACCEPTED.toString(), stateMachine.getState().getId().toString());

        stateMachine.sendEvent(MailEvent.VALIDATE);
        assertEquals(MailState.VALID.toString(), stateMachine.getState().getId().toString());

        stateMachine.sendEvent(MailEvent.SEND);
        assertEquals(MailState.DELIVERED.toString(), stateMachine.getState().getId().toString());
        stateMachine.stop();
    }

}
