package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.EligibleEmployees;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpAvailablityNewPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface EmpAvailblityNewRepository extends JpaRepository<EmpAvailablityNewPOJO, Integer> {
    List<EmpAvailablityNewPOJO> findAll();

 /*   @Query("SELECT new com.scheduler.app.model.entity.EligibleEmployees(e.startTime,e.endTime,h.totalHoursWeekly,e.employeeId) FROM emphistory h inner join EmpAvailablityNewPOJO e where emphistory.employeeId=EmpAvailablityNewPOJO .employeeId and EmpAvailablityNewPOJO .roleId = :role and EmpAvailablityNewPOJO.shiftdate = :shiftdate and EmpAvailablityNewPOJO.departmentId =:department_id order by h.totalHoursWeekly")
    List<EligibleEmployees> fetchEligibleEmployeesInnerJoin(@Param("role_id") Date date, @Param("department") String department);
*/
}

