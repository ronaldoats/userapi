package com.tinoco.userapi.error.validation;

import com.tinoco.userapi.domain.dto.PhoneDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class PhoneValidator implements ConstraintValidator<Phone, List<PhoneDto>> {

    @Override
    public boolean isValid(List<PhoneDto> list, ConstraintValidatorContext constraintValidatorContext) {
    System.out.println(list.size());
        //        try {
//            list.forEach(phoneDto -> phoneDto.phoneEntity());
//        } catch (Exception e) {
//            System.out.println(e);
//            return false;
//        }
        return true;
    }
}
