package com.cipherLab.user.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
