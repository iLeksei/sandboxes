package com.example.sprinboot_statemachine_demo;

import com.example.sprinboot_statemachine_demo.controllers.MailController;
import com.example.sprinboot_statemachine_demo.dtos.MailDto;
import com.example.sprinboot_statemachine_demo.entities.Mail;
import com.example.sprinboot_statemachine_demo.enums.MailState;
import com.example.sprinboot_statemachine_demo.services.MailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MailController.class)
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailServiceImpl mailService;

    @Test
    public void shouldReturnHealthStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/health-check"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("alive"));
    }

    @Test
    public void shouldSendToProcessNewMail() throws Exception {
        Mail savedMail = new Mail();
        savedMail.setId(1L);
        savedMail.setAddress("testAddress");
        savedMail.setAuthor("testAuthor");
        savedMail.setMailState(MailState.DELIVERED);
        when(mailService.processNewMail(any()))
                .thenReturn(savedMail);
        MailDto mail = new MailDto("TestAuthor", "Saint-Petersburg");
        String mailJson = new ObjectMapper().writeValueAsString(mail);
        System.out.println(mailJson);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/mail")
            .content(mailJson)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());

        verify(mailService, times(1)).processNewMail(any(MailDto.class));
    }


}
