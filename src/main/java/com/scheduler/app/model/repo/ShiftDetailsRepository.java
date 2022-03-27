package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.AdminShiftPOJO;
import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.ShiftDetailsPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public interface ShiftDetailsRepository extends JpaRepository<ShiftDetailsPOJO, Integer> {

        List<ShiftDetailsPOJO> findByShiftDateAndDepartmentIdAndStartTime(Date shiftDate, String departmentId, LocalTime startTime);

        ShiftDetailsPOJO saveAndFlush(ShiftDetailsPOJO entity);

        void deleteById(Integer id);
}

