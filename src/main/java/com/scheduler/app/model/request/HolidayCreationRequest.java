package com.scheduler.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class HolidayCreationRequest {

    private int holidayId;
    private String holidayTitle;
    private Date startDate;
    private Date endDate;
}
