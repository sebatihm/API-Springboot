package com.springbook.application.sjhm.API_springboot.Model.User.Web;

import com.springbook.application.sjhm.API_springboot.Anotation.AnotationControllerTest;
import com.springbook.application.sjhm.API_springboot.Model.User.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.User.CreateUserParameters;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.User.UserId;
import com.springbook.application.sjhm.API_springboot.Service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@AnotationControllerTest(UserRestController.class)
public class UserRestControllerDocumentation {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;
    
    @TestConfiguration
    static class TestConfig {
        @Bean
        public RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer() {
            return configurer -> configurer.operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint(),
                            modifyHeaders().removeMatching("X.*")
                                    .removeMatching("Pragma")
                                    .removeMatching("Expires"));
        }
    }
    
    @Test
    public void ownUserDetailsWhenNotLoggedInExample() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(status().isUnauthorized())
                .andDo(document("own-details-unauthorized"));
    }
    
    @Test
    public void authenticatedOfficerDetailsExample() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/me")
                        .with(jwt().jwt(builder -> builder.subject(UUID.randomUUID().toString()))
                                .authorities(new SimpleGrantedAuthority("ROLE_OFFICER"))))
                .andExpect(status().isOk())
                .andDo(document("own-details",
                        responseFields(
                                fieldWithPath("subject").description("The subject from the JWT token"),
                                subsectionWithPath("claims").description("The claims from the JWT token")
                        )));
    }
    
    
    @Test
    public void createOfficerExample() throws Exception {
        UserId userId = new UserId(UUID.randomUUID());
        when(service.createUser(any(CreateUserParameters.class)))
                .thenReturn(new User(userId,
                        "wim@example.com",
                        new AuthServerId(UUID.fromString("eaa8b8a5-a264-48be-98de-d8b4ae2750ac")),
                        "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"));
        mockMvc.perform(post("/api/users")
                        .with(jwt().jwt(builder -> builder.subject(UUID.randomUUID().toString()))
                                .authorities(new SimpleGrantedAuthority("ROLE_OFFICER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "mobileToken": "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
                                }
                                """))
                .andExpect(status().isCreated())
                .andDo(document("create-user",
                        requestFields( // <.>
                                fieldWithPath("mobileToken")
                                        .description("The unique mobile token of the device (for push notifications).")
                        ),
                        responseFields( // <.>
                                fieldWithPath("userId")
                                        .description("The unique id of the user."),
                                fieldWithPath("email")
                                        .description("The email address of the user."),
                                fieldWithPath("authServerId")
                                        .description("The id of the user on the authorization server."),
                                fieldWithPath("mobileToken")
                                        .description("The unique mobile token of the device (for push notifications).")
                        )));
    }

    
}