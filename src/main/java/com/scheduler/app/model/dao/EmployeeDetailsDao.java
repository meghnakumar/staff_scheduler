package com.scheduler.app.model.dao;

import com.scheduler.app.model.entity.EmployeeDetailsPojo;

import java.util.List;

public interface EmployeeDetailsDao {
    List<EmployeeDetailsPojo> selectAllRecords();
    List<EmployeeDetailsPojo> selectByEmployeeNumber();
    List<EmployeeDetailsPojo> selectByEmployeeDepartment();
    List<EmployeeDetailsPojo> selectByEmployeeJobType();
    List<EmployeeDetailsPojo> selectByEmployeeJobAvailabilityHours();
    List<EmployeeDetailsPojo> selectByEmployeeJobRole();

    boolean insertEmployeeDetails(EmployeeDetailsPojo employeeDetails);
    boolean updateEmployeeDetails(EmployeeDetailsPojo employeeDetails);
    boolean deleteEmployeeDetails(EmployeeDetailsPojo employeeDetails);
}
