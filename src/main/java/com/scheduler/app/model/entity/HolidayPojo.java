package com.scheduler.app.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Holiday")
public class HolidayPojo {
    @Column(name="pk_holiday_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int holidayId;
    @Column(name="start_date")
    private Date holidayStartDate;
    @Column(name="end_date")
    private Date holidayEndDate;
    @Column(name="holiday_title")
    private String holidayTitle;

    public int getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    public Date getHolidayStartDate() {
        return holidayStartDate;
    }

    public void setHolidayStartDate(Date holidayStartDate) {
        this.holidayStartDate = holidayStartDate;
    }

    public Date getHolidayEndDate() {
        return holidayEndDate;
    }

    public void setHolidayEndDate(Date holidayEndDate) {
        this.holidayEndDate = holidayEndDate;
    }

    public String getHolidayTitle() {
        return holidayTitle;
    }

    public void setHolidayTitle(String holidayTitle) {
        this.holidayTitle = holidayTitle;
    }



}
