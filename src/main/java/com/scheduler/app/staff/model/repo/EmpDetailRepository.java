package com.scheduler.app.staff.model.repo;

import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpDetailRepository extends JpaRepository<EmpDetailPOJO, Integer> {

    Optional<EmpDetailPOJO> findById(Integer integer);

    EmployeeCredsDTO getLoginCredsById(Integer id);

    EmpDetailPOJO getTopByEmployeeNumber(String employeeNumber);

    List<EmpDetailPOJO> findAll();

    EmployeeCredsDTO getDistinctFirstByEmployeeNumber(String employeeNumber);

    Long countDistinctByDepartmentId(String department);
}