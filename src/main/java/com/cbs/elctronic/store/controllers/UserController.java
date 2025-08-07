package com.cbs.elctronic.store.controllers;

import com.cbs.elctronic.store.dtos.ApiResponse;
import com.cbs.elctronic.store.dtos.UserDto;
import com.cbs.elctronic.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("{/userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId)
    {
        UserDto updatedUser = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{/userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId)
    {
        userService.deleteUser(userId);
        ApiResponse apiResponse = ApiResponse.builder().message("User Deleted Successfully.").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId)
    {
        UserDto userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String userEmail)
    {
        UserDto userByEmail = userService.getUserByEmail(userEmail);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword)
    {
        List<UserDto> userDtos = userService.searchUser(keyword);
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }
}
