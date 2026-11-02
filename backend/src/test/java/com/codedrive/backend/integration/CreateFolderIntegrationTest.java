package com.codedrive.backend.integration;

import com.codedrive.backend.domain.Folder;
import com.codedrive.backend.exceptions.FolderPersistenceException;
import com.codedrive.backend.exceptions.InvalidFolderException;
import com.codedrive.backend.repositories.FolderRepo;
import com.codedrive.backend.repositories.MongoRepo;
import com.codedrive.backend.repositories.entities.FolderEntity;
import com.codedrive.backend.services.FolderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringJUnitConfig({FolderService.class, FolderRepo.class, MongoRepo.class})
class CreateFolderIntegrationTest {

    @Autowired
    private FolderService service;

    // in order for the integration test to work, we have to import a mock of mongodb
    @MockitoBean
    private MongoTemplate mongoTemplate;

    @Test
    void creatingFolderReturnsSavedFolder() {
        Folder folder = new Folder("Projects", "Source code", new Date(1000));
        Folder savedFolder = new Folder("123", "Projects", "Source code", new Date(1000));
        when(mongoTemplate.save(any(FolderEntity.class))).thenReturn(FolderEntity.mapFrom(savedFolder));

        Folder result = service.createFolder(folder);

        assertThat(result).usingRecursiveComparison().isEqualTo(savedFolder);
    }

    @Test
    void creatingFolderWithoutDescriptionReturnsSavedFolder() {
        Folder folder = new Folder("Projects", null, new Date(1000));
        Folder savedFolder = new Folder("123", "Projects", null, new Date(1000));
        when(mongoTemplate.save(any(FolderEntity.class))).thenReturn(FolderEntity.mapFrom(savedFolder));

        Folder result = service.createFolder(folder);

        assertThat(result).usingRecursiveComparison().isEqualTo(savedFolder);
    }

    @Test
    void nullFolderIsRejectedWithoutSaving() {
        assertThatThrownBy(() -> service.createFolder(null))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder is required.");

        verifyNoInteractions(mongoTemplate);
    }

    @Test
    void missingNameIsRejectedWithoutSaving() {
        Folder folder = new Folder(null, "Source code", new Date(1000));

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder name is required.");

        verifyNoInteractions(mongoTemplate);
    }

    @Test
    void emptyNameIsRejectedWithoutSaving() {
        Folder folder = new Folder("", "Source code", new Date(1000));

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder name is required.");

        verifyNoInteractions(mongoTemplate);
    }

    @Test
    void blankNameIsRejectedWithoutSaving() {
        Folder folder = new Folder("   ", "Source code", new Date(1000));

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder name is required.");

        verifyNoInteractions(mongoTemplate);
    }

    @Test
    void missingCreationDateIsRejectedWithoutSaving() {
        Folder folder = new Folder("Projects", "Source code", null);

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder creation date is required.");

        verifyNoInteractions(mongoTemplate);
    }

    @Test
    void databaseFailureReachesServiceAsCustomException() {
        Folder folder = new Folder("Projects", "Source code", new Date(1000));
        var databaseError = new DataAccessResourceFailureException("Database unavailable");
        when(mongoTemplate.save(any(FolderEntity.class))).thenThrow(databaseError);

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(FolderPersistenceException.class)
                .hasMessage("Folder could not be saved. Please try again later.")
                .hasCause(databaseError);
    }
}
