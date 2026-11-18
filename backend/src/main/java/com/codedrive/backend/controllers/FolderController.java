package com.codedrive.backend.controllers;

import com.codedrive.backend.controllers.dto.CreateFolderDTO;
import com.codedrive.backend.controllers.dto.FolderDTO;
import com.codedrive.backend.Interfaces.services.iFolderService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    // POST /api/folder -> create a folder
    @PostMapping
    public ResponseEntity<String> createFolder(@RequestBody(required = false) CreateFolderDTO folderDTO) {
        folderService.createFolder(folderDTO == null ? null : folderDTO.map());
        return ResponseEntity.status(HttpStatus.CREATED).body("The Folder was successfully created");
    }

    // GET api/folder -> gets all folders (for user in the future)
    @GetMapping
    public ResponseEntity<List<FolderDTO>> getFolders() {
        List<FolderDTO> folders = folderService.getFolders()
                .stream()
                .map(FolderDTO::from)
                .toList();
        return ResponseEntity.ok(folders);
    }
}
