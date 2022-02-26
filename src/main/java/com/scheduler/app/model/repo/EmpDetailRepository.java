package com.scheduler.app.model.repo;

import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpDetailRepository extends JpaRepository<EmpDetailPOJO, Integer> {


    @Override
    Optional<EmpDetailPOJO> findById(Integer integer);

    EmployeeCredsDTO getLoginCredsById(Integer id);

    //Requires maintaining the order of values.
    /*@Query(name = "Fetch_Creds", value = "select new com.scheduler.app.model.dto.EmpCredsDTO(e.id, e.eid, e.employeeNumber) FROM empdetails e WHERE e.id = ?1 ")
    EmpCredsDTO getCreds(Integer id);*/

    List<EmpDetailPOJO> findAll();
}