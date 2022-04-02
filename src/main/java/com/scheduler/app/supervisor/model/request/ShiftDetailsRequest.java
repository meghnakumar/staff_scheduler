package com.scheduler.app.supervisor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type - Request Object to save shift details to the DB.
 */
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
