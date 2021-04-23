package com.example.ribbon_balancer_client_demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;


@Service
public class UrlService {

    private final String ribbonServiceName = "demo-services";

//    @Autowired
//    LoadBalancerClient loadBalancer;

//    public String getUrl() {
//        ServiceInstance instance = loadBalancer.choose(ribbonServiceName);
//        return String.format("http://%s:%s", instance.getHost(), instance.getPort());
//    }

}
