package com.example.web.http.controllers.sync;

import com.example.web.app.application.query.asyncrespoonse.queries.find.FindAsyncResponse;
import com.example.web.app.application.query.asyncrespoonse.queries.find.FindAsyncResponseResult;
import com.example.web.external.communication.StartCommunication;
import com.example.web.http.models.sync.SyncPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Tag(description = "Sync API", name = "Sync API")
@RestController
@RequestMapping("/api/sync")
public record SyncController(
        CommandGateway commandGateway,
        QueryGateway queryGateway
) {
    @Operation(summary = "Test.")
    @PostMapping
    public Mono<SyncPostResponse> post() {
        var requestId = UUID.randomUUID();
        System.out.println(requestId);

        var query = new FindAsyncResponse(requestId);
        var result = queryGateway.subscriptionQuery(query, ResponseTypes.instanceOf(FindAsyncResponseResult.class), ResponseTypes.instanceOf(FindAsyncResponseResult.class));

        var command = new StartCommunication(requestId, "Hello, world! @" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        commandGateway.sendAndWait(command);

        return Mono.from(result.updates()
                .filter(update -> update.data() != null)
                .next()
                .map(updateResult -> new SyncPostResponse(requestId, updateResult.data()))
                .timeout(Duration.ofSeconds(10))
                .onErrorResume(Mono::error)
                .doFinally(signalType -> result.close())
        );
    }
}
