package com.springbook.application.sjhm.API_springboot.Model.Web.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReportDescriptionValidator implements ConstraintValidator<ValidReportDescription, String> { 

    @Override
    public void initialize(ValidReportDescription constraintAnnotation) { 
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;
        if (!value.toLowerCase().contains("suspect")) { 
            result = false;
        }
        return result;
    }
}