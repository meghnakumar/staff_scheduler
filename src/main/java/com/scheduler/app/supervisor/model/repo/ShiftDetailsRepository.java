package com.scheduler.app.supervisor.model.repo;

import com.scheduler.app.supervisor.model.entity.ShiftDetailsPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface ShiftDetailsRepository extends JpaRepository<ShiftDetailsPOJO, Integer> {

        List<ShiftDetailsPOJO> findByShiftDateAndDepartmentIdAndStartTime(Date shiftDate, String departmentId, LocalTime startTime);

        ShiftDetailsPOJO saveAndFlush(ShiftDetailsPOJO entity);

        void deleteById(Integer id);
}

