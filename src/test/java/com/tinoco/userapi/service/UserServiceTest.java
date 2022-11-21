package com.tinoco.userapi.service;

import com.tinoco.userapi.domain.dto.PhoneDto;
import com.tinoco.userapi.domain.dto.UserDto;
import com.tinoco.userapi.domain.models.UserEntity;
import com.tinoco.userapi.domain.service.implement.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void createUser() {
        UserDto userDto = new UserDto()
                .setName("Ronaldo Tinoco")
                .setEmail("ronaldots@live.com")
                .setPassword("Ronaldo!@31$$123..");
        List<PhoneDto> phoneDtoList = new ArrayList<PhoneDto>() {
        };
        PhoneDto phoneDto = new PhoneDto()
                .setNumber("88228811")
                .setCityCode("BO")
                .setCountryCode("NI");
        phoneDtoList.add(phoneDto);
        userDto.setPhones(phoneDtoList);

        Optional<UserEntity> user = Optional.ofNullable(userService.createUser(userDto.toUser()));
        assertThat(user.get().getPassword(), not("Ronaldo!@31$$123.."));

    }
}
