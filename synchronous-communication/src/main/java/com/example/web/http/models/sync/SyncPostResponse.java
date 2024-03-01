package com.example.web.http.models.sync;

import com.example.web.infrastructure.jpa.asyncresponse.datamodel.AsyncResponseDataModel;

import java.util.UUID;

public record SyncPostResponse(UUID requestId, AsyncResponseDataModel data) {
}
