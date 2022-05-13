package com.dhruv.translationapi.service;

import com.dhruv.translationapi.model.request.Mail;

import javax.mail.MessagingException;

public interface MailInterface {
    public void sendEmail(Mail email) throws MessagingException;
}
