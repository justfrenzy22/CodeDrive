package com.codedrive.backend.controllers;


import com.codedrive.backend.controllers.dto.FolderDTO;
import com.codedrive.backend.services.FolderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/folder")
public class FolderController {

    private com.codedrive.backend.interfaces.iFolderService folderService ;

    @PostMapping
    public FolderDTO createFolder(@RequestBody FolderDTO folderDTO) {
        // send the folder to the iFolderService
        return null;
    }

}
