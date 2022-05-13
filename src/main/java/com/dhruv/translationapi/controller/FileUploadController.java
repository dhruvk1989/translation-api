package com.dhruv.translationapi.controller;

import com.dhruv.translationapi.exception.TranslationException;
import com.dhruv.translationapi.model.request.Mail;
import com.dhruv.translationapi.service.FileUploadService;
import com.dhruv.translationapi.service.MailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.ws.rs.core.Application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class FileUploadController {

    @Autowired
    FileUploadService uploadService;

    @Autowired
    MailInterface mailInterface;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
        uploadService.uploadFile(file);
        return new ResponseEntity<>("File " + file.getOriginalFilename() + " has been upload successfully.", HttpStatus.ACCEPTED);

    }

    @GetMapping("/email")
    public String sendEmail() throws MessagingException {
        Mail mail = new Mail();
        mail.setMailFrom("workdhruv5@gmail.com");
        mail.setMailTo("dhruvachintya22@gmail.com");
        mail.setMailSubject("Badhiya");
        mail.setMailContent("Learn learn and learn.");
        List files = new ArrayList();
        files.add(new File("/temp/todo.txt"));
        mail.setAttachments(files);
        mailInterface.sendEmail(mail);
        return "Email sent";
    }

}
