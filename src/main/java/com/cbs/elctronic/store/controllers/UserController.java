package com.cbs.elctronic.store.controllers;

import com.cbs.elctronic.store.dtos.ApiResponse;
import com.cbs.elctronic.store.dtos.PageableResponse;
import com.cbs.elctronic.store.dtos.UserDto;
import com.cbs.elctronic.store.services.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId)
    {
        UserDto updatedUser = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId)
    {
        userService.deleteUser(userId);
        ApiResponse apiResponse = ApiResponse.builder().message("User Deleted Successfully.").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(@RequestParam(value = "pageNumber",defaultValue ="0",required = false) int pageNumber,
                                                        @RequestParam(value = "pageSize",defaultValue ="5",required = false) int pageSize,
                                                        @RequestParam(value = "sortDir",defaultValue ="asc",required = false) String sortDir,
                                                        @RequestParam(value = "sortBy",defaultValue ="name",required = false) String sortBy)
    {
        PageableResponse<UserDto> allUsers = userService.getAllUsers(pageNumber,pageSize,sortBy,sortDir);
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
