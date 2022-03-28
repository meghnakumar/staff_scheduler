package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmployeeAvailabilityPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface EmpAvailabilityRepository extends JpaRepository<EmployeeAvailabilityPOJO,Integer> {

//    List<EmpAvailabilityPOJO> findByShiftDate(Date date);

//    @Query(value = "select * FROM empavailablitynew where shiftdate = :date and employee_id in (select employee_id from empdetails where department_id = :department and role_id = :role)", nativeQuery = true)
//    List<EmployeeAvailabilityPOJO> findEmployeeByDateAndRoleAndDeparment(@Param("date") Date date, @Param("department") String department, @Param("role") int roleId);

    EmployeeAvailabilityPOJO findEmployeeAvailabilityPOJOByShiftDateAndEmployeeId(Date date, Integer employeeId);
}
