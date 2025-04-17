package com.springbook.application.sjhm.API_springboot.Repository.Report;

import org.springframework.data.repository.CrudRepository;

import com.springbook.application.sjhm.API_springboot.Model.Report.Report;
import com.springbook.application.sjhm.API_springboot.Model.Report.ReportId;

public interface ReportRepository extends CrudRepository<Report, ReportId>, ReportRepositoryCustom {
}