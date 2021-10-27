package com.example.sprinboot_statemachine_demo.configs;

import com.example.sprinboot_statemachine_demo.entities.Mail;
import com.example.sprinboot_statemachine_demo.enums.MailEvent;
import com.example.sprinboot_statemachine_demo.enums.MailState;
import com.example.sprinboot_statemachine_demo.repositories.MailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

import static com.example.sprinboot_statemachine_demo.utils.MessageUtils.buildMessage;

@Slf4j
@Configuration
@EnableStateMachineFactory
public class MailStatemachineConfig extends EnumStateMachineConfigurerAdapter<MailState, MailEvent> {

    private MailRepository mailRepository;

    @Autowired
    MailStatemachineConfig(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    private final Action<MailState, MailEvent> actionAccept = ctx -> {
        Long mailId = (Long) ctx.getMessageHeader("MAIL_ID");
        logAction(ctx, mailId);
        ctx.getStateMachine().sendEvent(buildMessage(mailId, MailEvent.VALIDATE));
    };

    private final Action<MailState, MailEvent> actionValidate = ctx -> {
        Long mailId = (Long) ctx.getMessageHeader("MAIL_ID");
        logAction(ctx, mailId);
        assert mailId != null;
        Mail mail = mailRepository.getOne(mailId);

        if (mail.isValid()) {
            ctx.getStateMachine().sendEvent(buildMessage(mailId, MailEvent.SEND));
        } else {
            ctx.getStateMachine().sendEvent(buildMessage(mailId, MailEvent.REJECT));
        }
    };

    private final Action<MailState, MailEvent> actionSend  = ctx -> {
        Long mailId = (Long)  ctx.getMessageHeader("MAIL_ID");
        logAction(ctx, mailId);
        assert mailId != null;
        ctx.getStateMachine().sendEvent(buildMessage(mailId, MailEvent.SEND));
    };

    private final Action<MailState, MailEvent> actionReject  = ctx -> {
        Long mailId = (Long)  ctx.getMessageHeader("MAIL_ID");
        logAction(ctx, mailId);
        assert mailId != null;
        ctx.getStateMachine().sendEvent(buildMessage(mailId, MailEvent.REJECT));
    };


    @Override
    public void configure(StateMachineStateConfigurer<MailState, MailEvent> states) throws Exception {
        states.withStates()
                .initial(MailState.NEW)
                .state(MailState.ACCEPTED, actionAccept)
                .state(MailState.VALID, actionValidate)
                .state(MailState.DELIVERED, actionSend)
                .state(MailState.REJECTED, actionReject)
                .states(EnumSet.allOf(MailState.class))
                .end(MailState.DELIVERED)
                .end(MailState.REJECTED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<MailState, MailEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(MailState.NEW).target(MailState.ACCEPTED).event(MailEvent.ACCEPT)
                .and()
                .withExternal()
                .source(MailState.ACCEPTED).target(MailState.VALID).event(MailEvent.VALIDATE)
                .and()
                .withExternal()
                .source(MailState.ACCEPTED).target(MailState.REJECTED).event(MailEvent.REJECT)
                .and()
                .withExternal()
                .source(MailState.VALID).target(MailState.DELIVERED).event(MailEvent.SEND);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<MailState, MailEvent> config) throws Exception {
        StateMachineListenerAdapter<MailState, MailEvent> adapter =
                new StateMachineListenerAdapter<MailState, MailEvent>() {
                    @Override
            public void stateChanged(State from, State to) {
                String fromState = from == null ? "null" : from.getId().toString();
                String toState = to == null ? "null" : to.getId().toString();
                log.info(String.format("Changed state from: {%s} to: {%s}", fromState, toState));
            }
        };
        config.withConfiguration().listener(adapter);
    }

    void logAction(StateContext<MailState, MailEvent> ctx, Long mailId) {
        MailState id = ctx.getStateMachine().getState().getId();
        if (mailId != null) {
            Mail mail = mailRepository.getOne(mailId);
            log.info("MAIL={} STATE={}", mail, id + "");
        }
    }
}
