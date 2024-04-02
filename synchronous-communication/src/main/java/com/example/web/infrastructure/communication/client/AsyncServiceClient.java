package com.example.web.infrastructure.communication.client;

import java.util.List;
import java.util.UUID;

public record AsyncServiceClient(
        AsyncServiceClientDummyControlRepository dummyControlRepository
) {
    public void send(UUID id, String message) {
        var data = new DummyControlDataModel();
        data.setId(id.toString());
        data.setBody(message);
        dummyControlRepository.save(data);
    }

    public List<DummyControlDataModel> receive() {
        return receive(true);
    }

    public List<DummyControlDataModel> receive(boolean pooling) {
        var dataList = dummyControlRepository.findAll();

        if (pooling && dataList.size() < 3) {
            return List.of();
        }

        dummyControlRepository.deleteAllById(dataList.stream().map(DummyControlDataModel::getId).toList());

        return dataList;
    }
}
