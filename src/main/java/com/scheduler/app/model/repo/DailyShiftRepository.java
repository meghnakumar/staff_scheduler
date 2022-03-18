package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface DailyShiftRepository extends JpaRepository {
        List<DailyShiftPOJO> findByShiftDateTime(Date shiftDate, Time startTime, Time endTime);

}

