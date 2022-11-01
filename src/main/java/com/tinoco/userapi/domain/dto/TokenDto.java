package com.tinoco.userapi.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenDto {
    public String token;
}
