package com.springbook.application.sjhm.API_springboot.Model.Web.Controllers;

import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.springbook.application.sjhm.API_springboot.Model.Converter.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.Report.CreateReportParameters;
import com.springbook.application.sjhm.API_springboot.Model.Report.Report;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.Web.DTO.ReportDTO;
import com.springbook.application.sjhm.API_springboot.Model.Web.ExceptionHandler.UserNotFoundException;
import com.springbook.application.sjhm.API_springboot.Model.Web.Request.CreateReportRequest;
import com.springbook.application.sjhm.API_springboot.Service.ReportService;
import com.springbook.application.sjhm.API_springboot.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {
    private final ReportService service;
    private final UserService userService;

    public ReportRestController(ReportService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReportDTO createReport(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody CreateReportRequest request) {
        AuthServerId authServerId = new AuthServerId(UUID.fromString(jwt.getSubject()));
        User user = userService.findUserByAuthServerId(authServerId)
            .orElseThrow(() -> new UserNotFoundException(authServerId));
        CreateReportParameters parameters = request.toParameters(user.getId());
        Report report = service.createReport(parameters);
        return ReportDTO.fromReport(report, userService);
    }
}