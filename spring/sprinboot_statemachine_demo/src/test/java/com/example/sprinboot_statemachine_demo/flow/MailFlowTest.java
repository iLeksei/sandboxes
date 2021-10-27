package com.example.sprinboot_statemachine_demo.flow;

import com.example.sprinboot_statemachine_demo.Application;
import com.example.sprinboot_statemachine_demo.dtos.MailDto;
import com.example.sprinboot_statemachine_demo.entities.Mail;
import com.example.sprinboot_statemachine_demo.enums.MailState;
import com.example.sprinboot_statemachine_demo.repositories.MailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MailFlowTest {

    private String mailJson;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MailRepository mailRepository;

    @Before
    public void setUp() throws JsonProcessingException {
        MailDto mailDto = new MailDto();
        mailDto.setAddress("testAddress");
        mailDto.setAuthor("testAuthor");
        mailJson = new ObjectMapper().writeValueAsString(mailDto);
    }

    @Test
    public void shouldProcessMail() throws InterruptedException, ExecutionException {
        assert mailJson != null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> body = new HttpEntity<>(mailJson, headers);
        ResponseEntity<MailDto> response =
                restTemplate.postForEntity("http://localhost:8080/mail", body, MailDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<MailState> mailState = service.submit(() -> {
            try {
                Thread.sleep(10000);
                Mail processingMail = mailRepository.getOne(Objects.requireNonNull(response.getBody()).getId());
                return processingMail.getMailState();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });
        assertEquals(MailState.DELIVERED.toString(), mailState.get().toString());
        service.shutdown();
    }
}
