package com.hqguestposting.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String role;
}
