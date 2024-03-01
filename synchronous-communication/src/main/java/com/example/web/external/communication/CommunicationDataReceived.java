package com.example.web.external.communication;

import java.util.UUID;

public record CommunicationDataReceived(UUID requestId, String data) {
}
