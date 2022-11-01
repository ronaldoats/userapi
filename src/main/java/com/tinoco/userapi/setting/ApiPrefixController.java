package com.tinoco.userapi.setting;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RequestMapping(value = "/api", consumes = {"application/json"}, produces = {"application/json"})
public @interface ApiPrefixController {
    @AliasFor(annotation = Component.class)
    String value() default "";
}