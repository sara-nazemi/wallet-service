package com.example.bootcampwalletservice.models.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("LOGWALLET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogDocument {
    @MongoId
    private String id;
    private Object request;
    private Object response;
    private String methodName;
    private String errorTrace;
}
