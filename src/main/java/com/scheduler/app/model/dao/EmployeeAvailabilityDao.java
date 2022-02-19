package com.scheduler.app.model.dao;

import com.scheduler.app.model.entity.EmployeeAvailabilityPojo;

import java.util.List;

public interface EmployeeAvailabilityDao {
    List<EmployeeAvailabilityPojo> selectAllRecords();
    List<EmployeeAvailabilityPojo> selectByAvailabilityDate();
    List<EmployeeAvailabilityPojo> selectByAvailabilityDay();
    boolean insertEmployeeAvailability(EmployeeAvailabilityPojo employeeAvailability);
    boolean updateEmployeeAvailability(EmployeeAvailabilityPojo employeeAvailability);
    boolean deleteEmployeeAvailability(EmployeeAvailabilityPojo employeeAvailability);
}
