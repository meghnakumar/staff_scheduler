package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DepartmentDetails {
    @Getter
    @Setter
    private String departmentId;

    @Getter
    @Setter
    private List shiftEmployees;

    public DepartmentDetails(String departmentId, List shiftDetails) {
        this.departmentId = departmentId;
        this.shiftEmployees = shiftDetails;
    }

}
