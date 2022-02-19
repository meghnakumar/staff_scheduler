package com.scheduler.app.model.dao;

import com.scheduler.app.model.entity.DailyShiftPojo;

import java.util.List;

public interface DailyShiftDao {
    List<DailyShiftPojo> selectAllRecords();
    List<DailyShiftPojo> selectByDepartment();
    List<DailyShiftPojo> selectByShiftType();
    List<DailyShiftPojo> selectByShiftDuration();
    List<DailyShiftPojo> selectByStartTimeAndEndTime();
    boolean insertDailyShift(DailyShiftPojo dailyShift);
    boolean updateDailyShift(DailyShiftPojo dailyShift);
    boolean deleteDailyShift(DailyShiftPojo dailyShift);
}
