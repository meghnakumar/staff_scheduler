package com.scheduler.app.model.entity;

import java.util.List;

public class DepartmentDetails {
    private String departmentId;
    private List shiftEmployees;

    public DepartmentDetails(String departmentId, List shiftDetails) {
        this.departmentId = departmentId;
        this.shiftEmployees = shiftDetails;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List getShiftEmployees() {
        return shiftEmployees;
    }

    public void setShiftEmployees(List shiftEmployees) {
        this.shiftEmployees = shiftEmployees;
    }
}
