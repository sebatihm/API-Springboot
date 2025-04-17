package com.springbook.application.sjhm.API_springboot.Model.Web.Validators.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CreateUserRequestValidator.class}) 
public @interface ValidCreateUserRequest {
    String message() default "Invalid user";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}