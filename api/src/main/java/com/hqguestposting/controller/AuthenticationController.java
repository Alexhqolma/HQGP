package com.hqguestposting.controller;

import com.hqguestposting.dto.request.UserRequestDto;
import com.hqguestposting.dto.response.AuthenticationResponse;
import com.hqguestposting.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok((authenticationService.login(userRequestDto)));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> registration(
            @RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok((authenticationService.register(userRequestDto)));
    }
}
