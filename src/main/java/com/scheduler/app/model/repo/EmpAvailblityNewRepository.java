package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface EmpAvailblityNewRepository extends JpaRepository<EmpAvailablityNewPOJO, Integer> {
    List<EmpAvailablityNewPOJO> findAll();

    @Query(value = "select * FROM empavailability where available_date = :date and employee_id in (select employee_id from empdetails where department_id = :department and role_id = :role)", nativeQuery = true)
    List<EmpAvailabilityPOJO> findEmployeeByDateAndRoleAndDeparment(@Param("date") Date date, @Param("department") String department, @Param("role") int roleId);

}

