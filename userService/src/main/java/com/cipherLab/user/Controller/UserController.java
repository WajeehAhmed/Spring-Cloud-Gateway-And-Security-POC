package com.cipherLab.user.Controller;

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

//    @GetMapping("/users/byCellNumber/{cellNumber}")
//    public ResponseEntity<List<UserEntity>> getBookByCellNumber(@PathVariable String cellNumber) {
//        var user = userRepository.findByCellNumber(cellNumber);
//        if (user.isEmpty()) throw new UserNotFoundException(cellNumber);
//        else return ResponseEntity.status(HttpStatus.OK).body(user);
//    }

//    @PutMapping("/users/{id}")
//    public ResponseEntity<ResponseDto> updateUserById(@RequestBody UserEntity newUserEntity, @PathVariable Long id) {
//        Optional<UserEntity> userOptional = userRepository.findById(id);
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException(id);
//        } else {
//            var oldUser = userOptional.get();
//            oldUser.setName(newUserEntity.getName() != null ? newUserEntity.getName() : oldUser.getName());
//            oldUser.setAge(newUserEntity.getAge() != null ? newUserEntity.getAge() : oldUser.getAge());
//            newUserEntity.setLastChangeTs(LocalDateTime.now());
//            userRepository.save(oldUser);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, UserConstant.MESSAGE_200));
//        }
//    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED, UserConstant.MESSAGE_201));
    }

//    @DeleteMapping("/users/{id}")
//    ResponseEntity<ResponseDto> deleteUser(@PathVariable Long id) {
//        var user = userRepository.findById(id);
//        if (user.isEmpty()) throw new UserNotFoundException(id);
//        else {
//            userRepository.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, UserConstant.MESSAGE_200));
//        }
//    }
}
