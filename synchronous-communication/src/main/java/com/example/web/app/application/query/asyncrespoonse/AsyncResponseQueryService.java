package com.example.web.app.application.query.asyncrespoonse;

import com.example.web.app.application.query.asyncrespoonse.queries.find.FindAsyncResponse;
import com.example.web.app.application.query.asyncrespoonse.queries.find.FindAsyncResponseResult;
import com.example.web.external.communication.CommunicationDataReceived;
import com.example.web.infrastructure.jpa.asyncresponse.AsyncResponseRepository;
import com.example.web.infrastructure.jpa.asyncresponse.datamodel.AsyncResponseDataModel;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

@Service
public record AsyncResponseQueryService(AsyncResponseRepository repository,
                                        QueryUpdateEmitter queryUpdateEmitter) {
    @EventHandler
    public void on(CommunicationDataReceived event) {
        var data = new AsyncResponseDataModel();
        data.setRequestId(event.requestId().toString());
        data.setData(event.data());

        repository.save(data);

        queryUpdateEmitter.emit(
                FindAsyncResponse.class,
                query -> query.requestId().equals(event.requestId()),
                new FindAsyncResponseResult(data)
        );
    }
    @QueryHandler
    public FindAsyncResponseResult handle(FindAsyncResponse criteria) {
        var maybeData = repository.findById(criteria.requestId().toString());

        return new FindAsyncResponseResult(maybeData.orElse(null));
    }
}
