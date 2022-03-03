package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class StaffAvailabilityRequest {

    private String employeeNumber;
    private Timestamp startTime;
    private Timestamp endTime;
    private Date availableDate;
    private String availableDay;
}
