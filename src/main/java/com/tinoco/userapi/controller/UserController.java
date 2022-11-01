package com.tinoco.userapi.controller;

import com.tinoco.userapi.domain.models.UserEntity;
import com.tinoco.userapi.domain.dto.UserCreatedDto;
import com.tinoco.userapi.domain.dto.UserDto;
import com.tinoco.userapi.domain.service.UserService;
import com.tinoco.userapi.setting.ApiPrefixController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(value = "/api/users")
    ResponseEntity<Object> findUsers() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", "Lista de usuarios");
        body.put("body", this.userService.findAll());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @PostMapping(value = "/api/users")
    ResponseEntity<UserCreatedDto> addUser(@Valid @RequestBody UserDto userDto) {
        UserEntity usr = userService.createUser(userDto.toUser());
        return ResponseEntity.ok(usr.toUserCreatedDto());
    }
}