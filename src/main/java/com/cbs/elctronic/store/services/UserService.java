package com.cbs.elctronic.store.services;

import com.cbs.elctronic.store.dtos.PageableResponse;
import com.cbs.elctronic.store.dtos.UserDto;
import com.cbs.elctronic.store.entities.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);


}
