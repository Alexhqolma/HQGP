package com.hqguestposting.service;

public interface MailSenderService {
    String sendMail(String nameFirst,
                    String nameLast,
                    String email,
                    Long budget,
                    String website,
                    String mailMessage);
}
