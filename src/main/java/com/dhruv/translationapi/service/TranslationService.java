package com.dhruv.translationapi.service;

import com.dhruv.translationapi.exception.TranslationException;
import com.dhruv.translationapi.model.request.RequestModel;
import com.dhruv.translationapi.model.request.RequestModelUser;
import com.dhruv.translationapi.model.response.TranslationDataModel;
import com.dhruv.translationapi.util.ObjectFactory2;
import com.dhruv.translationapi.util.PDFExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@Service
public class TranslationService {

    @Autowired
    ObjectFactory2 objectFactory;

    @Autowired
    private PDFExtractor pdfExtractor;

    @Autowired
    private RestTemplate restTemplate;

    public void translate(String file, RequestModelUser requestModelUser){
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



    }

}
