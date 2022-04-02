package com.scheduler.app.staff.model.repo;

import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJO;
import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpAvailabilityRepository extends JpaRepository<EmployeeAvailabilityPOJO, EmployeeAvailabilityPOJOId> {

}
