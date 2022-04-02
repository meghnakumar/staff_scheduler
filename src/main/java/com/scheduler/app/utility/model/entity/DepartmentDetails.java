package com.scheduler.app.utility.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class DepartmentDetails {
    @Getter
    @Setter
    private String departmentId;

    @Getter
    @Setter
    private List shiftEmployees;

}
