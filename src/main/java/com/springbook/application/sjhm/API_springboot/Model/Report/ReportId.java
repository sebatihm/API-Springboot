package com.springbook.application.sjhm.API_springboot.Model.Report;

import com.springbook.application.sjhm.orm.jpa.AbstractEntityId;
import java.util.UUID;

public class ReportId extends AbstractEntityId<UUID> {

    protected ReportId() { }

    public ReportId(UUID id) { 
        super(id);
    }
}