package com.codedrive.backend.unit.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.codedrive.backend.repositories.MongoRepo;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class MongoRepoTests {
    @Test
    void repositoriesCanUseTheSameMongoTemplate() {
        MongoTemplate mongoTemplate = mock(MongoTemplate.class);
        MongoRepo mongoRepo = new MongoRepo(mongoTemplate);

        assertSame(mongoTemplate, mongoRepo.getMongoTemplate());
    }
}
