package com.scheduler.app.supervisor.model.repo;

import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.util.List;

/**
 * The Daily Shift JPA repository.
 */
public interface DailyShiftRepository extends JpaRepository<DailyShiftPOJO, Integer> {

        /**
         * Find by shift date
         *
         * @param shiftDate the shift date
         * @return the list of records
         */
        List<DailyShiftPOJO> findByShiftDate(Date shiftDate);

        /**
         * Find all records from the database table.
         *
         * @return the list of records
         */
        List<DailyShiftPOJO> findAll();
}

