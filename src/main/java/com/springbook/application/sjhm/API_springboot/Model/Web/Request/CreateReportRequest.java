package com.springbook.application.sjhm.API_springboot.Model.Web.Request;

import java.time.Instant;

import com.springbook.application.sjhm.API_springboot.Model.Report.CreateReportParameters;
import com.springbook.application.sjhm.API_springboot.Model.User.UserId;

import com.springbook.application.sjhm.API_springboot.Model.Web.Validators.*;;

@ValidCreateReportRequest
public record CreateReportRequest(Instant dateTime,@ValidReportDescription String description,boolean trafficIncident, int numberOfInvolvedCars){
    public CreateReportParameters toParameters(UserId userId) {
        return new CreateReportParameters(userId, dateTime, description);
    }
}