package com.scheduler.app.staff.model.repo;

import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJO;
import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * Employee Availability JPA Repository.
 */
public interface EmpAvailabilityRepository extends JpaRepository<EmployeeAvailabilityPOJO, EmployeeAvailabilityPOJOId> {

    @Query(value = "SELECT a.start_time, a.end_time, a.employee_id, b.total_hours_weekly FROM `empavailability` a INNER JOIN `emphistory` b ON a.employee_id = b.employee_id "
            + "WHERE a.role_Id = :roleId and a.shift_date = :shiftDate and a.department_id = :deptId "
            + "ORDER BY b.total_hours_weekly", nativeQuery = true)
    List<String> fetchEligibleEmployeesInnerJoin(Integer roleId, Date shiftDate, String deptId);
}
