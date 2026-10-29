package com.codedrive.backend.services;

import com.codedrive.backend.Interfaces.repositories.iFolderRepo;
import com.codedrive.backend.domain.Folder;
import com.codedrive.backend.exceptions.InvalidFolderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FolderServiceTests {
    private iFolderRepo repository;
    private FolderService service;

    @BeforeEach
    void setUp() {
        repository = mock(iFolderRepo.class);
        service = new FolderService(repository);
    }

    @Test
    void validFolderReturnsSavedFolder() {
        Folder folder = new Folder("Projects", null, new Date(1000));
        Folder savedFolder = new Folder("123", "Projects", null, new Date(1000));
        when(repository.save(folder)).thenReturn(savedFolder);

        Folder result = service.createFolder(folder);

        assertThat(result).usingRecursiveComparison().isEqualTo(savedFolder);
    }

    @Test
    void nullFolderThrowsException() {
        assertThatThrownBy(() -> service.createFolder(null))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder is required.");
        verifyNoInteractions(repository);
    }

    @Test
    void missingNameThrowsExceptionWithMessage() {
        Folder folder = new Folder(null, null, new Date(1000));

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder name is required.");
        verifyNoInteractions(repository);
    }

    @Test
    void blankNameThrowsExceptionWithMessage() {
        Folder folder = new Folder("  ", null, new Date(1000));

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder name is required.");
        verifyNoInteractions(repository);
    }

    @Test
    void missingCreationDateThrowsExceptionWithMessage() {
        Folder folder = new Folder("Projects", null, null);

        assertThatThrownBy(() -> service.createFolder(folder))
                .isInstanceOf(InvalidFolderException.class)
                .hasMessage("Folder creation date is required.");
        verifyNoInteractions(repository);
    }
}
