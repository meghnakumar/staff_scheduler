package com.scheduler.app.model.dao;

import com.scheduler.app.model.entity.HolidayPojo;

import java.util.List;

public interface HolidayDao {
    List<HolidayPojo> selectAllRecords();
    List<HolidayPojo> selectByHolidayTitle();

    boolean insertHoliday(HolidayPojo holiday);
    boolean updateHoliday(HolidayPojo holiday);
    boolean deleteHoliday(HolidayPojo holiday);
}
