package com.codedrive.backend.repositories;

import com.codedrive.backend.domain.Folder;
import com.codedrive.backend.repositories.entities.FolderEntity;
import com.codedrive.backend.exceptions.FolderPersistenceException;
import com.codedrive.backend.exceptions.InvalidFolderException;
import com.codedrive.backend.Interfaces.repositories.iFolderRepo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class FolderRepo implements iFolderRepo {
    private final MongoRepo mongoRepo;

    public FolderRepo(MongoRepo mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public Folder save(Folder folder) {
        if (folder == null) {
            throw new InvalidFolderException("Folder is required.");
        }
        FolderEntity entity = FolderEntity.mapFrom(folder);
        try {
            FolderEntity savedEntity = mongoRepo.getMongoTemplate().save(entity);
            return savedEntity.mapTo();
        } catch (DataAccessException exception) {
            throw new FolderPersistenceException("Folder could not be saved. Please try again later.", exception);
        }
    }
}
