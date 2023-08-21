package com.hqguestposting.service.impl;

import com.hqguestposting.exception.MailException;
import com.hqguestposting.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final Properties properties;

    @Override
    public String sendMail(String nameFirst, String nameLast, String email, Long budget,
                           String website, String mailMessage) {

        properties.put("mail.smtp.host", "smtp.google.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session mailSession = Session.getInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("hqolma@gmail.com", "qgrmrvhunnoxkbow");
                    }
                });
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("hqolma@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("hqolma@gmail.com"));
            message.setSubject("HQOlma Mail from: " + nameFirst + " " + nameLast);
            message.setText("Email: " + email + "\n"
                    + "Budget: " + budget + "\n"
                    + "Web Site: " + website + "\n"
                    + mailMessage);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new MailException("Can't create mail");
        }
        return "Done!";
    }
}
