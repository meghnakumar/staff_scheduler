package com.scheduler.app.service;

import com.scheduler.app.model.entity.*;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.repo.*;
import com.scheduler.app.model.request.RequiredRoleHours;
import com.scheduler.app.model.request.ShiftDetailsRequest;
import com.scheduler.app.model.response.ShiftDetailsResponse;
import com.scheduler.app.util.DateUtil;
import com.scheduler.app.model.request.ScheduleOutputRequest;
import com.scheduler.app.model.response.ScheduleOutputResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @Autowired
    ShiftDetailsRepository shiftDetailsRepository;

    @Override
    public ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest) {
        List<RequiredRoleHours> roleHours = shiftDetailsRequest.getShiftRoleHours();
        List<ShiftDetailsPOJO> shiftList = new ArrayList<>();
        String departmentId = shiftDetailsRequest.getDepartmentId();
        int slotType = shiftDetailsRequest.getSlotType();
        LocalTime startTime = getTime(shiftDetailsRequest.getStartTime());
        LocalTime endTime = getTime(shiftDetailsRequest.getEndTime());
        Date shiftDate = Date.valueOf(shiftDetailsRequest.getShiftDate());

        // fetch the records based on shiftDate, departmentId and startTime, it will give multiple records if roles are multiple.
        // Delete the records
        List <ShiftDetailsPOJO> currentDetails = shiftDetailsRepository.findByShiftDateAndDepartmentIdAndStartTime(shiftDate, departmentId, startTime);
        for(ShiftDetailsPOJO shiftDetail: currentDetails) {
            shiftDetailsRepository.deleteById(shiftDetail.getId());
        }

        // insert record for each role in dailyshift table
        for (RequiredRoleHours item: roleHours) {
            ShiftDetailsPOJO shiftDetails = new ShiftDetailsPOJO();
            shiftDetails.setDepartmentId(departmentId);
            shiftDetails.setStartTime(startTime);
            shiftDetails.setEndTime(endTime);
            shiftDetails.setShiftDate(shiftDate);
            shiftDetails.setShiftType(slotType);
            shiftDetails.setRoleId(item.getRoleId());
            shiftDetails.setEmployeeHours(item.getEmployeeHours());
            shiftDetails.setEmployeeHours(item.getEmployeeHours());
            shiftList.add(shiftDetails);
            shiftDetailsRepository.saveAndFlush(shiftDetails);
        }
        return new ShiftDetailsResponse(REQUEST_STATUS.SUCCESS, true);
    }

    private LocalTime getTime(String time) {
        String[] splitTime = time.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
        return localTime;
    }

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
    public ScheduleOutputResponse getScheduleByDateTime(ScheduleOutputRequest scheduleOutputRequest) {

        if(scheduleOutputRequest.getShiftDate() == null || scheduleOutputRequest.getShiftTime() == null || scheduleOutputRequest.getDepartmentId().isEmpty()){
            return new ScheduleOutputResponse(REQUEST_STATUS.INVALID_REQUEST, false, Collections.emptyMap());

        } else {

            LocalDate shiftDate = scheduleOutputRequest.getShiftDate();
            LocalTime shiftTime = scheduleOutputRequest.getShiftTime();
            String departmentType = scheduleOutputRequest.getDepartmentId();

            Map<String, List<ScheduleOutputPOJO>> scheduleMap = new HashMap<>();
            List<ScheduleOutputPOJO> scheduleOutputPOJOList = new ArrayList<>();
            List<ScheduleOutputPOJO> scheduleOutput = scheduleRepository.findByShiftDateAndStartTimeAndDepartmentId(shiftDate, shiftTime, departmentType);

            if (scheduleOutput.size() > 0) {

                for (ScheduleOutputPOJO  schedule: scheduleOutput ) {

                    scheduleOutputPOJOList.add(schedule);
                }

                scheduleMap.put(departmentType, scheduleOutputPOJOList);

                return new ScheduleOutputResponse(REQUEST_STATUS.SUCCESS, true, scheduleMap);
            } else {

                return new ScheduleOutputResponse(REQUEST_STATUS.SUCCESS, false,  Collections.emptyMap());
            }

        }
    }


}
