package com.tinoco.userapi.controller;

import com.tinoco.userapi.domain.dto.TokenDto;
import com.tinoco.userapi.domain.dto.UserDto;
import com.tinoco.userapi.domain.service.UserService;
import com.tinoco.userapi.error.EmailException;
import com.tinoco.userapi.setting.ApiPrefixController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@ApiPrefixController
@Validated
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<TokenDto> signin(@RequestBody UserDto user) {
        Optional<String> token = this.userService.login(user.getEmail(), user.getPassword());
        token.orElseThrow(() -> new EmailException("Error al intentar ingresar"));
        return ResponseEntity.ok(new TokenDto().setToken(token.get()));
    }
}
