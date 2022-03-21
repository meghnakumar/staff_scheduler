package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.DepartmentPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentPOJO, String> {

}
