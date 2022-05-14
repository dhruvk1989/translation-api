package com.dhruv.translationapi.controller;

import com.dhruv.translationapi.exception.TranslationException;
import com.dhruv.translationapi.model.request.RequestModelUser;
import com.dhruv.translationapi.service.TranslationService;
import com.dhruv.translationapi.util.FileReaderClass;
import com.dhruv.translationapi.util.ObjectFactory;
import com.dhruv.translationapi.model.request.RequestModel;
import com.dhruv.translationapi.model.response.TranslationDataModel;
import com.dhruv.translationapi.util.ObjectFactory2;
import com.dhruv.translationapi.util.PDFExtractor;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RequestMapping("/api")
@RestController
public class TranslationController {

    @Autowired
    private TranslationService service;

    //RestTemplateBuilder builder = new RestTemplateBuilder();

    @Autowired
    private ObjectFactory2 objectFactory;

    @PostMapping("/translate")
    public String translateFile(@RequestParam String file, @RequestBody RequestModelUser requestModelUser){
        return service.translate(file, requestModelUser);
    }


}
