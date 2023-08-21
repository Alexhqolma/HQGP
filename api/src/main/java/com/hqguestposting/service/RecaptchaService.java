package com.hqguestposting.service;

import com.hqguestposting.model.RecaptchaResponse;

public interface RecaptchaService {
    RecaptchaResponse validateToken(String recaptcha);
}
