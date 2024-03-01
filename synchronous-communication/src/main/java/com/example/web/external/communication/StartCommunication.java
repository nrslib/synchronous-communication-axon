package com.example.web.external.communication;

import java.util.UUID;

public record StartCommunication(UUID communicationId, String message) {
}
