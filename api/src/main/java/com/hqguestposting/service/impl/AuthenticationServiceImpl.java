package com.hqguestposting.service.impl;

import com.hqguestposting.dto.mapper.UserMapper;
import com.hqguestposting.dto.request.UserRequestDto;
import com.hqguestposting.model.User;
import com.hqguestposting.repository.UserRepository;
import com.hqguestposting.dto.response.AuthenticationResponse;
import com.hqguestposting.service.AuthenticationService;
import com.hqguestposting.security.jwt.JwtTokenProvider;
import com.hqguestposting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper userMapper;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse login(UserRequestDto userRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequestDto.getEmail(),
                        userRequestDto.getPassword()
                )
        );
        var user = userRepository.findByEmail(userRequestDto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Can't find user by email "
                                + userRequestDto.getEmail()));
        var userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        var jwtToken = jwtTokenProvider.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).role(user.getRole()).build();
    }

    public AuthenticationResponse register(UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        userService.create(user);
        var userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .roles("USER")
                .build();
        var jwtToken = jwtTokenProvider.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
