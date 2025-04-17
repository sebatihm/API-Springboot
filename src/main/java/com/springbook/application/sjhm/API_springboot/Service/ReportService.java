package com.springbook.application.sjhm.API_springboot.Service;

import org.springframework.stereotype.Service;

import com.springbook.application.sjhm.API_springboot.Model.Report.CreateReportParameters;
import com.springbook.application.sjhm.API_springboot.Model.Report.Report;
import com.springbook.application.sjhm.API_springboot.Repository.Report.ReportRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportService {
    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Report createReport(CreateReportParameters parameters) {
        return repository.save(new Report(repository.nextId(),
                parameters.userId(),
                parameters.dateTime(),
                parameters.description()));
    }
}