package com.codedrive.backend.controllers.dto;

import java.util.Date;

import com.codedrive.backend.domain.Folder;

public class FolderDTO {
    private String _id;
    private String name;
    private String description;
    private Date createdAt;

    public FolderDTO(String _id, String name, String description, Date createdAt) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;

    }

    public static FolderDTO from(Folder fldr) {
        return new FolderDTO(
                fldr.getId(),
                fldr.getName(),
                fldr.getDescription(),
                fldr.getCreatedAt());
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
