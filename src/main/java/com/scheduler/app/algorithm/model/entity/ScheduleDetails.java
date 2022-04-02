package com.scheduler.app.algorithm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class ScheduleDetails {

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private List DepartmentList;


}
