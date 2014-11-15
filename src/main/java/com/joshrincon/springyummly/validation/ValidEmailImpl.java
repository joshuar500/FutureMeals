package com.joshrincon.springyummly.validation;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {
    private int min;
    @Override
    public void initialize(ValidEmail validEmail) {
// valid email argument
        min = validEmail.min();
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email.length() <= min) {
// if less than 8
            return false;
        } if (!EmailValidator.getInstance(false).isValid(email)) {
// if false true
            return false;
        }
        return true;
    }
}