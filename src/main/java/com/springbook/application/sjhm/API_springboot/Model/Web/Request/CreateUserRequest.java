package com.springbook.application.sjhm.API_springboot.Model.Web.Request;

import org.springframework.security.oauth2.jwt.Jwt;

import com.springbook.application.sjhm.API_springboot.Model.Converter.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.User.CreateUserParameters;
import com.springbook.application.sjhm.API_springboot.Model.Web.Validators.user.ValidCreateUserRequest;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;
@ValidCreateUserRequest
public record CreateUserRequest(@NotEmpty String mobileToken) { 

    public CreateUserParameters toParameters(Jwt jwt) {
        AuthServerId authServerId = new AuthServerId(UUID.fromString(jwt.getSubject()));
        String email = jwt.getClaimAsString("email");
        return new CreateUserParameters(authServerId, email, mobileToken);
    }
}