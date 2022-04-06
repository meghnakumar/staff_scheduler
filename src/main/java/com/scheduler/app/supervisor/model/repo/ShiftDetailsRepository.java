package com.scheduler.app.supervisor.model.repo;

import com.scheduler.app.supervisor.model.entity.ShiftDetailsPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

/**
 * Shift Details JPA repository.
 */
public interface ShiftDetailsRepository extends JpaRepository<ShiftDetailsPOJO, Integer> {

        /**
         * Finds records by Shift Date and Department Id and Start Time
         *
         * @param shiftDate    the shift date
         * @param departmentId the department id
         * @param startTime    the start time
         * @return the list of records that match the criteria
         */
        List<ShiftDetailsPOJO> findByShiftDateAndDepartmentIdAndStartTime(Date shiftDate, String departmentId, LocalTime startTime);

        //Saves the records to the DB
        @Transactional
        ShiftDetailsPOJO saveAndFlush(ShiftDetailsPOJO entity);

        /**
         * Deletes the record that matches the Id given
         *
         * @param id    the id of the record to be deleted.
         */
        void deleteById(Integer id);
}

