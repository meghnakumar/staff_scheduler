package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ShiftDetailsRequest {

    private String departmentId;
    private String startTime;
    private String endTime;
    private String shiftDate;
    private Integer slotType;
    private List<RequiredRoleHours> shiftRoleHours;
}
