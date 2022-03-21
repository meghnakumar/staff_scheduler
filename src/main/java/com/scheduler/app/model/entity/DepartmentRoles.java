package com.scheduler.app.model.entity;

import java.util.List;

public class DepartmentRoles {
    private Integer role;
    private List<AssignedEmployeeDetail> employeeList;

    public DepartmentRoles(Integer role, List<AssignedEmployeeDetail> employeeList) {
        this.role = role;
        this.employeeList = employeeList;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public List<AssignedEmployeeDetail> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<AssignedEmployeeDetail> employeeList) {
        this.employeeList = employeeList;
    }
}
