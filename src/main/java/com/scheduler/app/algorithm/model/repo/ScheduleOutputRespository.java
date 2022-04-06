package com.scheduler.app.algorithm.model.repo;

import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleOutputRespository extends JpaRepository<ScheduleOutputPOJO, Integer> {
    @Modifying
    @Query(value = "truncate table scheduleoutput", nativeQuery = true)
    void truncateTable();
}
