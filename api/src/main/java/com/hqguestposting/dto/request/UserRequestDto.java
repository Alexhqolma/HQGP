package com.hqguestposting.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotEmpty(message = "Full name name can't be empty")
    @Size(min = 2, max = 20, message
            = "Name must be between 2 and 20 characters")
    private String fullName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message
            = "Password must be between 6 and 20 characters")
    private String password;
    private String role;
}
