package com.scheduler.app.staff.model.repo;

import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Employee Detail JPA Repository.
 */
public interface EmpDetailRepository extends JpaRepository<EmpDetailPOJO, Integer> {

    /**
     * Gets Records by the primary key value - employee_id
     *
     * @param integer the employee_id
     * @return the record that matches the employee_id
     */
    Optional<EmpDetailPOJO> findById(Integer integer);

    /**
     * Gets the top records by employee number.
     *
     * @param employeeNumber the employee number
     * @return the top record by the employee number match in the table.
     */
    EmpDetailPOJO getTopByEmployeeNumber(String employeeNumber);

    /**
     * Gets all the records from the table.
     *
     * @return the list of all the records in the table
     */
    List<EmpDetailPOJO> findAll();

    /**
     * Gets the first distinct record by employee number.
     *
     * @param employeeNumber the employee number
     * @return the distinct first record by employee number
     */
    EmployeeCredsDTO getDistinctFirstByEmployeeNumber(String employeeNumber);

    /**
     * Counts distinct records based on the department id
     *
     * @param department the department id
     * @return the count of distinct records for that department id
     */
    Long countDistinctByDepartmentId(String department);
}