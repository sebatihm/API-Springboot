package com.springbook.application.sjhm.API_springboot.Model.Web.Controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.http.MediaType;


import static com.springbook.application.sjhm.API_springboot.Util.test.ConstraintViolationSetAssert.assertThat;

import com.springbook.application.sjhm.API_springboot.Model.Web.Request.CreateReportRequest;

import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportDescriptionValidatorTest {

    @Test
    public void givenEmptyString_notValid() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) { //<1>
            Validator validator = factory.getValidator(); //<2>

            CreateReportRequest parameters = new CreateReportRequest(Instant.now(), "", false, 0,
                    createImage());
            Set<ConstraintViolation<CreateReportRequest>> violationSet = validator.validate(parameters); //<3>
            assertThat(violationSet).hasViolationOnPath("description"); //<4>
        }
    }

    
    @Test
    public void givenSuspectWordPresent_valid() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            CreateReportRequest parameters = new CreateReportRequest(Instant.now(),
                    "The suspect was wearing a black hat.", false, 0,
                    createImage());
            Set<ConstraintViolation<CreateReportRequest>> violationSet = validator.validate(parameters);
            assertThat(violationSet).hasNoViolations();
        }
    }

    @NotNull
    private static MockMultipartFile createImage() {
        return new MockMultipartFile("image", "picture.png", MediaType.IMAGE_PNG_VALUE, new byte[]{1, 2, 3});
    }

}