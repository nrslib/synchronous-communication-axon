package com.example.web.app.application.query.asyncrespoonse;

import com.example.web.infrastructure.jpa.asyncresponse.AsyncResponseRepository;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

@Component
public record AsyncResponseProjection(
        AsyncResponseRepository repository,
        QueryUpdateEmitter queryUpdateEmitter
) {
}
