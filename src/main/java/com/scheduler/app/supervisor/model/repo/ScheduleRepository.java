package com.scheduler.app.supervisor.model.repo;

import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleOutputPOJO, Integer> {

    List<ScheduleOutputPOJO> findByShiftDateAndStartTimeAndDepartmentId(LocalDate shiftDate, LocalTime shiftTime, String departmentType);
}