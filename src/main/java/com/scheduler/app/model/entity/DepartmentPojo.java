package com.scheduler.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public class DepartmentPojo {
    @Id
    @Column(name = "pk_department_id")
    private String departmentId;
    @Column(name = "fk_employee_id")
    private String employeeId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "number_of_shifts")
    private int numberOfShifts;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }


}
