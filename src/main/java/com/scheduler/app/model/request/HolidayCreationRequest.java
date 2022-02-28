package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class HolidayCreationRequest {

    private int holidayId;
    private String holidayTitle;
    private Date startDate;
    private Date endDate;
}
