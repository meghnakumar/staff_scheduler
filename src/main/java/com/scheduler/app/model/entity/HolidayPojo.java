package com.scheduler.app.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Holiday")
public class HolidayPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int holidayId;

    private Date holidayStartDate;
    private Date holidayEndDate;
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
