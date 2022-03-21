package com.scheduler.app.service;

import com.scheduler.app.model.entity.*;
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

    public Map<String, Map> algoMap = new HashMap<>();
    List<ScheduleDetails> scheduleList = new ArrayList<>();

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    DailyShiftRepository dailyShiftRepository;

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    public List<ScheduleDetails> getEmployees(Date startDate) {
        Date date = DateUtil.addDays(startDate, -1);

        // for each day
        for(int day = 0; day < 7; day++) {
            date = DateUtil.addDays(date, 1);
            List<DailyShiftPOJO> dayShifts = getShifts(date);
            Map<String, Set<Integer>> departmentRoleMap = getDepartMentRolesMap(dayShifts);

            Date finalDate = date;
            Map<String, List> departmentRoleDetailsMap = new HashMap<>();

            // departments required for one day
            List<DepartmentDetails> departmentList = new ArrayList<>();
            // for each department
            departmentRoleMap.forEach((k, v) -> {
                Set<Integer> roles = departmentRoleMap.get(k);

                // Roles required by department
                List<DepartmentRoles> rolesBydepartmentList = new ArrayList<>();
                // for each role
                for (Integer roleId : roles) {
                    List<EmpAvailabilityPOJO> availableEmployees = empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(finalDate, k, roleId);
                    List<AssignedEmployeeDetail> assignedEmpList = new ArrayList<>();
                    for(EmpAvailabilityPOJO empAvailability: availableEmployees) {
                        AssignedEmployeeDetail assignedEmployeeDetail = new AssignedEmployeeDetail(empAvailability.getEmployeeNumber(),
                                empAvailability.getStartTime().toString(), empAvailability.getEndTime().toString(), "", "");
                        assignedEmpList.add(assignedEmployeeDetail);
                    }

                    // role details for a department
                    DepartmentRoles dr = new DepartmentRoles(roleId, assignedEmpList);
                    rolesBydepartmentList.add(dr);
                }
                DepartmentDetails depDetails = new DepartmentDetails(k, rolesBydepartmentList);
                departmentList.add(depDetails);
                departmentRoleDetailsMap.put(k, rolesBydepartmentList);

            });
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            ScheduleDetails scheduleDetails = new ScheduleDetails(dateString, departmentList);
            algoMap.put(dateString, departmentRoleDetailsMap);
            scheduleList.add(scheduleDetails);
        }
        return scheduleList;
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
