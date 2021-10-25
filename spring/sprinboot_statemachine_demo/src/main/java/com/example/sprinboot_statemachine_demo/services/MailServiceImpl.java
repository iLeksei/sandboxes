package com.example.sprinboot_statemachine_demo.services;

import com.example.sprinboot_statemachine_demo.dtos.MailDto;
import com.example.sprinboot_statemachine_demo.entities.Mail;
import com.example.sprinboot_statemachine_demo.enums.MailEvent;
import com.example.sprinboot_statemachine_demo.enums.MailState;
import com.example.sprinboot_statemachine_demo.repositories.MailRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class MailServiceImpl implements MailService{

    public final String MAIL_ID_HEADER = "MAIL_ID";

    private final StateMachineFactory<MailState, MailEvent> stateMachineFactory;

    private final MailRepository mailRepository;

    @Autowired
    MailServiceImpl(StateMachineFactory<MailState, MailEvent> stateMachineFactory,
                    MailRepository mailRepository) {
        this.stateMachineFactory = stateMachineFactory;
        this.mailRepository =  mailRepository;
    }

    @Override
    public void registerNewMail(MailDto newMail) {
        Mail mail = Mail.builder()
                .author(newMail.getAuthor())
                .address(newMail.getAddress())
                .mailState(MailState.NEW)
                .build();
        Mail savedMail = mailRepository.saveAndFlush(mail);
        StateMachine<MailState, MailEvent> sm = build(savedMail);
        sendEvent(savedMail.getId(), sm, MailEvent.ACCEPT);
    }

    private void sendEvent(Long mailId, StateMachine<MailState, MailEvent> sm, MailEvent event) {
        log.debug("Sending event to statemachine with MAIL_ID: {}", mailId);
        Message<MailEvent> msg = MessageBuilder.withPayload(event)
                .setHeader(MAIL_ID_HEADER, mailId)
                .build();
        sm.sendEvent(msg);
    }

    private StateMachine<MailState, MailEvent> build(Mail mail) {
        log.debug("Building statemachine for mail with id: {}", mail.getId());
        StateMachine<MailState, MailEvent> sm = stateMachineFactory.getStateMachine(Long.toString(mail.getId()));
        sm.stop();

        // reset statemachine manually
        sm.getStateMachineAccessor()
                .doWithAllRegions( sma -> {
                    sma.resetStateMachine(new DefaultStateMachineContext<>(
                            mail.getMailState(), null, null, null));
                    sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<MailState, MailEvent>() {
                        @Override
                        public void preStateChange(State<MailState, MailEvent> state, Message<MailEvent> message, Transition<MailState, MailEvent> transition, StateMachine<MailState, MailEvent> stateMachine) {
                            Optional.ofNullable(message).ifPresent((msg) -> {
                                Optional.ofNullable((Long) message.getHeaders().getOrDefault(MAIL_ID_HEADER, -1L))
                                        .ifPresent( mailId -> {
                                            Mail mail = mailRepository.getOne(mailId);
                                            mail.setMailState(state.getId());
                                            mailRepository.saveAndFlush(mail);
                                        });
                            });
                        }
                    });
                });
        sm.start();
        return sm;
    }
}
