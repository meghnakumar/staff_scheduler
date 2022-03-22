package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DepartmentRoles {

    @Getter
    @Setter
    private Integer role;

    @Getter
    @Setter
    private List<AssignedEmployeeDetail> employeeList;

    public DepartmentRoles(Integer role, List<AssignedEmployeeDetail> employeeList) {
        this.role = role;
        this.employeeList = employeeList;
    }

}
