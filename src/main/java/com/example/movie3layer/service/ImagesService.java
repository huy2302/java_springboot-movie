package com.example.movie3layer.service;

import com.example.movie3layer.model.Images;
import com.example.movie3layer.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;

@Service
public class ImagesService {
    @Autowired
    private ImagesRepository imagesRepository;

    public Images SaveImages (MultipartFile image, String imageName) throws IOException {
        byte[] imageData = image.getBytes();

        try {
            Blob imageDataBlob = new SerialBlob(imageData);
            Images savedImage = new Images(1, imageData, imageName);

            return imagesRepository.save(savedImage);
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}
