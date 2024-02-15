package com.example.bootcampwalletservice.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("LOGWALLET")
public class LogDocument {
    @MongoId
    private String id;
    private Object request;
    private Object response;
    private String methodName;
    private String errorTrace;
}
