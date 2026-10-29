package com.codedrive.backend.repositories;

import com.codedrive.backend.domain.Folder;
import com.codedrive.backend.exceptions.FolderPersistenceException;
import com.codedrive.backend.exceptions.InvalidFolderException;
import com.codedrive.backend.repositories.entities.FolderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FolderRepoTests {
    private MongoTemplate mongoTemplate;
    private FolderRepo repository;

    @BeforeEach
    void setUp() {
        mongoTemplate = mock(MongoTemplate.class);
        repository = new FolderRepo(new MongoRepo(mongoTemplate));
    }

    @Test
    void saveReturnsSavedFolder() {
        Folder folder = new Folder("Projects", "Source code", new Date(1000));
        Folder savedFolder = new Folder("123", "Projects", "Source code", new Date(1000));
        when(mongoTemplate.save(any(FolderEntity.class))).thenReturn(FolderEntity.mapFrom(savedFolder));

        Folder result = repository.save(folder);

        assertThat(result).usingRecursiveComparison().isEqualTo(savedFolder);
    }

    @Test
    void nullFolderThrowsExceptionWithoutCallingDatabase() {
        assertThatThrownBy(() -> repository.save(null))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder is required.");
        verifyNoInteractions(mongoTemplate);
    }

    @Test
    void databaseFailureThrowsCustomException() {
        Folder folder = new Folder("Projects", null, new Date(1000));
        var databaseError = new DataAccessResourceFailureException("Database unavailable");
        when(mongoTemplate.save(any(FolderEntity.class))).thenThrow(databaseError);

        assertThatThrownBy(() -> repository.save(folder))
                .isInstanceOf(FolderPersistenceException.class)
                .hasMessage("Folder could not be saved. Please try again later.")
                .hasCause(databaseError);
    }
}
