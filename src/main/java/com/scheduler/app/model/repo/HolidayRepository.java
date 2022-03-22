package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.HolidayPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayPOJO, Integer> {


    @Transactional
    HolidayPOJO saveAndFlush(HolidayPOJO holiday);

    List<HolidayPOJO> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date start , Date end);
}
