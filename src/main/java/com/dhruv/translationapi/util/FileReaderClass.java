package com.dhruv.translationapi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReaderClass {

    public String extractText() throws Exception {
        File file = new File("/home/kyojuro/Desktop/Projects/translation-api/src/main/resources/static/huehue");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String text;
        String result = "";
        while ((text = br.readLine()) != null){
            result += text;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println((new FileReaderClass()).extractText());
    }
}