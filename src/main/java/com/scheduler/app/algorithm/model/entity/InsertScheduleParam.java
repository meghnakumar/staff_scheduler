package com.scheduler.app.algorithm.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

/**
 * The type - Insert Schedule Param type object.
 */
@Getter
@Setter
public class InsertScheduleParam {

   public String deptId;
   public String empno;
   public Time startTime;
   public Time endTime;
   public Date shift_date;
   public String roleId;
   public String emp_hours;
}
