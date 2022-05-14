package com.dhruv.translationapi.util;

import com.dhruv.translationapi.model.request.Mail;
import com.dhruv.translationapi.service.MailInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MailService {

    @Autowired
    MailInterfaceImpl mailInterface;

    public boolean sendEmail(Mail mail) throws MessagingException {
        mailInterface.sendEmail(mail);
        return true;
    }
}
