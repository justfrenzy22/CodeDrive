package com.codedrive.backend.services;

import com.codedrive.backend.domain.Folder;
import com.codedrive.backend.exceptions.InvalidFolderException;
import com.codedrive.backend.Interfaces.repositories.iFolderRepo;
import com.codedrive.backend.Interfaces.services.iFolderService;
import org.springframework.stereotype.Service;

@Service
public class FolderService implements iFolderService {

    private final iFolderRepo folderRepo;

    public FolderService(iFolderRepo folderRepo) {
        this.folderRepo = folderRepo;
    }

    @Override
    public Folder createFolder(Folder folder) {
        if (folder == null) {
            throw new InvalidFolderException("Folder is required.");
        }
        if (folder.getName() == null || folder.getName().isBlank()) {
            throw new InvalidFolderException("Folder name is required.");
        }
        if (folder.getCreatedAt() == null) {
            throw new InvalidFolderException("Folder creation date is required.");
        }
        return folderRepo.save(folder);
    }
}
