package com.scheduler.app.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public final class MailService {


    private static MailService mailServiceInstance;

    @Getter
    @Value("${mail-service.emailid}")
    private String STAFF_SCHEDULER_EMAIL_ID;

    @Getter
    @Value("${mail-service.password}")
    private String STAFF_SCHEDULER_PASSWORD;

    @Getter
    @Value("${mail-service.firstName}")
    private String SENDER_FIRST_NAME;


    @Value("${mail-service.password}")
    private String SENDER_LAST_NAME;

    @Getter
    @Setter
    private ArrayList<String> toMail;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String body;

    private  MailService(){}

    private static MailService getInstance(){
        if(mailServiceInstance == null){
            mailServiceInstance = new MailService();
        }
        return mailServiceInstance;
    }

    private boolean sendMail(String toMail, String subject, String message){

        Properties property = new Properties();
        property.put("mail.smtp.host", "smtp.gmail.com");
        property.put("mail.smtp.port", "465");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.socketFactory.port", "465");
        property.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(property,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(STAFF_SCHEDULER_EMAIL_ID, STAFF_SCHEDULER_PASSWORD);
                    }
                });

        try {

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(STAFF_SCHEDULER_EMAIL_ID));
            mimeMessage.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toMail)
            );
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            System.out.println("Mail Sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
