package com.example.spring_integration_demo.flow;


import com.example.spring_integration_demo.dto.MailDto;
import com.example.spring_integration_demo.enums.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.integration.router.HeaderValueRouter;

import java.io.Serializable;

@Slf4j
@Configuration
public class MailFlowConfig {

    public final String REGION_MESSAGE_HEADER = "REGION";

    @Bean
    public HttpRequestHandlingMessagingGateway mailInbound() {
        HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
        gateway.setRequestMapping(getRequestMapping());
        gateway.setRequestPayloadType(ResolvableType.forClass(MailDto.class).as(MailDto.class));
        gateway.setRequestChannelName("mailInputChannel");
        return gateway;
    }

    @Bean
    public RequestMapping getRequestMapping() {
        RequestMapping mapping = new RequestMapping();
        mapping.setMethods(HttpMethod.POST);
        mapping.setPathPatterns("/mail2");
        return mapping;
    }

    @Bean("mailInputChannel")
    public DirectChannel mailInputChannel() {
        return new DirectChannel();
    }

    @Bean("mailOutputChannel")
    public DirectChannel mailOutputChannel() {
        return new DirectChannel();
    }

    @Bean("canRouterChannel")
    public DirectChannel canRouterChannel() {
        return new DirectChannel();
    }

    @Bean("usaRouterChannel")
    public DirectChannel usaRouterChannel() {
        return new DirectChannel();
    }

    @Bean("unknownRegionRouterChannel")
    public DirectChannel unknownRegionRouterChannel() {
        return new DirectChannel();
    }

    @Bean("errorChannel")
    public DirectChannel errorChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "mailInputChannel")
    @Bean
    public HeaderValueRouter destinationCountryRouter() {
        HeaderValueRouter router = new HeaderValueRouter(Header.REGION_MESSAGE_HEADER.getValue());
        router.setChannelMapping("CAN", "canRouterChannel");
        router.setChannelMapping("USA", "usaRouterChannel");
        router.setChannelMapping(null, "unknownRegionRouterChannel");
        return router;
    }

    // todo add errorRouter example
    @ServiceActivator(inputChannel = "mailInputChannel")
    @Bean
    public ErrorMessageExceptionTypeRouter exceptionRouter() {
        ErrorMessageExceptionTypeRouter router = new ErrorMessageExceptionTypeRouter();
        router.setDefaultOutputChannel(errorChannel());
        return router;
    }

    @Bean
    public IntegrationFlow mailListener() throws Exception {
        return IntegrationFlows.from("mailInputChannel")
                .log()
                .handle("mailServiceImpl", "handleMail")
                .filter( (MailDto mail) -> {
                    if (mail.getAuthor() == null ||
                            mail.getAddress() == null ||
                            mail.getRegion() == null) {
                        return false;
                    }
                    return true;
                })
                .route(destinationCountryRouter())
                .get();
    }

    @Bean
    public IntegrationFlow usaRouterFlow() {
        return IntegrationFlows.from("usaRouterChannel")
                .log()
                .nullChannel();
    }


    @Bean
    public IntegrationFlow canRouterFlow() {
        return IntegrationFlows.from("canRouterChannel")
                .log((mail) -> {
                    log.info("Your mail is in the Canada router dude!!!");
                    return mail;
                })
                .nullChannel();
    }

    @Bean
    public IntegrationFlow unknownRegionRouterFlow() {
        return IntegrationFlows.from("unknownRegionRouterChannel")
                .log((mail) -> {
                    log.info("Your mail doesn't have region header!!!");
                    return mail;
                })
                .nullChannel();
    }



}
