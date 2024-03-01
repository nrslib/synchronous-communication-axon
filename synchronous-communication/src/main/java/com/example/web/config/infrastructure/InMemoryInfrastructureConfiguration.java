package com.example.web.config.infrastructure;

import com.example.web.infrastructure.communication.client.AsyncServiceClient;
import com.example.web.infrastructure.communication.client.AsyncServiceClientDummyControlRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryInfrastructureConfiguration {
    @Bean
    public AsyncServiceClient asyncServiceClient(AsyncServiceClientDummyControlRepository repository) {
        return new AsyncServiceClient(repository);
    }
}
