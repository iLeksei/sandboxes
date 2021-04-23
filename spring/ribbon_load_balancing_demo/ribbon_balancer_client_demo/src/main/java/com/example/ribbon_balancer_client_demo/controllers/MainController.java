package com.example.ribbon_balancer_client_demo.controllers;

import com.example.ribbon_balancer_client_demo.services.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
public class MainController {

    private final RestTemplate restTemplate;
    private UrlService urlService;
    private final String ribbonServiceName = "demo-services";

    @Value("${demo-services.ribbon.listOfServers}")
    private String servicesUrl;


    @Autowired
    MainController(RestTemplate restTemplate, UrlService urlService) {
        this.restTemplate = restTemplate;
        this.urlService = urlService;
    }


    @GetMapping("/services")
    public String callBalancedService() {
//        System.out.println("all urls: " + servicesUrl);
//        return restTemplate.getForObject(urlService.getUrl() + "/service" , String.class);
        String result = restTemplate.getForObject("http://" + ribbonServiceName + "/service", String.class);
        System.out.println("getting response from service with port: " + result);
        return "getting response from service with port: " + result;
    }

    @GetMapping(value = "healthCheck")
    public String healthCheck() {
        return "alive";
    }
}
