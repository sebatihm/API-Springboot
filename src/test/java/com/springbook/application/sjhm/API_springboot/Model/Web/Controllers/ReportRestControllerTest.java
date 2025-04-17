package com.springbook.application.sjhm.API_springboot.Model.Web.Controllers;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import com.springbook.application.sjhm.API_springboot.Anotation.AnotationControllerTest;
import com.springbook.application.sjhm.API_springboot.Model.Converter.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.Report.CreateReportParameters;
import com.springbook.application.sjhm.API_springboot.Model.Report.Report;
import com.springbook.application.sjhm.API_springboot.Model.Report.ReportId;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.User.UserId;
import com.springbook.application.sjhm.API_springboot.Service.ReportService;
import com.springbook.application.sjhm.API_springboot.Service.UserService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AnotationControllerTest(ReportRestController.class)
public class ReportRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReportService service;
    @MockBean
    private UserService userService;

    @Test
    public void officerIsAbleToPostAReport() throws Exception {

        UserId userId = new UserId(UUID.randomUUID());
        AuthServerId authServerId = new AuthServerId(UUID.fromString("eaa8b8a5-a264-48be-98de-d8b4ae2750ac"));
        User user = new User(userId,
                "wim@example.com",
                authServerId,
                "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0");
        when(userService.findUserByAuthServerId(authServerId))
                .thenReturn(Optional.of(user));
        when(userService.getUserById(userId))
                .thenReturn(user);
        when(service.createReport(any(CreateReportParameters.class)))
                .thenReturn(new Report(new ReportId(UUID.randomUUID()),
                        userId,
                        Instant.parse("2023-04-11T22:59:03.189+02:00"),
                        "This is a test report description. The suspect was wearing a black hat."));
        mockMvc.perform(post("/api/reports")
                        .with(jwt().jwt(builder -> builder.subject(authServerId.value().toString())
                                        .claim("email", "wim@example.com"))
                                .authorities(new SimpleGrantedAuthority("ROLE_OFFICER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "dateTime": "2023-04-11T22:59:03.189+02:00",
                                    "description": "This is a test report description. The suspect was wearing a black hat."
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("reporter").value("wim@example.com"))
                .andExpect(jsonPath("dateTime").value("2023-04-11T20:59:03.189Z"))
                .andExpect(jsonPath("description").value("This is a test report description. The suspect was wearing a black hat."));
    }
}