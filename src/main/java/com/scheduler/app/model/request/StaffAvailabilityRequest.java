package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;

@Getter
@Setter
public class StaffAvailabilityRequest {

    private String employeeNumber;
    private String startTime;
    private String endTime;
    private String availableDate;
    private String availableDay;
}
