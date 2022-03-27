package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;
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
    public String id;

    @Getter
    @Setter
    public int totalHoursLastWeek;

    public EligibleEmployees(Time availableStartTime,Time availableEndTime,int totalHoursLastWeek, String id){

    this.availableStartTime=availableStartTime;
    this.availableEndTime=availableEndTime;
    this.totalHoursLastWeek=totalHoursLastWeek;
    this.id=id;
    }
}
