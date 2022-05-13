package com.dhruv.translationapi.service;

import com.dhruv.translationapi.exception.TranslationException;
import com.dhruv.translationapi.model.request.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
public class MailInterfaceImpl implements MailInterface{

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(Mail email) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        try {
            helper.setTo(email.getMailTo());
            helper.setSubject(email.getMailSubject());
            helper.setFrom(email.getMailFrom());
            helper.setText(email.getMailContent());
            helper.addAttachment("attachment 1", (File) email.getAttachments().get(0));

            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            throw new TranslationException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}
