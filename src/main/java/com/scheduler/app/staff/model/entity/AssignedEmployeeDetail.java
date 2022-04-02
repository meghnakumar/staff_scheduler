package com.scheduler.app.staff.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AssignedEmployeeDetail {

    @Getter
    @Setter
    private String employeeNumber;

    @Getter
    @Setter
    private String availableStartTime;

    @Getter
    @Setter
    private String availableEndTime;

    @Getter
    @Setter
    private String assignedStartTime;

    @Getter
    @Setter
    private String assignedEndTime;

}
