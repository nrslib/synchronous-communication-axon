package com.example.web.scheduling;

import com.example.web.external.communication.CheckReceiveData;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public record ContinuousReaderService(CommandGateway commandGateway) {
    @Scheduled(fixedDelay = 5000)
    public void read() {
        System.out.println("proc");
        var command = new CheckReceiveData();
        commandGateway.sendAndWait(command);
    }
}
