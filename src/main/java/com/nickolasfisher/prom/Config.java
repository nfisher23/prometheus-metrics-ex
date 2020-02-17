package com.nickolasfisher.prom;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    // used https://grafana.com/grafana/dashboards/10280. Worked out nicely
    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTags("application", "PromApplication")
                .commonTags("instance", "prom-1").commonTags("hikaricp", "HikariPool-1");
    }

    // this isn't doing anything for me, by the way
    @Bean
    HttpTraceRepository httpTraceRepository() {
        InMemoryHttpTraceRepository inMemoryHttpTraceRepository = new InMemoryHttpTraceRepository();
        inMemoryHttpTraceRepository.setCapacity(1000);
        return inMemoryHttpTraceRepository;
    }
}
