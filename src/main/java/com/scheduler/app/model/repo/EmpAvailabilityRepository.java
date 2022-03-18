package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.util.List;

public interface EmpAvailabilityRepository extends JpaRepository<EmpAvailabilityPOJO,Integer> {

    List<EmpAvailabilityPOJO> findByAvailableDate(Date date);

}
