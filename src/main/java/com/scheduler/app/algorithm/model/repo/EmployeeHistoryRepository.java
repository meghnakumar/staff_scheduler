package com.scheduler.app.algorithm.model.repo;

import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Employee History JPA repository.
 */
@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmpHistoryPOJO, Integer> {

    /**
     * Finds employee history by employee id
     *
     * @param employeeId the employee id
     * @return the list of employeehistory records.
     */
    List<EmpHistoryPOJO> findEmpHistoryById(int employeeId);

    //Saves a record to the table.
    @Transactional
    EmpHistoryPOJO saveAndFlush(EmpHistoryPOJO entity);

    //Checks if the employee with the employee id exists in the table.
    boolean existsByEmployeeId(int employeeId);

    EmpHistoryPOJO findDistinctTopByEmployeeId(Integer employeeId);
}
