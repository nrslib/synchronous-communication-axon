package com.example.web.infrastructure.communication.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsyncServiceClientDummyControlRepository extends JpaRepository<DummyControlDataModel, String> {
}
