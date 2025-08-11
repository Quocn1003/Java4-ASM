package com.slide5.Service;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailService {
    public void sendEmails(String[] emails, String subject, String html) throws MessagingException {
        for (String to : emails) {
            sendHtmlMail(to, subject, html);
        }
    }

    private final String username = "quocn1003@gmail.com";
    private final String password = "anlx igym hods udnm"; // App Password của Gmail

    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendTextMail(String to, String subject, String content) throws MessagingException {
        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);
        Transport.send(message);
    }

    public void sendHtmlMail(String to, String subject, String html) throws MessagingException {
        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(html, "text/html; charset=UTF-8");
        Transport.send(message);
    }
}
