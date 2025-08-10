package com.cbs.elctronic.store.controllers;

import com.cbs.elctronic.store.dtos.ApiResponse;
import com.cbs.elctronic.store.dtos.ImageResponse;
import com.cbs.elctronic.store.dtos.PageableResponse;
import com.cbs.elctronic.store.dtos.UserDto;
import com.cbs.elctronic.store.services.FileService;
import com.cbs.elctronic.store.services.UserService;
import com.cbs.elctronic.store.services.impl.FileServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

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

    @GetMapping("/email/{userEmail}")
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

    // upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@PathVariable String userId, @RequestParam MultipartFile image) throws IOException {

        String imageName = fileService.uploadFile(image, imageUploadPath);

        UserDto userById = userService.getUserById(userId);
        userById.setImageName(imageName);
        UserDto updatedUser = userService.updateUser(userById, userId);

        ImageResponse imageResponse= ImageResponse.builder().message("Image uploaded successfully").imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {

        UserDto user = userService.getUserById(userId);
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());


    }
}
