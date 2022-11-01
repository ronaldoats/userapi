package com.tinoco.userapi.error.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "{Password.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
