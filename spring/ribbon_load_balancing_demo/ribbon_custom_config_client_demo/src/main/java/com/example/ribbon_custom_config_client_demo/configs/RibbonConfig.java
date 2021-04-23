package com.example.ribbon_custom_config_client_demo.configs;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

//    @Autowired
//    private IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new BestAvailableRule();
    }


//    @Autowired
//    IClientConfig clientConfig;
//
//    @Bean
//    public IPing ping() {
//        return new NoOpPing();
////        return new PingUrl();
//    }
//
//    @Bean
//    public IRule ribbonRule() {
//        return new BestAvailableRule();
//    }

//    @Bean
//    public ServerListSubsetFilter serverListFilter() {
//        return new ServerListSubsetFilter();
//    }
}
