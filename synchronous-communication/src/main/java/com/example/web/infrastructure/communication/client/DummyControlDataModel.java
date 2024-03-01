package com.example.web.infrastructure.communication.client;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dummy_control_data")
public class DummyControlDataModel {
    @Id
    String id;

    String body;
}
