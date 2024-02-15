package com.example.bootcampwalletservice.repositories;

import com.example.bootcampwalletservice.models.documents.LogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogDocumentRepository extends MongoRepository<LogDocument,String> {
}
