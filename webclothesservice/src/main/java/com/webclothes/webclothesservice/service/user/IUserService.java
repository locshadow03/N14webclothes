package com.webclothes.webclothesservice.service.user;

import com.webclothes.webclothesservice.dto.UserDto;
import com.webclothes.webclothesservice.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserDto register(UserDto userDto);

    UserDto login(UserDto userDto);

    void deleteUser(Long id) throws UserNotFoundException;

    UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException;

    void updateRole(Long id, String role) throws UserNotFoundException;

    Optional<UserDto> getUserById(Long userId);

    List<UserDto> allUser();

    Long getCountUsersToday();

    Long getCountUsersThisMonth();

    Long getCountUsersThisYear();

    Long getCountUsersAllTime();
}
