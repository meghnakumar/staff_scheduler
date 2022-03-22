package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

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

    public AssignedEmployeeDetail(String employeeNumber, String availableStartTime, String availableEndTime, String assignedStartTime, String assignedEndTime) {
        this.employeeNumber = employeeNumber;
        this.availableStartTime = availableStartTime;
        this.availableEndTime = availableEndTime;
        this.assignedStartTime = assignedStartTime;
        this.assignedEndTime = assignedEndTime;
    }
}
