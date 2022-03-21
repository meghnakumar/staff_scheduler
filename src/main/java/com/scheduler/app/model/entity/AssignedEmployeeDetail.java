package com.scheduler.app.model.entity;

public class AssignedEmployeeDetail {
    private String employeeNumber;
    private String availableStartTime;
    private String availableEndTime;
    private String assignedStartTime;
    private String assignedEndTime;

    public AssignedEmployeeDetail(String employeeNumber, String availableStartTime, String availableEndTime, String assignedStartTime, String assignedEndTime) {
        this.employeeNumber = employeeNumber;
        this.availableStartTime = availableStartTime;
        this.availableEndTime = availableEndTime;
        this.assignedStartTime = assignedStartTime;
        this.assignedEndTime = assignedEndTime;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getAvailableStartTime() {
        return availableStartTime;
    }

    public void setAvailableStartTime(String availableStartTime) {
        this.availableStartTime = availableStartTime;
    }

    public String getAvailableEndTime() {
        return availableEndTime;
    }

    public void setAvailableEndTime(String availableEndTime) {
        this.availableEndTime = availableEndTime;
    }

    public String getAssignedStartTime() {
        return assignedStartTime;
    }

    public void setAssignedStartTime(String assignedStartTime) {
        this.assignedStartTime = assignedStartTime;
    }

    public String getAssignedEndTime() {
        return assignedEndTime;
    }

    public void setAssignedEndTime(String assignedEndTime) {
        this.assignedEndTime = assignedEndTime;
    }
}
