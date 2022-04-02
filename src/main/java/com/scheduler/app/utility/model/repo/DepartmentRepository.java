package com.scheduler.app.utility.model.repo;

import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentPOJO, String> {

}
