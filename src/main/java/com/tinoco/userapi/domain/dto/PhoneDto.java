package com.tinoco.userapi.domain.dto;

import com.tinoco.userapi.domain.models.PhoneEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class PhoneDto {
    @Min(value = 1, message = "Numero celular requerido")
    private String number;
    @Min(value = 1, message = "Codigo de la ciudad requerido")
    private String cityCode;
    @Min(value = 1, message = "Codigo del pais requerido")
    private String countryCode;

    public PhoneEntity phoneEntity() {
        return new PhoneEntity()
                .setNumber(number)
                .setCityCode(cityCode)
                .setCountryCode(countryCode);
    }
}
