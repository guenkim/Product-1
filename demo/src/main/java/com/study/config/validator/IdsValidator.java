package com.study.config.validator;
import com.study.annotation.Ids;
import org.apache.commons.lang3.StringUtils;


import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;


public class IdsValidator implements ConstraintValidator<Ids , String> {


    private Ids ids;


    @Override

    public void initialize(Ids ids) {

        this.ids = ids;

    }


    @Override

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isBlank(s))

            return true;


        return s.matches("^[0-9a-zA-Z._-]*$]");

    }

}
