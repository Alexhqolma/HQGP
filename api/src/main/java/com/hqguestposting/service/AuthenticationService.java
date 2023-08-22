package com.hqguestposting.service;

import com.hqguestposting.dto.request.UserRequestDto;
import com.hqguestposting.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(UserRequestDto userRequestDto);

    AuthenticationResponse register(UserRequestDto userRequestDto);
}
