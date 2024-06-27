package com.example.Show_Data_10_years_ago.controller;


import com.example.Show_Data_10_years_ago.ImageDTO;
import com.example.Show_Data_10_years_ago.model.Image;
import com.example.Show_Data_10_years_ago.repository.ImageRepository;
import com.example.Show_Data_10_years_ago.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")


public class ImageController {
    @Autowired
     ImageRepository imageRepository;
    @Autowired
     FileStorageService fileStorageService;

    @GetMapping("/getAllImage")
    public ResponseEntity<List<Image>> getAllImages() {
        try {
            List<Image> imageList = fileStorageService.getAllFiles().toList();

            if (imageList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(imageList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getImageById/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Integer id) {
        Image  image = fileStorageService.getFile(id);
        if (image!= null) {
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addImage")
    public ResponseEntity<Image> addImage(@RequestParam("file") MultipartFile file) {
        try {
            Image imageObj  = fileStorageService.store(file);
            return new ResponseEntity<>(imageObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteImageById/{id}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable Integer id) {
        try {
            fileStorageService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
