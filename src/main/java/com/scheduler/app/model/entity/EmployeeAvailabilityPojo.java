package com.scheduler.app.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "EmployeeAvailability")
public class EmployeeAvailabilityPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int availabilityId;
    @Column(name = "fk_employee_id")
    private int employeeId;
    @Column(name = "available_date")
    private Date availabilityDate;
    @Column(name = "available_day")
    private Date availabilityDay;
    @Column(name = "start_time")
    private Time availabilityStartTime;
    @Column(name = "end_time")
    private Time availabilityEndTime;

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(Date availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public Date getAvailabilityDay() {
        return availabilityDay;
    }

    public void setAvailabilityDay(Date availabilityDay) {
        this.availabilityDay = availabilityDay;
    }

    public Time getAvailabilityStartTime() {
        return availabilityStartTime;
    }

    public void setAvailabilityStartTime(Time availabilityStartTime) {
        this.availabilityStartTime = availabilityStartTime;
    }

    public Time getAvailabilityEndTime() {
        return availabilityEndTime;
    }

    public void setAvailabilityEndTime(Time availabilityEndTime) {
        this.availabilityEndTime = availabilityEndTime;
    }


}
