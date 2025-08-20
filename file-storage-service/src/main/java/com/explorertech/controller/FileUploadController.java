package com.explorertech.controller;

import com.explorertech.service.StorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    private final StorageService storageService;

    public FileUploadController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){

        return ResponseEntity.ok(storageService.uploadFile(file));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<File> downloadFile(@PathVariable String fileName) throws IOException {
        return ResponseEntity.ok(storageService.getFile(fileName));
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity(storageService.deleteFile(fileName), HttpStatus.OK);
    }
}
