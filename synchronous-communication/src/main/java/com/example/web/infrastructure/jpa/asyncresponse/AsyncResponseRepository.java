package com.example.web.infrastructure.jpa.asyncresponse;

import com.example.web.infrastructure.jpa.asyncresponse.datamodel.AsyncResponseDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsyncResponseRepository extends JpaRepository<AsyncResponseDataModel, String> {
}
