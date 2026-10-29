package com.codedrive.backend.controllers;


import com.codedrive.backend.controllers.dto.CreateFolderDTO;
import com.codedrive.backend.exceptions.FolderPersistenceException;
import com.codedrive.backend.exceptions.InvalidFolderException;
import com.codedrive.backend.Interfaces.services.iFolderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/folder")
public class FolderController {
    private final iFolderService folderService;

    public FolderController(iFolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public ResponseEntity<String> createFolder(@RequestBody(required = false) CreateFolderDTO folderDTO) {
        try {
            folderService.createFolder(folderDTO == null ? null : folderDTO.map());
            return ResponseEntity.status(HttpStatus.CREATED).body("The Folder was successfully created");
        } catch (InvalidFolderException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (FolderPersistenceException exception) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exception.getMessage());
        }
    }
}
