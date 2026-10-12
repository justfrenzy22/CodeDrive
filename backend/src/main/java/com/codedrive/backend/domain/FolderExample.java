//package com.codedrive.backend.domain;
//
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Optional;
//// seoarate domain to entity
//@Document("Folder")
//public class Folder {
//    @Id
//    private String _id;
//    private String name; // Hello World
//    //private String code; // hello_world
//    private String description;
//    //private String color; // # hex color
//
//    /// location in drive
//    //private Optional<ObjectId> parentId;
//    //private String path; // /root/some_folder/
//    //private Number depth; // 4 folders
//
//    /// owner ? yes or no
//    //private ObjectId ownerId;
//    //private ObjectId updatedBy;
//    /// !TODO private ArrayList<ObjectId> sharedWith;
//
//    /// customization other class suggestion
//    //private Boolean isPublic;
//    //private ArrayList<ObjectId> tags;
//    //private Boolean isStarred;
//    //private Boolean deleted;
//    //private Optional<Date> deletedAt;
//
//    // timestamps and audit
//    //private Date createdAt;
//    //private Optional<Date> updatedAt;
//
//    //ObjectId ownerId,
//    // ArrayList<ObjectId> sharedWith
//    //ObjectId updatedBy,
//    public Folder(String _id, String name, String code,
//                  String description, String color, Optional<ObjectId> parentId,
//                  String path, Number depth,
//                  Boolean isPublic, ArrayList<ObjectId> tags,
//                  Boolean isStarred, Boolean deleted,
//                  Date createdAt, Optional<Date> updatedAt) {
//
//        this._id          = _id;
//        this.name         = name;
//
//        //this.code         = code;
//        //this.description  = description;
//        //this.color        = color;
//
//        // location in drive
//        this.parentId     = parentId;
//        this.path         = path;
//        this.depth        = depth;
//
//        // owner & audit
//        //this.ownerId      = ownerId;
//        //this.updatedBy    = updatedBy;
//        //this.sharedWith   = sharedWith;
//
//        // customization
//        this.isPublic     = isPublic;
//        this.tags         = tags;
//        this.isStarred    = isStarred;
//        this.deleted      = deleted;
//
//        // timestamps & audit
//        this.createdAt    = createdAt;
//        this.updatedAt    = updatedAt;   // may be Optional.empty()
//    }
//}
