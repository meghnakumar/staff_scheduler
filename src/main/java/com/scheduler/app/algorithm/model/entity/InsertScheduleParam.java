package com.scheduler.app.algorithm.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;


public class InsertScheduleParam {
   @Getter
   @Setter
   public String deptId;

   @Getter
   @Setter
   public String empno;

   @Getter
   @Setter
   public Time startTime;

   @Getter
   @Setter
   public Time endTime;

   @Getter
   @Setter
   public Date shift_date;

   @Getter
   @Setter
   public String roleId;

   @Getter
   @Setter
   public String emp_hours;


}
