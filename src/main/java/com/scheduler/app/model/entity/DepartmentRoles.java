package com.scheduler.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class DepartmentRoles {

    @Getter
    @Setter
    private Integer role;

    @Getter
    @Setter
    private List<AssignedEmployeeDetail> employeeList;

}
