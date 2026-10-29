package com.codedrive.backend.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepo {
    private final MongoTemplate mongoTemplate;

    public MongoRepo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
