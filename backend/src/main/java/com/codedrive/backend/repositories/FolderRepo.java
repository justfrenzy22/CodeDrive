package com.codedrive.backend.repositories;

import com.codedrive.backend.domain.Folder;
import com.codedrive.backend.repositories.entities.FolderEntity;
import com.codedrive.backend.exceptions.FolderPersistenceException;
import com.codedrive.backend.exceptions.InvalidFolderException;
import com.codedrive.backend.Interfaces.repositories.iFolderRepo;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
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
            Folder savedEntity = mongoRepo.getMongoTemplate()
                    .save(entity)
                    .mapTo();
            return savedEntity;
        } catch (DataAccessException exc) {
            throw new FolderPersistenceException("Folder could not be saved. Please try again later.", exc);
        }
    }

    @Override
    public List<Folder> getFolders() {
        try {
            return mongoRepo.getMongoTemplate()
                    .find(new Query().with(Sort.by(Sort.Direction.DESC, "createdAt")), FolderEntity.class)
                    .stream()
                    .map(FolderEntity::mapTo)
                    .toList();
        } catch (DataAccessException exc) {
            throw new FolderPersistenceException("Folders could not be loaded. Please try again later. ", exc);
        }
    }
}
