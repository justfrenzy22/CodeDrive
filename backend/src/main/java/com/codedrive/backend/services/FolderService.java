package com.codedrive.backend.services;

import com.codedrive.backend.domain.Folder;

public class FolderService {

    private com.codedrive.backend.interfaces.iFolderRepo folderRepo;

    public FolderService(com.codedrive.backend.interfaces.iFolderRepo folderRepo) {
        this.folderRepo = folderRepo;
    }

    public Folder CreateFolder(Folder folder) { // DTO or Folder?
        // if checks


        ///Save the folder after the checks
        //return folderRepo.save(folder);
        return null;
    }
}
