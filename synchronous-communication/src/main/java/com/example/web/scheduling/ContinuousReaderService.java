package com.example.web.scheduling;

import com.example.web.external.communication.CheckReceiveData;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public record ContinuousReaderService(CommandGateway commandGateway) {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ContinuousReaderService.class);

    @Scheduled(fixedDelay = 5000)
    public void read() {
        logger.info("Checking for new received data...");
        var command = new CheckReceiveData();
        commandGateway.sendAndWait(command);
    }
}
