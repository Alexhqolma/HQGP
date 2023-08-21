package com.hqguestposting.security;

import com.hqguestposting.dto.mapper.UserMapper;
import com.hqguestposting.dto.request.UserRequestDto;
import com.hqguestposting.dto.response.UserResponseDto;
import com.hqguestposting.model.User;
import com.hqguestposting.repository.UserRepository;
import com.hqguestposting.security.jwt.JwtService;
import com.hqguestposting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserMapper userMapper;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse login(UserResponseDto userResponseDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userResponseDto.getEmail(),
                        userResponseDto.getPassword()
                )
        );
        var user = userRepository.findByEmail(userResponseDto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Can't find user by email "
                                + userResponseDto.getEmail()));
        var userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).role(user.getRole()).build();
    }

    public AuthenticationResponse registration(UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        userService.create(user);
        var userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .roles("USER")
                .build();
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
