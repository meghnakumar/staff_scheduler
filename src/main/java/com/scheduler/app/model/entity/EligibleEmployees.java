package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Time;

public class EligibleEmployees {
    @Getter
    @Setter
    public Time availableStartTime;

    @Getter
    @Setter
    public Time availableEndTime;

    @Getter
    @Setter
    public String employeeId;

    @Getter
    @Setter
    public int totalHoursLastWeek;

    public EligibleEmployees(Time availableStartTime, Time availableEndTime, String employeeId, int totalHoursLastWeek){

    this.availableStartTime=availableStartTime;
    this.availableEndTime=availableEndTime;
    this.totalHoursLastWeek=totalHoursLastWeek;
    this.employeeId=employeeId;
    }
}
