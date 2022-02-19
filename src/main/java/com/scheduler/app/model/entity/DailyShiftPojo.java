package com.scheduler.app.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DailyShift")
public class DailyShiftPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shiftId;
    @Column(name="fk_department_id")
    private String departmentId;
    @Column(name="shift_duration")
    private String shiftDuration;

    //shiftType => If there are more than one shifts for a department on a particular day
    //values =>(day shift, night shift)
    @Column(name="shift_type")
    private String shiftType;
    @Column(name="start_time")
    private Date shiftStartTime;
    @Column(name="end_time")
    private Date shiftEndTime;

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getShiftDuration() {
        return shiftDuration;
    }

    public void setShiftDuration(String shiftDuration) {
        this.shiftDuration = shiftDuration;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public Date getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Date shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public Date getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Date shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }


}
