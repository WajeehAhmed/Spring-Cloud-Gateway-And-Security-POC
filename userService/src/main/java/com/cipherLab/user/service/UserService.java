package com.cipherLab.user.service;

import com.cipherLab.user.dto.UserDto;
import com.cipherLab.user.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public void createUser(UserDto userDto);
    public UserResponseDto getUserById(Long id);

    UserDto getUserDetailsByEmail(String userName);
}
