package com.server.mechacal.controllers;

import java.io.InputStream;
import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.server.mechacal.services.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) 
    {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException 
    {
        String fileId = fileService.uploadFile(file);
        return new ResponseEntity<>("File uploaded with ID: " + fileId, HttpStatus.OK);
    }

    @GetMapping(value = "/{fileId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public void serveFileHandler(@PathVariable String fileId, HttpServletResponse response) throws IOException 
    {
        InputStream resourceFile = fileService.getResourceFile(fileId);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}
