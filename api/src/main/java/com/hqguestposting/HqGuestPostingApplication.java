package com.hqguestposting;

import com.hqguestposting.security.RecaptchaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RecaptchaProperties.class)
public class HqGuestPostingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HqGuestPostingApplication.class, args);
    }

}
