package com.codedrive.backend.controllers.dto;

import com.codedrive.backend.domain.Folder;

import java.util.Date;

public class CreateFolderDTO {
    private String name;
    private String description;
    private Date createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Folder map() {
        return new Folder(name, description, createdAt);
    }
}
