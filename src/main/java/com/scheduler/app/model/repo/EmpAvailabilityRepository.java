package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.EmployeeAvailabilityPOJO;
import com.scheduler.app.model.entity.EmployeeAvailabilityPOJOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpAvailabilityRepository extends JpaRepository<EmployeeAvailabilityPOJO, EmployeeAvailabilityPOJOId> {

}
