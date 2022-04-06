package com.scheduler.app.supervisor.service;

import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
import com.scheduler.app.supervisor.model.response.ShiftDetailsResponse;
import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;

import java.sql.Date;
import java.util.List;

/**
 * The interface for Scheduler service.
 */
public interface SchedulerService {


     /**
      * Saves the shift details to the table in the DB.
      *
      * @param shiftDetailsRequest the shift details request type object
      * @return the shift details response type object.
      */
     ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest);


     /**
      * Gets schedule by the date, time, and department specified.
      *
      * @param request the ScheduleOutputRequest type object.
      * @return the ScheduleOutputResponse type object.
      */
     ScheduleOutputResponse getScheduleByDateTimeDepartment(ScheduleOutputRequest request);

     /**
      * Gets daily shifts form the DB.
      *
      * @return the daily shifts
      */
     List<DailyShiftPOJO> getDailyShifts();


     /**
      * Algorithm Implementation - contains the main business logic for the Scheduler Application.
      *
      * @return the boolean - whether the schedule was created by the algorithm.
      */
     boolean algoImplementation();
}
