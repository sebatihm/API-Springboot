package com.springbook.application.sjhm.API_springboot.Model.Web.Controllers;

import com.springbook.application.sjhm.API_springboot.Infraestructure.*;
import com.springbook.application.sjhm.API_springboot.Model.Converter.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.User.UserId;
import com.springbook.application.sjhm.API_springboot.Model.Web.Request.CreateUserRequest;
import com.springbook.application.sjhm.API_springboot.Service.UserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.springbook.application.sjhm.API_springboot.Util.test.ConstraintViolationSetAssert.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest 
@ActiveProfiles(SpringProfiles.REPOSITORY_TEST)
public class CreateUserRequestValidatorTest {

    @MockBean
    private UserService userService; 
    @Autowired
    private ValidatorFactory factory; 

    @Test
    public void invalidIfAlreadyUserWithGivenMobileToken() {

        String mobileToken = "abc123";
        when(userService.findUserByMobileToken(mobileToken))
                .thenReturn(Optional.of(new User(new UserId(UUID.randomUUID()),
                        "tonotos@example.com",
                        new AuthServerId(UUID.randomUUID()),
                        mobileToken)));

        Validator validator = factory.getValidator(); 

        CreateUserRequest request = new CreateUserRequest(mobileToken);
        Set<ConstraintViolation<CreateUserRequest>> violationSet = validator.validate(request); 
        assertThat(violationSet).hasViolationSize(2)
                .hasViolationOnPath("mobileToken"); 
    }

    @Test
    public void validIfNoUserWithGivenMobileToken() {
        String mobileToken = "abc123";
        when(userService.findUserByMobileToken(mobileToken))
                .thenReturn(Optional.empty());

        Validator validator = factory.getValidator();

        CreateUserRequest request = new CreateUserRequest(mobileToken);
        Set<ConstraintViolation<CreateUserRequest>> violationSet = validator.validate(request);
        assertThat(violationSet).hasNoViolations();
    }
}