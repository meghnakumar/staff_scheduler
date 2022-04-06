package com.scheduler.app.admin.model.repo;

import com.scheduler.app.admin.model.entity.HolidayPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Holiday JPA repository.
 */
@Repository
public interface HolidayRepository extends JpaRepository<HolidayPOJO, Integer> {


    @Transactional
    HolidayPOJO saveAndFlush(HolidayPOJO holiday);

    /**
     * Find DB Records by Start Date Greater than or equal to given date AND the End Date less than or equal to the given date.
     *
     * @param start the start date
     * @param end   the end date
     * @return the list of Records
     */
    List<HolidayPOJO> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date start , Date end);
}
