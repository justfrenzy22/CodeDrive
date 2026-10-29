package com.codedrive.backend.domain;

import java.util.Date;

public class Folder {
    private final String id;
    private final String name;
    private final String description;
    private final Date createdAt;

    public Folder(String id, String name, String description, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Folder(String name, String description, Date createdAt) {
        this(null, name, description, createdAt);
    }

    public String getId() {
        return id;
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
