package com.scheduler.app.supervisor.model.repo;

import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.util.List;

public interface DailyShiftRepository extends JpaRepository<DailyShiftPOJO, Integer> {
//        List<DailyShiftPOJO> findByShiftDateTime(Date shiftDate, Time startTime, Time endTime);
        List<DailyShiftPOJO> findByShiftDate(Date shiftDate);
        List<DailyShiftPOJO> findAll();
}

