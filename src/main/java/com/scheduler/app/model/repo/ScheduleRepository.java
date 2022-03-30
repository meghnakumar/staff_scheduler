package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.ScheduleOutputPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleOutputPOJO, Integer> {

    List<ScheduleOutputPOJO> findByShiftDateAndStartTimeAndDepartmentId(LocalDate shiftDate, LocalTime shiftTime, String departmentType);
}