package com.tinoco.userapi.domain.dto;

import com.tinoco.userapi.domain.models.UserEntity;
import com.tinoco.userapi.error.validation.Password;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class UserDto {
    private UUID id;
    @NotBlank(message = "El nombre no debe estar vacio")
    private String name;

    @NotBlank(message = "La contraseña no debe estar vacio")
    @Password(message = "La contraseña no cumple los criterios necesarios")
    private String password;

    @Email(message = "Ingresar un email valido.", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotBlank(message = "El correo no puede esta vacio")
    @NotNull
    private String email;

    @NotEmpty(message = "Lista de telefono no puede estar vacia.")
    //@Phone(message = "El listado de telefono no cumple con el formato")
    private List<@Valid PhoneDto> phones;

    public UserEntity toUser() {
        UserEntity usrEntity = new UserEntity()
                .setName(name)
                .setEmail(email.toLowerCase())
                .setPassword(password);

//        usrEntity.getPhones().addAll(phones.stream()
//                .map(PhoneDto::phoneEntity)
//                .map(phoneEntity -> {
//                    //phoneEntity.setUser(usrEntity);
//                    return phoneEntity;
//                })
//                .collect(Collectors.toList()));

        return usrEntity;
    }
}
