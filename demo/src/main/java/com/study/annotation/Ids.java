package com.study.annotation;
import com.study.config.validator.IdsValidator;

import javax.validation.Constraint;

import javax.validation.Payload;

import java.lang.annotation.*;


@Documented

@Constraint(validatedBy = IdsValidator.class)

@Target( {ElementType.METHOD , ElementType.FIELD , ElementType.PARAMETER})

@Retention(RetentionPolicy.RUNTIME)

public @interface Ids {


    String message() default "영문, 숫자와 특수기호(.)()(-)만 사용 가능합니다.";


    Class<?>[] groups() default{};


    Class<? extends Payload>[] payload() default {};


}
