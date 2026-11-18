package com.codedrive.backend.domain;

import java.util.Date;

public class Folder {
    private final String _id;
    private final String name;
    private final String description;
    private final Date createdAt;

    public Folder(String _id, String name, String description, Date createdAt) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Folder(String name, String description, Date createdAt) {
        this(null, name, description, createdAt);
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
