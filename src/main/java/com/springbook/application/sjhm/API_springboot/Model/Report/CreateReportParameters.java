package com.springbook.application.sjhm.API_springboot.Model.Report;

import java.time.Instant;

import com.springbook.application.sjhm.API_springboot.Model.User.UserId;

public record CreateReportParameters(UserId userId, Instant dateTime, String description) {
}