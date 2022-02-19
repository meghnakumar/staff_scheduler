package com.scheduler.app.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "JobRole")
public class JobRolePojo {
    @Id
    private String roleId;

    private String departmentId;
    private String roleTitle;
    private int shiftsPerWeek;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public int getShiftsPerWeek() {
        return shiftsPerWeek;
    }

    public void setShiftsPerWeek(int shiftsPerWeek) {
        this.shiftsPerWeek = shiftsPerWeek;
    }


}
