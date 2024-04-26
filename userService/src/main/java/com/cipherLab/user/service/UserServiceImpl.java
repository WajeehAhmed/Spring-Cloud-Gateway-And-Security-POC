package com.cipherLab.user.service;

import com.cipherLab.user.Entity.UserEntity;
import com.cipherLab.user.Exception.UserNotFoundException;
import com.cipherLab.user.Repository.UserRepository;
import com.cipherLab.user.dto.UserDto;
import com.cipherLab.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void createUser(UserDto userDto) {
        var userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
    }

    public UserResponseDto getUserById(Long id) {
        var user = userRepository.findById(id);
        var userResponse = new UserResponseDto();
        if (user.isEmpty()) throw new UserNotFoundException(id);
        else {
            var userObj = user.get();
            userResponse.setUserId(userObj.getUserId());
            userResponse.setEmail(userObj.getEmail());
            userResponse.setFirstName(userObj.getFirstName());
            userResponse.setLastName(userObj.getLastName());
        }
        return userResponse;
    }


    public UserDto getUserDetailsByEmail(String userName) {
        var userEntity = userRepository.findByEmail(userName);
        if (userEntity == null) throw new UsernameNotFoundException(userName);
        return new UserDto(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getEncryptedPassword(), userEntity.getUserId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByEmail(username);
        if (userEntity == null) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true,
                true, true, true, new ArrayList<>());
    }
}
