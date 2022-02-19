package com.scheduler.app.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "JobRole")
public class JobRolePojo {
    @Column(name="pk_role_id")
    @Id
    private String roleId;
    @Column(name="fk_department_id")
    private String departmentId;
    @Column(name="role_title")
    private String roleTitle;
    @Column(name="total_shifts_week")
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
