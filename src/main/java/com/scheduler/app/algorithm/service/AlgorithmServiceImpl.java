package com.scheduler.app.algorithm.service;

import com.scheduler.app.algorithm.model.DTO.EligibleEmployeesDTO;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.algorithm.model.repo.ScheduleOutputRespository;
import com.scheduler.app.staff.model.repo.EmpAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {


    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    ScheduleOutputRespository scheduleOutputRespository;

    /**
     * Gets the employees eligible to be considered for the shifts.
     *
     * @param roleId    the role id
     * @param shiftDate the shift date
     * @param deptId    the dept id
     * @return the list of eligible employees
     */
    public List<EligibleEmployeesDTO> getEligibleEmployees(String roleId, String shiftDate, String deptId) {

//        List<EmployeeAvailabilityPOJO> availabilityPOJOS = empAvailabilityRepository.findBy(String roleId, String shiftDate, String deptId)
        List<EligibleEmployeesDTO> list = new ArrayList<>();
//        list = empAvailabilityRepository.fetchEligibleEmployeesInnerJoin(roleId, Date.valueOf(shiftDate), deptId);
        return list;
    }

    @Transactional
    public void truncateScheduleOutput() {
        scheduleOutputRespository.truncateTable();
    }

    /**
     * The enum for mapping Column indices in the tables.
     */
    public enum COLUMN_INDEX {

        COLUMN_ONE(1),

        COLUMN_TWO(2),

        COLUMN_THREE(3),

        COLUMN_FOUR(4),

        COLUMN_FIVE(5),

        COLUMN_SIX(6),

        COLUMN_SEVEN(7);

        private int numVal;

        COLUMN_INDEX(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }
}
