package com.scheduler.app.util;

import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

@Service
public class MailService {

    private static MailService mailServiceInstance;

    @Getter
    @Value("${mail-service.email}")
    private String STAFF_SCHEDULER_EMAIL_ID;

    @Getter
    @Value("${mail-service.password}")
    private String STAFF_SCHEDULER_PASSWORD;

    @Getter
    @Value("${mail-service.firstname}")
    private String SENDER_FIRST_NAME;


    @Value("${mail-service.lastname}")
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

    private MailService() {
    }

    private static MailService getInstance() {
        if (mailServiceInstance == null) {
            mailServiceInstance = new MailService();
        }
        return mailServiceInstance;
    }

    public boolean sendMail(String toMail, String subject, String message) {
        Properties property = new Properties();
        property.put("mail.smtp.host", "smtp.gmail.com");
        property.put("mail.smtp.port", "465");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.socketFactory.port", "465");
        property.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(property,
                new javax.mail.Authenticator() {
                    @Override
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
            mimeMessage.setContent(message, "text/html; charset=utf-8");
            Transport.send(mimeMessage);
//            System.out.println("Mail Sent");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendMailToEmployee(EmpDetailPOJO employee, String subject, String message) {
        String html = "";
        FileResourceUtils htmlTemplate = new FileResourceUtils();
        String htmlTemplatePath = "templates/EmailTemplate.HTML";
        InputStream is = htmlTemplate.getFileFromResourceAsStream(htmlTemplatePath);
        html = htmlTemplate.getStringInputStream(is);
        html = html.replaceFirst("FirstName", employee.getFirstName());
        html = html.replaceFirst("LastName", employee.getLastName());
        html = html.replaceFirst("MessageContent", message);
        return sendMail(employee.getEmailId(), subject, html);
    }
}
