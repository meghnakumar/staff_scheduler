package com.scheduler.app.model.repo;

import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import com.scheduler.app.model.entity.HolidayPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface HolidayRepo extends JpaRepository<HolidayPOJO, Integer> {


    @Transactional
    HolidayPOJO saveAndFlush(HolidayPOJO holiday);
}
