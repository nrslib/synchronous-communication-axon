package com.example.web.app.application.query.asyncrespoonse;

import com.example.web.app.application.query.asyncrespoonse.queries.find.FindAsyncResponse;
import com.example.web.app.application.query.asyncrespoonse.queries.find.FindAsyncResponseResult;
import com.example.web.infrastructure.jpa.asyncresponse.AsyncResponseRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public record AsyncResponseQueryService(AsyncResponseRepository repository) {
    @QueryHandler
    public FindAsyncResponseResult handle(FindAsyncResponse criteria) {
        var maybeData = repository.findById(criteria.requestId().toString());

        return new FindAsyncResponseResult(maybeData.orElse(null));
    }
}
