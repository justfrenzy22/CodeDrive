package com.codedrive.backend.repositories.entities;

import com.codedrive.backend.domain.Folder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Folder")
public class FolderEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private Date createdAt;

    public FolderEntity() {
    }

    public static FolderEntity mapFrom(Folder folder) {
        FolderEntity entity = new FolderEntity();
        entity.id = folder.getId();
        entity.name = folder.getName();
        entity.description = folder.getDescription();
        entity.createdAt = folder.getCreatedAt();
        return entity;
    }

    public Folder mapTo() {
        return new Folder(id, name, description, createdAt);
    }
}
