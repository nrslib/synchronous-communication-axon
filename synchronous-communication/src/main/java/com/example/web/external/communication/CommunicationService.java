package com.example.web.external.communication;

import com.example.web.infrastructure.communication.client.AsyncServiceClient;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public record CommunicationService(
        AsyncServiceClient client,
        EventGateway eventGateway
) {
    @CommandHandler
    public void startCommunication(StartCommunication command) {
        client.send(command.communicationId(), command.message());
        var results = client.receive();

        results.stream()
                .map(result ->
                        new CommunicationDataReceived(
                                UUID.fromString(result.getId()),
                                result.getBody())
                )
                .forEach(eventGateway::publish);
    }
}
