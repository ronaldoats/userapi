package com.tinoco.userapi.error.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    public PasswordValidator(@Value("${spring.security.user.password_pattern}") String password_pattern) {
        PASSWORD_PATTERN = password_pattern;
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }
    private static String PASSWORD_PATTERN;
    private static Pattern pattern;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
