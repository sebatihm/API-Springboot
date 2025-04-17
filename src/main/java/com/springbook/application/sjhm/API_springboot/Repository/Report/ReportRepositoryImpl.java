package com.springbook.application.sjhm.API_springboot.Repository.Report;

import java.util.UUID;

import com.springbook.application.sjhm.API_springboot.Model.Report.ReportId;
import com.springbook.application.sjhm.orm.jpa.UniqueIdGenerator;

public class ReportRepositoryImpl implements ReportRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public ReportRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public ReportId nextId() {
        return new ReportId(generator.getNextUniqueId());
    }
}
