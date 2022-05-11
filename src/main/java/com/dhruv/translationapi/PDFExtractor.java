package com.dhruv.translationapi;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PDFExtractor {

    public static void main(String[] args) throws IOException, TikaException {
        File stream = new File("/home/kyojuro/Desktop/Projects/translation-api/output");
        File file = new File("/home/kyojuro/Desktop/Java/Core Java/31-03.txt");
        Tika tika = new Tika();
        System.out.println(tika.detect(file));
        String result = tika.parseToString(file);
        System.out.println("Content : " + result);
        FileUtils.write(stream, result, StandardCharsets.UTF_16);
    }

}
