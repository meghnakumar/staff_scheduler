package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.AdminShiftPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface AdminRepository extends JpaRepository<AdminShiftPOJO, Date> {

    @Override
    AdminShiftPOJO  saveAndFlush(AdminShiftPOJO entity);
}
