package com.example.ribbon_custom_config_client_demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;


@Service
public class UrlService {

    private final String ribbonServiceName = "demo-services";
    private boolean isSecure = false;

    @Autowired
    LoadBalancerClient loadBalancer;

    UrlService(@Value("${demo-services.ribbon.IsSecure}") boolean isSecure) {
        this.isSecure = isSecure;
    }

    public String getUrl() {
        String protocol = isSecure ? "https://" : "http://";
        return protocol + ribbonServiceName + "/stub";
//        ServiceInstance instance = loadBalancer.choose(ribbonServiceName);
//        return String.format("http://%s:%s", instance.getHost(), instance.getPort());
    }

}
