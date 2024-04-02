package com.example.web.external.communication;

import com.example.web.infrastructure.communication.client.AsyncServiceClient;
import com.example.web.infrastructure.communication.client.DummyControlDataModel;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public record CommunicationService(
        AsyncServiceClient client,
        EventGateway eventGateway
) {
    @CommandHandler
    public void handle(StartCommunication command) {
        client.send(command.communicationId(), command.message());
        var results = client.receive();

        publish(results);
    }

    @CommandHandler
    public void handle(CheckReceiveData command) {
        var results = client.receive(false);

        publish(results);
    }

    private void publish(List<DummyControlDataModel> results) {
        results.stream()
                .map(result ->
                        new CommunicationDataReceived(
                                UUID.fromString(result.getId()),
                                result.getBody())
                )
                .forEach(eventGateway::publish);
    }
}
