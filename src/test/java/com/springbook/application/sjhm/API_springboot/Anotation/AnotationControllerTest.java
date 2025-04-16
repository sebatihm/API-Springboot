package com.springbook.application.sjhm.API_springboot.Anotation;

import com.c4_soft.springaddons.security.oauth2.test.webmvc.AutoConfigureAddonsWebmvcResourceServerSecurity;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME) 
@WebMvcTest 
@AutoConfigureAddonsWebmvcResourceServerSecurity 
@Import(com.springbook.application.sjhm.API_springboot.Infraestructure.Security.WebSecurityConfiguration.class) 
public @interface AnotationControllerTest {

    @AliasFor(annotation = WebMvcTest.class, attribute = "value") 
    Class<?>[] value() default {};

    @AliasFor(annotation = WebMvcTest.class, attribute = "controllers") 
    Class<?>[] controllers() default {};
}
