package com.scheduler.app.model.entity;

import java.util.List;

public class ScheduleDetails {
    private String date;
    private List DepartmentList;

    public ScheduleDetails(String date, List departmentList) {
        this.date = date;
        DepartmentList = departmentList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List getDepartmentList() {
        return DepartmentList;
    }

    public void setDepartmentList(List departmentList) {
        DepartmentList = departmentList;
    }
}
