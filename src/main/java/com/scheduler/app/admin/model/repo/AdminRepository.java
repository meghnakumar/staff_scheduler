package com.scheduler.app.admin.model.repo;

import com.scheduler.app.admin.model.entity.AdminShiftPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.sql.Date;

/**
 * Admin JPA repository.
 */
public interface AdminRepository extends JpaRepository<AdminShiftPOJO, Date> {

    @Transactional
    AdminShiftPOJO  saveAndFlush(AdminShiftPOJO entity);

    /**
     * Find Distinct Top by Order and by Shift Creation date arranged in descending order.
     *
     * @return the admin shift pojo object type.
     */
    AdminShiftPOJO findDistinctTopByOrderByShiftCreationDateDesc();
}
