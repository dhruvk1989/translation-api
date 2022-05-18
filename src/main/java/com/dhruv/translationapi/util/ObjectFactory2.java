package com.dhruv.translationapi.util;

import org.springframework.stereotype.Component;

@Component
public class ObjectFactory2 {
    private final String BASE_URL = "https://translation.googleapis.com/language/translate/v2";
    private final String KEY = "AIzaSyCgi6TwxoG4pFrhqjGwYeHtrux4rjoqNM0";

    public String getKEY() {
        return KEY;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }
}
