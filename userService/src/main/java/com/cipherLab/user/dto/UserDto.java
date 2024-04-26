package com.cipherLab.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = -953297098295050686L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userId;
}
