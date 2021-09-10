package com.example.actuator_influx_grafana_demo.jobs;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import io.micrometer.core.instrument.Metrics;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableScheduling
@Configuration
public class MetricsJob {

    @Value("${instanceName:test_1}")
    private String instanceName;

    @Value("${instance_name:mock}")
    private String instName;


    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> {
            registry.config().commonTags("instance", instanceName);
            new ClassLoaderMetrics().bindTo(registry);
            new JvmMemoryMetrics().bindTo(registry);
            new JvmGcMetrics().bindTo(registry);
            new ProcessorMetrics().bindTo(registry);
            new JvmThreadMetrics().bindTo(registry);
        };
    }

    @Scheduled(fixedDelayString = "5000")
    private void sendMetrics() throws UnknownHostException {
        System.out.println("sending metrics to influxdb...");
        System.out.println("instance name: " + instName);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        System.out.println(InetAddress.getLoopbackAddress().getCanonicalHostName());
    }


}
