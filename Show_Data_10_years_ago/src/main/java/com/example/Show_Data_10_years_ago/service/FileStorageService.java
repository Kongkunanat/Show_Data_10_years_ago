package com.example.Show_Data_10_years_ago.service;

import com.example.Show_Data_10_years_ago.model.Image;
import com.example.Show_Data_10_years_ago.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FileStorageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image Images = new Image(fileName, file.getContentType(), file.getBytes());

        return imageRepository.save(Images);
    }


    public Image getFile(Integer id) {
        return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllFiles() {
        return imageRepository.findAll().stream();
    }

    public void deleteById(Integer id) {
         imageRepository.deleteById(id);
    }
}
