package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;
import com.scheduler.app.model.entity.ShiftDetailsPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface EmployeeHistoryRepository extends JpaRepository<EmpHistoryPOJO, Integer> {
    List<EmpHistoryPOJO> findEmpHistoryById(int employeeId);
    EmpHistoryPOJO saveAndFlush(EmpHistoryPOJO entity);

}
