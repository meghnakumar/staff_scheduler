package com.scheduler.app.supervisor.service;

import com.scheduler.app.algorithm.databasejdbc.DatabaseOperations;
import com.scheduler.app.algorithm.model.entity.EligibleEmployees;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.algorithm.model.entity.InsertScheduleParam;
import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.supervisor.model.request.RequiredRoleHours;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.supervisor.model.response.ShiftDetailsResponse;
import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import com.scheduler.app.supervisor.model.entity.ShiftDetailsPOJO;
import com.scheduler.app.supervisor.model.repo.DailyShiftRepository;
import com.scheduler.app.supervisor.model.repo.ScheduleRepository;
import com.scheduler.app.supervisor.model.repo.ShiftDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


/**
 * Scheduler Service.
 * This service is responsible for dealing with the creation of the Schedule by using the information present in the relevant
 * tables in the database. The output of the Algorithm which generates the schedule is stored in a database table. This service
 * is also responsible for returning the output i.e. the generated schedule back to the Front-end.
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final int SIXTY = 60;
    private static final int THOUSAND = 1000;

    /**
     * The Insert Schedule Param type object.
     */
    InsertScheduleParam insertScheduleParam = new InsertScheduleParam();

    /**
     * Auto-wired Component : JPA Repo to the Daily Shifts table.
     */
    @Autowired
    private DailyShiftRepository dailyShiftRepository;

    /**
     * Auto-wired Component : JPA Repo to the Employee History table.
     */
    @Autowired
    private EmployeeHistoryRepository employeeHistoryRepository;

    /**
     * Auto-wired Component : JPA Repo to the Schedule Output table.
     */
    @Autowired
    private ScheduleRepository scheduleRepository;

    /**
     * Auto-wired Component : JPA Repo to the Shift Details table.
     */
    @Autowired
    private ShiftDetailsRepository shiftDetailsRepository;


    /**
     * Saves the shift details to the table in the DB.
     *
     * @param shiftDetailsRequest the shift details request type object
     * @return the shift details response type object.
     */
    @Override
    public ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest) {

        List<RequiredRoleHours> roleHours = shiftDetailsRequest.getShiftRoleHours();
        String departmentId = shiftDetailsRequest.getDepartmentId();

        //Get Shift Details from the Request object.
        //Get the slot type selected by the Admin - represents the duration of a shift.
        int slotType = shiftDetailsRequest.getSlotType();
        LocalTime startTime = getTime(shiftDetailsRequest.getStartTime());
        LocalTime endTime = getTime(shiftDetailsRequest.getEndTime());
        Date shiftDate = Date.valueOf(shiftDetailsRequest.getShiftDate());

        // Fetch the records based on shiftDate, departmentId and startTime, it will give multiple records if roles are multiple.
        // Delete the records before new values can be updated in the DB table.
        List <ShiftDetailsPOJO> currentDetails = shiftDetailsRepository.findByShiftDateAndDepartmentIdAndStartTime(shiftDate, departmentId, startTime);
        for(ShiftDetailsPOJO shiftDetail: currentDetails) {
            shiftDetailsRepository.deleteById(shiftDetail.getId());
        }

        //Insert a new record for each role in 'dailyshift' table
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
            // Save the record to the database table using JPA
            shiftDetailsRepository.saveAndFlush(shiftDetails);
        }

        return new ShiftDetailsResponse(REQUEST_STATUS.SUCCESS, true);
    }

    /**
     * Gets LocalTime object from the time values given as a string.
     *
     * @param time the time in string format.
     * @return the LocalTime object.
     */
    private LocalTime getTime(String time) {
        String[] splitTime = time.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
        return localTime;
    }

//    public List<ScheduleDetails> getEmployees(Date startDate) {
//        Date date = DateUtil.addDays(startDate, -1);
//
//        // for each day
//        for(int day = 0; day < 7; day++) {
//            date = DateUtil.addDays(date, 1);
//            List<DailyShiftPOJO> dayShifts = getShifts(date);
//            Map<String, Set<Integer>> departmentRoleMap = getDepartMentRolesMap(dayShifts);
//
//            Date finalDate = date;
//            Map<String, List> departmentRoleDetailsMap = new HashMap<>();
//
//            // departments required for one day
//            List<DepartmentDetails> departmentList = new ArrayList<>();
//            // for each department
//            departmentRoleMap.forEach((k, v) -> {
//                Set<Integer> roles = departmentRoleMap.get(k);
//
//                // Roles required by department
//                List<DepartmentRoles> rolesBydepartmentList = new ArrayList<>();
//                // for each role
//                for (Integer roleId : roles) {
//                    List<EmployeeAvailabilityPOJO> availableEmployees = empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(finalDate, k, roleId);
//                    List<AssignedEmployeeDetail> assignedEmpList = new ArrayList<>();
//                    for(EmployeeAvailabilityPOJO empAvailability: availableEmployees) {
//                        AssignedEmployeeDetail calculatedEmployeeDetail = assignShiftToEmployee(empAvailability);
//                        assignedEmpList.add(calculatedEmployeeDetail);
//                    }
//
//                    // role details for a department
//                    DepartmentRoles dr = new DepartmentRoles(roleId, assignedEmpList);
//                    rolesBydepartmentList.add(dr);
//                }
//                DepartmentDetails depDetails = new DepartmentDetails(k, rolesBydepartmentList);
//                departmentList.add(depDetails);
//                departmentRoleDetailsMap.put(k, rolesBydepartmentList);
//
//            });
//            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
//            ScheduleDetails scheduleDetails = new ScheduleDetails(dateString, departmentList);
//            algoMap.put(dateString, departmentRoleDetailsMap);
//            scheduleList.add(scheduleDetails);
//        }
//        return scheduleList;
//    }

//    public AssignedEmployeeDetail assignShiftToEmployee(EmpAvailabilityPOJO empAvailability) {
//        return new AssignedEmployeeDetail(empAvailability.getEmployeeNumber(),
//                empAvailability.getStartTime().toString(), empAvailability.getEndTime().toString(), "", "");
//    }

//    public List<EmpAvailabilityPOJO> getShiftRoleEmployees(Date date, String department, Integer roleId) {
//        List<EmpAvailabilityPOJO> availableEmployees = empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(date, department, roleId);
//        for(EmpAvailabilityPOJO employee: availableEmployees) {
//            System.out.println(employee.getEmployeeId() +" " +  employee.getStartTime() + " " + employee.getEndTime());
//        }
//        return availableEmployees;
//    }


    /**
     * Gets shifts based on the Date inout.
     *
     * @param date the date
     * @return the list of shifts
     */
    @Override
    public List<DailyShiftPOJO> getShifts(Date date) {
        List<DailyShiftPOJO> dailyShiftList = dailyShiftRepository.findByShiftDate(date);
        return dailyShiftList;
    }

    /**
     * Gets Employee history based on the employee id.
     *
     * @param employeeId the employee id
     * @return the emp history
     */
    @Override
    public List<EmpHistoryPOJO> getEmpHistory(int employeeId) {
        List<EmpHistoryPOJO> empHistoryList = employeeHistoryRepository.findEmpHistoryById(employeeId);
        return empHistoryList;
    }


    /**
     * Gets schedule by the date, time, and department specified.
     *
     * @param scheduleOutputRequest the ScheduleOutputRequest type object.
     * @return the ScheduleOutputResponse type object.
     */
    @Override
    public ScheduleOutputResponse getScheduleByDateTimeDepartment(ScheduleOutputRequest scheduleOutputRequest) {

        if(isScheduleRequestNull(scheduleOutputRequest)){
            // If the request is empty, return the response for an Invalid Request.
            return new ScheduleOutputResponse(REQUEST_STATUS.INVALID_REQUEST, false, Collections.emptyMap());

        } else {

            //Extract query values from the scheduleOutputRequest.
            LocalDate shiftDate = scheduleOutputRequest.getShiftDate();
            LocalTime shiftTime = scheduleOutputRequest.getShiftTime();
            String departmentType = scheduleOutputRequest.getDepartmentId();

            Map<String, List<ScheduleOutputPOJO>> scheduleMap = new HashMap<>();
            //Search for the schedule output based on the query.
            List<ScheduleOutputPOJO> scheduleOutput = scheduleRepository.findByShiftDateAndStartTimeAndDepartmentId(shiftDate, shiftTime, departmentType);

            if (!scheduleOutput.isEmpty()) {

                List<ScheduleOutputPOJO> scheduleOutputPOJOList = new ArrayList<>(scheduleOutput);
                scheduleMap.put("employees", scheduleOutputPOJOList);

                //Return response with the found data & success status.
                return new ScheduleOutputResponse(REQUEST_STATUS.SUCCESS, true, scheduleMap);

            } else {

                //No data ws found by the query - Return empty response & success status.
                return new ScheduleOutputResponse(REQUEST_STATUS.SUCCESS, false,  Collections.emptyMap());
            }

        }
    }


    //Method to check if the request object for the Schedule output is null or empty
    private boolean isScheduleRequestNull(ScheduleOutputRequest scheduleOutputRequest) {
        return scheduleOutputRequest.getShiftDate() == null || scheduleOutputRequest.getShiftTime() == null || scheduleOutputRequest.getDepartmentId().isEmpty();
    }

    /**
     * Gets daily shifts form the DB.
     *
     * @return the daily shifts
     */
    @Override
    public List<DailyShiftPOJO> getDailyShifts() {

        List<DailyShiftPOJO> dailyShiftList = dailyShiftRepository.findAll();
        return dailyShiftList;
    }


    /**
     * Algorithm Implementation - contains the main business logic for the Scheduler Application.
     *
     * @return the boolean - whether the schedule was created by the algorithm.
     */
    @Override
    public boolean algoImplementation(){

        DatabaseOperations.truncateScheduleOutput();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        double totalHours;
        List<DailyShiftPOJO> shiftList = getDailyShifts();

        // For each shift record in the list of shifts map with employees.
        for (DailyShiftPOJO dailyShiftPOJO : shiftList){

            List<EligibleEmployees> eligibleEmployeesList = DatabaseOperations.getEligibleEmployees(dailyShiftPOJO.getRoleId().toString(), dailyShiftPOJO.getShiftDate().toString(), dailyShiftPOJO.getDepartment().getId());
            totalHours=dailyShiftPOJO.getEmployeeHours();

            for (EligibleEmployees eligibleEmployee: eligibleEmployeesList){
                if(eligibleEmployee.getAvailableStartTime().toString().equals(dailyShiftPOJO.getStartTime().toString())&&eligibleEmployee.getAvailableEndTime().toString().equals(dailyShiftPOJO.getEndTime().toString())) {

                    if(totalHours <= 0) {
                        break;
                    }
                    totalHours -= Double.parseDouble(dailyShiftPOJO.getShiftType());
                    insertScheduleParam.setDeptId(dailyShiftPOJO.getDepartment().getId());
                    insertScheduleParam.setEmpno(eligibleEmployee.getEmployeeId());
                    insertScheduleParam.setStartTime(dailyShiftPOJO.getStartTime());
                    insertScheduleParam.setEndTime( dailyShiftPOJO.getEndTime());
                    insertScheduleParam.setShift_date(dailyShiftPOJO.getShiftDate());
                    insertScheduleParam.setRoleId(dailyShiftPOJO.getRoleId()+"");
                    insertScheduleParam.setEmp_hours(dailyShiftPOJO.getShiftType() + "");
                    DatabaseOperations.insertFinalSchedule(insertScheduleParam);
                    DatabaseOperations.updateEmpHistory(Integer.parseInt(dailyShiftPOJO.getShiftType()),eligibleEmployee.getEmployeeId());
                    return true;
                }
                
                else if((eligibleEmployee.getAvailableStartTime().toString().equals(dailyShiftPOJO.getStartTime().toString())&&!(eligibleEmployee.getAvailableEndTime().toString().equals(dailyShiftPOJO.getEndTime().toString())))){
                    Time startTimeEmp = eligibleEmployee.getAvailableStartTime();
                    Time endTimeEmp = eligibleEmployee.getAvailableEndTime();
                    java.util.Date timeStart = null;
                    java.util.Date timeEnd= null;
                    try {
                        timeStart =  format.parse(String.valueOf(startTimeEmp));
                        timeEnd = format.parse(String.valueOf(endTimeEmp));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long diff = timeEnd.getTime() - timeStart.getTime();
                    int diffHours = Math.toIntExact(diff / (SIXTY * SIXTY * THOUSAND));

                    if(totalHours <= 0) {
                        break;
                    }
                    totalHours -= diffHours;

                    insertScheduleParam.setRoleId(dailyShiftPOJO.getRoleId()+"");
                    insertScheduleParam.setEmp_hours(diffHours + "");
                    insertScheduleParam.setDeptId(dailyShiftPOJO.getDepartment().getId());
                    insertScheduleParam.setEmpno(eligibleEmployee.getEmployeeId());
                    insertScheduleParam.setShift_date(dailyShiftPOJO.getShiftDate());
                    insertScheduleParam.setStartTime(dailyShiftPOJO.getStartTime());
                    insertScheduleParam.setEndTime(dailyShiftPOJO.getEndTime());

                    DatabaseOperations.insertFinalSchedule(insertScheduleParam);
                    DatabaseOperations.updateEmpHistory(diffHours,eligibleEmployee.getEmployeeId());
                    //Return true - schedule was generated.
                    return true;
                }
            }
        }

        //Return false
        return false;
    }

}
