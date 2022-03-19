package com.scheduler.app.service;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.DepartmentPOJO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;
import com.scheduler.app.model.repo.DailyShiftRepository;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    public String department = "Dept01";
    public int roleId = 2;
    public Map<String, Map> algoMap = new HashMap<>();



    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    DailyShiftRepository dailyShiftRepository;

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    public Map<String, Map> getEmployees(Date startDate) {
        Date date = DateUtil.addDays(startDate, -1);

        // for each day
        for(int day = 0; day < 7; day++) {
            date = DateUtil.addDays(date, 1);
            List<DailyShiftPOJO> dayShifts = getShifts(date);
            Map<String, Set<Integer>> departmentRoleMap = getDepartMentRolesMap(dayShifts);

            Date finalDate = date;
            Map<String, Map> departmentRoleDetailsMap = new HashMap<>();
            // for each department
            departmentRoleMap.forEach((k, v) -> {
                Map<Integer, List> roleMap = new HashMap<>();
                Set<Integer> roles = departmentRoleMap.get(k);

                // for each role
                for (Integer roleId : roles) {
                    List<EmpAvailabilityPOJO> availableEmployees = empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(finalDate, k, roleId);
                    roleMap.put(roleId, availableEmployees);
                }
                departmentRoleDetailsMap.put(k, roleMap);

            });
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            algoMap.put(dateString, departmentRoleDetailsMap);
        }

        return algoMap;
    }

    public Map<String, Set<Integer>> getDepartMentRolesMap(List<DailyShiftPOJO> dayShifts) {
        Map<String, Set<Integer>> departmentRoles = new HashMap();
        List<String> departments = new ArrayList<>();
        for(DailyShiftPOJO shift: dayShifts) {
            DepartmentPOJO departmentInfo = shift.getDepartment();
            String departmentId = departmentInfo.getId();
            if(departments.contains(departmentId)) {
                Set<Integer> roles = departmentRoles.get(departmentId);
                roles.add(shift.getRoleId());
                departmentRoles.put(departmentId, roles);
            }else {
                Set<Integer> roles = new HashSet<>();
                departments.add(departmentId);
                roles.add(shift.getRoleId());
                departmentRoles.put(departmentId, roles);
            }
        }
        return departmentRoles;
    }

    public List<EmpAvailabilityPOJO> getShiftRoleEmployees(Date date, String department, Integer roleId) {
        List<EmpAvailabilityPOJO> availableEmployees = empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(date, department, roleId);
        for(EmpAvailabilityPOJO employee: availableEmployees) {
            System.out.println(employee.getEmployeeId() +" " +  employee.getStartTime() + " " + employee.getEndTime());
        }
        return availableEmployees;
    }




    public List<DailyShiftPOJO> getShifts(Date date) {
        List<DailyShiftPOJO> dailyShiftList = dailyShiftRepository.findByShiftDate(date);
        return dailyShiftList;
    }

    public List<EmpHistoryPOJO> getEmpHistory(int employeeId) {
        List<EmpHistoryPOJO> empHistoryList = employeeHistoryRepository.findEmpHistoryById(employeeId);
        return empHistoryList;
    }

    public void addEmpHistory(int employeeId) {

    }


}
