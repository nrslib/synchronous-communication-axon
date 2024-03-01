package com.example.web.infrastructure.jpa.asyncresponse.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "async_response_data")
public class AsyncResponseDataModel {
    @Id
    String requestId;

    String data;
}
