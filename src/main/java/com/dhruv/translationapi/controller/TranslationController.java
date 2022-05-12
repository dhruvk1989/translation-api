package com.dhruv.translationapi.controller;

import com.dhruv.translationapi.util.FileReaderClass;
import com.dhruv.translationapi.util.ObjectFactory;
import com.dhruv.translationapi.model.request.RequestModel;
import com.dhruv.translationapi.model.response.TranslationDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class TranslationController {

    @Autowired
    private RestTemplate restTemplate;
    RestTemplateBuilder builder = new RestTemplateBuilder();

    @Autowired
    private ObjectFactory objectFactory;

    FileReaderClass reader = new FileReaderClass();

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("getStuff")
    public void translate() throws Exception {
        RequestModel request = new RequestModel((new FileReaderClass()).extractText(), "ar", "en", "text");
        URI uri = UriComponentsBuilder.fromUriString(objectFactory.getBASE_URL())
                .queryParam("key", objectFactory.getKEY())
                .build().toUri();
        TranslationDataModel translationDataModel = restTemplate.postForObject(uri, request, TranslationDataModel.class);
        System.out.println(translationDataModel.getData().getTranslations()[0].getTranslatedText());
    }


}
