package com.tinoco.userapi.domain.service.implement;

import com.tinoco.userapi.domain.dto.UserDto;
import com.tinoco.userapi.domain.models.UserEntity;
import com.tinoco.userapi.domain.repository.RoleRepository;
import com.tinoco.userapi.domain.repository.UserRepository;
import com.tinoco.userapi.domain.service.UserService;
import com.tinoco.userapi.error.EmailException;
import com.tinoco.userapi.security.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private Jwt jwt;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           Jwt jwt,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager amanager
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwt = jwt;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = amanager;
    }


    @Override
    public Optional<String> login(String username, String password) {
        Optional<String> token = Optional.empty();
        Optional<UserEntity> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            try {
                //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = Optional.of(jwt.createToken(username, user.get().getRoles()));
            } catch (Exception e) {
                new EmailException("Fallo la autenticacion ," + e.getMessage());
                //e.printStackTrace();
            }
        }
        return token;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> userDtos.add(userEntity.toUserDto()));
        return userDtos;
    }

    @Override
    public UserEntity findByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        user.orElseThrow();
        return user.get();
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setActive(true);
        user.setRoles(Arrays.asList(this.roleRepository.findByRoleName("admin").get()));
        user.setToken(this.jwt.createToken(user.getEmail(), user.getRoles()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailException(String.format("El correo ya registrado - %s", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
    }


}
