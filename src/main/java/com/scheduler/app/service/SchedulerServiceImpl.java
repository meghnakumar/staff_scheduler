package com.scheduler.app.service;

import com.scheduler.app.model.entity.*;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.repo.DailyShiftRepository;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.util.DateUtil;
import com.scheduler.app.model.repo.ScheduleRepository;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;
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

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

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
                        AssignedEmployeeDetail calculatedEmployeeDetail = assignShiftToEmployee(empAvailability);
                        assignedEmpList.add(calculatedEmployeeDetail);
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

    public AssignedEmployeeDetail assignShiftToEmployee(EmpAvailabilityPOJO empAvailability) {
        return new AssignedEmployeeDetail(empAvailability.getEmployeeNumber(),
                empAvailability.getStartTime().toString(), empAvailability.getEndTime().toString(), "", "");
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

    @Override
    public ScheduleResponse getScheduleByDateTime(ScheduleRequest scheduleRequest) {

        if(scheduleRequest.getShiftDate() == null && scheduleRequest.getShiftTime() == null){
            return new ScheduleResponse(REQUEST_STATUS.INVALID_REQUEST, false, Collections.emptyMap());

        } else {

            ScheduleCompositeId compositeId = new ScheduleCompositeId(scheduleRequest.getShiftDate(), scheduleRequest.getShiftTime());
            Map<String, SchedulePOJO> schedule = new HashMap<>();
            Optional<SchedulePOJO> scheduleOutput = scheduleRepository.findById(compositeId);

            if (scheduleOutput.isPresent()) {

                schedule.put(scheduleOutput.get().getDepartment().getId(), scheduleOutput.get());
                return new ScheduleResponse(REQUEST_STATUS.SUCCESS, true, schedule);
            } else {

                return new ScheduleResponse(REQUEST_STATUS.SUCCESS, false,  Collections.emptyMap());
            }

        }
    }


}
