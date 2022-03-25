package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.AdminShiftPOJO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface AdminRepository extends JpaRepository<AdminShiftPOJO, Date> {

    @Override
    AdminShiftPOJO  saveAndFlush(AdminShiftPOJO entity);

    AdminShiftPOJO findDistinctTopByOrderByShiftCreationDateDesc();
}
