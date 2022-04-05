package com.scheduler.app.admin.util;

import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Mail service.
 * This service is responsible for generating and sending the relevant emails to the employees that are added to the system.
 * This service is most commonly invoked during the creation of a new Employee.
 */
@Service
public class MailService {

    // Create a new instance of the Mail Service
    private static MailService mailServiceInstance;

    //Obtain the Email ID of the Staff Scheduler Application
    @Getter
    @Value("${mail-service.email}")
    private String STAFF_SCHEDULER_EMAIL_ID;

    //Obtain the Password of the Staff Scheduler Application Email ID
    @Getter
    @Value("${mail-service.password}")
    private String STAFF_SCHEDULER_PASSWORD;

    //Obtain the Name of the Staff Scheduler Application for the email.
    @Getter
    @Value("${mail-service.firstname}")
    private String SENDER_FIRST_NAME;

    //Obtain the Last Name of the Staff Scheduler Application Team for the email.
    @Value("${mail-service.lastname}")
    private String SENDER_LAST_NAME;

    private MailService() {
    }
//
//    private static MailService getInstance() {
//        if (mailServiceInstance == null) {
//            mailServiceInstance = new MailService();
//        }
//        return mailServiceInstance;
//    }

    /**
     * Send mail method generates, prepares, and sends the emails to the values provided.
     *
     * @param toMail  the address to which the email has to be sent
     * @param subject the subject of the email
     * @param message the message of the email
     * @return whether the email was sent or not
     */
    public boolean sendMail(String toMail, String subject, String message) {

        //Set all the information for the email into the properties.
        Properties property = new Properties();
        property.put("mail.smtp.host", "smtp.gmail.com");
        property.put("mail.smtp.port", "465");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.socketFactory.port", "465");
        property.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        property.put("mail.smtp.ssl.checkserveridentity", "true");

        //Create a new session with the given properties.
        Session session = Session.getInstance(property,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(STAFF_SCHEDULER_EMAIL_ID, STAFF_SCHEDULER_PASSWORD);
                    }
                });
        try {
            //Create and populate a new Message object in the session.
            Message mimeMessage = new MimeMessage(session);
            //Add the sender's email
            mimeMessage.setFrom(new InternetAddress(STAFF_SCHEDULER_EMAIL_ID));
            //Add Recipient
            mimeMessage.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toMail)
            );
            //Add the Subject
            mimeMessage.setSubject(subject);
            //Add the message
            mimeMessage.setContent(message, "text/html; charset=utf-8");
            Transport.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Send mail to employee with the given values.
     *
     * @param employee the employee to whom the email has to be sent.
     * @param subject  the subject of the email
     * @param message  the message of the email
     * @return whether the email was successfully sent or not.
     */
    public boolean sendMailToEmployee(EmpDetailPOJO employee, String subject, String message) {

        String html = "";
        //Get the Template for the email
        FileResourceUtils htmlTemplate = new FileResourceUtils();
        String htmlTemplatePath = "templates/EmailTemplate.HTML";
        InputStream is = htmlTemplate.getFileFromResourceAsStream(htmlTemplatePath);
        html = htmlTemplate.getStringInputStream(is);

        //Add content to the email template.
        html = html.replaceFirst("FirstName", employee.getFirstName());
        html = html.replaceFirst("LastName", employee.getLastName());
        html = html.replaceFirst("MessageContent", message);

        //Send the email by calling the sendMail method.
        return sendMail(employee.getEmailId(), subject, html);
    }
}
