package com.codedrive.backend.Interfaces.repositories;

import java.util.List;

import com.codedrive.backend.domain.Folder;

public interface iFolderRepo {
    Folder save(Folder folder);
    List<Folder> getFolders();
}
