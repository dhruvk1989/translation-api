package com.dhruv.translationapi.service;

import com.dhruv.translationapi.exception.TranslationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    public void uploadFile(MultipartFile file){
        try {
            file.transferTo(new File("C:\\Temp\\" + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new TranslationException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
