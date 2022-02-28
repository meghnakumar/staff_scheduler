package com.scheduler.app.model.repo;

import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpDetailRepository extends JpaRepository<EmpDetailPOJO, Integer> {

    Optional<EmpDetailPOJO> findById(Integer integer);

    EmployeeCredsDTO getLoginCredsById(Integer id);

    List<EmpDetailPOJO> findAll();
}