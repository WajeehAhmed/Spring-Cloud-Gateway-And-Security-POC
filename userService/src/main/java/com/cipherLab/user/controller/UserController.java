package com.cipherLab.user.controller;

import com.cipherLab.user.constants.UserConstant;
import com.cipherLab.user.dto.ResponseDto;
import com.cipherLab.user.dto.UserDto;
import com.cipherLab.user.dto.UserResponseDto;
import com.cipherLab.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        var userResponse = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ResponseDto> updateUserById(@RequestBody UserDto userDto, @PathVariable Long id) {
        userService.updateUser(userDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, UserConstant.MESSAGE_200));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED, UserConstant.MESSAGE_201));
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<ResponseDto> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, UserConstant.MESSAGE_200));
    }
}
