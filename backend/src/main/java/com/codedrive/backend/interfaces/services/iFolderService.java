package com.codedrive.backend.Interfaces.services;

import java.util.List;

import com.codedrive.backend.domain.Folder;

public interface iFolderService {
    Folder createFolder(Folder folder);
    List<Folder> getFolders();
}
