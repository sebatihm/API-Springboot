package com.springbook.application.sjhm.API_springboot.Model.Report;

import java.time.Instant;

import com.springbook.application.sjhm.API_springboot.Model.User.UserId;
import com.springbook.application.sjhm.orm.jpa.AbstractEntity;
import com.springbook.application.sjhm.util.ArtifactForFramework;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class Report extends AbstractEntity<ReportId> {

    private UserId reporterId;
    private Instant dateTime;
    private String description;

    @ArtifactForFramework
    protected Report() { }
    public Report(ReportId id, UserId reporterId, Instant dateTime, String description) {
        super(id);
        this.reporterId = reporterId;
        this.dateTime = dateTime;
        this.description = description;
    }

    public UserId getReporterId() {
        return reporterId;
    }
    
    public Instant getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
}