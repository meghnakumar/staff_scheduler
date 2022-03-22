package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ScheduleDetails {

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private List DepartmentList;

    public ScheduleDetails(String date, List departmentList) {
        this.date = date;
        this.DepartmentList = departmentList;
    }

}
