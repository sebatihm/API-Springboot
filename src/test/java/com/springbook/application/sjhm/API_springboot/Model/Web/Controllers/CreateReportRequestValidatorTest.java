package com.springbook.application.sjhm.API_springboot.Model.Web.Controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Test;

import com.springbook.application.sjhm.API_springboot.Model.Web.Request.CreateReportRequest;

import java.time.Instant;
import java.util.Set;

import static com.springbook.application.sjhm.API_springboot.Util.test.ConstraintViolationSetAssert.assertThat;




public class CreateReportRequestValidatorTest {
    @Test
    public void givenTrafficIndicentButInvolvedCarsZero_invalid() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            CreateReportRequest parameters = new CreateReportRequest(Instant.now(),
                    "The suspect was wearing a black hat",
                    true,
                    0);
            Set<ConstraintViolation<CreateReportRequest>> violationSet = validator.validate(parameters);
            assertThat(violationSet).hasViolationOnPath("");
        }
    }

    @Test
    public void givenTrafficIndicent_involvedCarsMustBePositive() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            CreateReportRequest parameters = new CreateReportRequest(Instant.now(),
                    "The suspect was wearing a black hat.",
                    true,
                    2);
            Set<ConstraintViolation<CreateReportRequest>> violationSet = validator.validate(parameters);
            assertThat(violationSet).hasNoViolations();
        }
    }

    @Test
    public void givenNoTrafficIndicent_involvedCarsDoesNotMatter() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            CreateReportRequest parameters = new CreateReportRequest(Instant.now(),
                    "The suspect was wearing a black hat.",
                    false,
                    0);
            Set<ConstraintViolation<CreateReportRequest>> violationSet = validator.validate(parameters);
            assertThat(violationSet).hasNoViolations();
        }
    }
}