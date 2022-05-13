package com.dhruv.translationapi.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;


import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class PDFExtractor {

    public void generatePdf() throws IOException, DocumentException, TikaException {
        File file = new File("/temp/newFile.txt");
        if(file.getName().endsWith(".txt")){
            if(convertTextfileToPDF(file)){
                System.out.println("created");
            }
        }
    }

    public static boolean convertTextfileToPDF(File file)
    {
        FileInputStream iStream=null;
        DataInputStream in=null;
        InputStreamReader is=null;
        BufferedReader br=null;
        try {
            Document pdfDoc = new Document();

            String text_file_name =file.getParent()+"\\"+"texttpPDF.pdf";
            PdfWriter writer=PdfWriter.getInstance(pdfDoc,new FileOutputStream(text_file_name));
            pdfDoc.open();
            pdfDoc.setMarginMirroring(true);
            pdfDoc.setMargins(36, 72, 108,180);
            pdfDoc.topMargin();
            Font normal_font = new Font();
            Font bold_font = new Font();
            bold_font.setStyle(Font.BOLD);
            bold_font.setSize(10);
            normal_font.setStyle(Font.NORMAL);
            normal_font.setSize(10);
            pdfDoc.add(new Paragraph("\n"));
            if(file.exists())
            {
                iStream = new FileInputStream(file);
                in = new DataInputStream(iStream);
                is=new InputStreamReader(in);
                br = new BufferedReader(is);
                String strLine;
                while ((strLine = br.readLine()) != null)   {
                    Paragraph para =new Paragraph(strLine+"\n",normal_font);
                    para.setAlignment(Element.ALIGN_JUSTIFIED);
                    pdfDoc.add(para);
                }
            }
            else
            {
                System.out.println("file does not exist");
                return false;
            }
            pdfDoc.close();
        }

        catch(Exception e)
        {
            System.out.println("FileUtility.covertEmailToPDF(): exception = " + e.getMessage());
        }
        finally
        {

            try {
                if(br!=null)
                {
                    br.close();
                }
                if(is!=null)
                {
                    is.close();
                }
                if(in!=null)
                {
                    in.close();
                }
                if(iStream!=null)
                {
                    iStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return true;
    }

    public String readPdf(File fileObj) throws IOException, TikaException {
        //File stream = new File("/temp/newFile.txt");
        Tika tika = new Tika();
        //System.out.println(tika.detect(file));
        String result = tika.parseToString(fileObj);
        //System.out.println("Content : " + result);
        //FileUtils.write(stream, result, StandardCharsets.UTF_16);
        return result;
    }

    public File writeToTextFile(String text) throws IOException {
        String fileName = "dhruv" + Math.random();
        File stream = new File("/temp/" + fileName);
        FileUtils.write(stream, text, StandardCharsets.UTF_16);
        return new File(fileName);
    }

    public static void main(String[] args) throws IOException, TikaException, DocumentException {
        PDFExtractor extractor = new PDFExtractor();
        //extractor.readPdf();
        extractor.generatePdf();
    }

}
