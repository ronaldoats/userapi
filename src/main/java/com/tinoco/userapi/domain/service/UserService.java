package com.tinoco.userapi.domain.service;

import com.tinoco.userapi.domain.dto.UserDto;
import com.tinoco.userapi.domain.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<String> login(String username, String password);

    List<UserDto> findAll();

    UserEntity findByEmail(String email);

    UserEntity createUser(UserEntity user);

    void updateUser(UserEntity user);



}
