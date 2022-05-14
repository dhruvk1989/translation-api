package com.dhruv.translationapi.service;

import com.dhruv.translationapi.exception.TranslationException;
import com.dhruv.translationapi.model.request.Mail;
import com.dhruv.translationapi.model.request.RequestModel;
import com.dhruv.translationapi.model.request.RequestModelUser;
import com.dhruv.translationapi.model.response.TranslationDataModel;
import com.dhruv.translationapi.util.MailService;
import com.dhruv.translationapi.util.ObjectFactory2;
import com.dhruv.translationapi.util.PDFExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranslationService {

    @Autowired
    ObjectFactory2 objectFactory;

    @Autowired
    private PDFExtractor pdfExtractor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailService mailService;

    public String translate(String file, RequestModelUser requestModelUser){
        File fileObj = new File("C:\\Temp\\" + file);
        String q = "";
        try {
            q = pdfExtractor.readPdf(fileObj);
        } catch (Exception e) {
            throw new TranslationException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        RequestModel requestModel =
                new RequestModel(q, requestModelUser.getSource(), requestModelUser.getTarget(),
                        requestModelUser.getFormat());

        URI uri = UriComponentsBuilder.fromUriString(objectFactory.getBASE_URL())
                .queryParam("key", objectFactory.getKEY())
                .build().toUri();

        TranslationDataModel translationDataModel =
                restTemplate.postForObject(uri, requestModel, TranslationDataModel.class);

        String translatedText = translationDataModel.getData().getTranslations()[0].getTranslatedText();

        File newFile;
        try {
            newFile = pdfExtractor.writeToTextFile(translatedText);
        } catch (IOException e) {
            throw new TranslationException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        Mail mail = new Mail();
        mail.setMailFrom("workdhruv5@gmail.com");
        mail.setMailTo("dhruv.kushwah@intellinum.com");
        mail.setMailSubject("Translation file request: " + newFile.getName());
        mail.setMailContent("The file sent by user 101 with the file name " + newFile.getName() + " has been submitted to you.");
        System.out.println("hello");
        List<File> files = new ArrayList<>();
        File newFilefile = new File("C:\\Temp\\" + newFile.getName());
        System.out.println(newFilefile.getAbsolutePath());
        files.add(newFilefile);
        mail.setAttachments(files);

        boolean emailSent = false;

        try {
            emailSent = mailService.sendEmail(mail);
        } catch (MessagingException e) {
            throw new TranslationException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        if(emailSent)
            return "Email has been sent to the translator. You will receive your file soon.";
        else
            return "Email was not sent.";
    }

}
