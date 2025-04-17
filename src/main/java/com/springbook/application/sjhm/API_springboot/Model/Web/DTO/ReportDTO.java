package com.springbook.application.sjhm.API_springboot.Model.Web.DTO;

import java.time.Instant;

import com.springbook.application.sjhm.API_springboot.Model.Report.Report;
import com.springbook.application.sjhm.API_springboot.Model.Report.ReportId;
import com.springbook.application.sjhm.API_springboot.Service.UserService;

public record ReportDTO(ReportId id,String reporter,Instant dateTime, String description) {

    public static ReportDTO fromReport(Report report, UserService userService) {
        return new ReportDTO(report.getId(),
                userService.getUserById(report.getReporterId()).getEmail(),
                report.getDateTime(),
                report.getDescription());
    }
}
