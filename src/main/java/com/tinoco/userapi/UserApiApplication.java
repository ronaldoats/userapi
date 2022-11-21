package com.tinoco.userapi;

import com.tinoco.userapi.domain.models.RoleEntity;
import com.tinoco.userapi.domain.models.UserEntity;
import com.tinoco.userapi.domain.repository.RoleRepository;
import com.tinoco.userapi.domain.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication()
public class UserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserService service, RoleRepository roleRepository) {
        return (args) -> {
            //create rol
            RoleEntity roleEntity = new RoleEntity()
                    .setRoleName("admin")
                    .setDescription("Administrador");
            roleRepository.save(roleEntity);
            //create user default
            service.createUser(
                    new UserEntity()
                            .setActive(true)
                            .setName("Ronaldo Tinoco")
                            .setEmail("ronaldots@live.com")
                            .setPassword("password$$12..")
                            .setRoles(Collections.singletonList(roleEntity))
            );

        };
    }
}