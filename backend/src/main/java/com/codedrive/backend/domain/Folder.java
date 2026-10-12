package com.codedrive.backend.domain;

import java.util.Date;

public class Folder {
    private String id;
    private String name;
    private String description;
    private Date createdAt;

    public Folder(String id, String name, String description, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Folder(String name, String description, Date createdAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
}
