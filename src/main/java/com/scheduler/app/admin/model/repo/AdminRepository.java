package com.scheduler.app.admin.model.repo;

import com.scheduler.app.admin.model.entity.AdminShiftPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface AdminRepository extends JpaRepository<AdminShiftPOJO, Date> {

    @Override
    AdminShiftPOJO  saveAndFlush(AdminShiftPOJO entity);

    AdminShiftPOJO findDistinctTopByOrderByShiftCreationDateDesc();
}
