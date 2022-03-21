package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.SchedulePOJO;
import com.scheduler.app.model.entity.ScheduleCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<SchedulePOJO, ScheduleCompositeId> {

    List<SchedulePOJO> findAll();

    Optional<SchedulePOJO> findById(ScheduleCompositeId compositeId);
}