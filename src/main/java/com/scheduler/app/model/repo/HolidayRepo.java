package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.HolidayPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface HolidayRepo extends JpaRepository<HolidayPOJO, Integer> {


    @Transactional
    HolidayPOJO saveAndFlush(HolidayPOJO holiday);
}
