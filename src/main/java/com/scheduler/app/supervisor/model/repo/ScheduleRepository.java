package com.scheduler.app.supervisor.model.repo;

import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Schedule JPA Repository.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleOutputPOJO, Integer> {

    /**
     * Finds records by Shift Date and Start Time and Department Id
     *
     *
     * @param shiftDate      the shift date
     * @param shiftTime      the shift time
     * @param departmentType the department type
     * @return the list of records that match the query
     */
    List<ScheduleOutputPOJO> findByShiftDateAndStartTimeAndDepartmentId(LocalDate shiftDate, LocalTime shiftTime, String departmentType);
}