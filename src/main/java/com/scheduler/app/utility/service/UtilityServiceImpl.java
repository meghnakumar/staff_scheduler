package com.scheduler.app.utility.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.entity.AdminShiftPOJO;
import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import com.scheduler.app.admin.model.entity.HolidayPOJO;
import com.scheduler.app.admin.model.repo.AdminRepository;
import com.scheduler.app.utility.model.repo.DepartmentRepository;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.admin.model.repo.HolidayRepository;
import com.scheduler.app.supervisor.model.request.ShiftCreationRequest;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.supervisor.model.response.ShiftCreationResponse;
import com.scheduler.app.supervisor.model.response.ShiftTimingsResponse;
import com.scheduler.app.supervisor.model.response.SupervisorInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Utility Service.
 * This service is shared by many modules for the common functionalities of some utility methods.
 */
@Service
public class UtilityServiceImpl implements  UtilityService {

    private static final int TIME_TWELVE = 12;
    private static final int TIME_TEN = 10;

    /**
     * The enum Util Constants.
     */
    public enum UTIL_CONSTANTS {

        DOUBLE_ZERO("00"), SINGLE_ZERO("0"),
        HYPHEN(" - "), COLON(":"),
        AM(" AM"), PM(" PM");

        private String stringVal;

        UTIL_CONSTANTS(String stringVal) {
            this.stringVal = stringVal;
        }
        public String getStringVal(){
            return stringVal;
        }
    }

    int zero = 0;
    int one = 1;
    int two = 2;
    int three = 3;
    int four = 4;
    int five = 5;
    int six = 6;
    int seven = 7;
    int eight = 8;
    String hyphen = UTIL_CONSTANTS.HYPHEN.getStringVal();

    //Shift Duration Constants.
    private static final int SHIFT_DURATION_FOUR = 4;
    private static final int SHIFT_DURATION_SIX = 6;
    private static final int SHIFT_DURATION_THREE = 3;


    /**
     * Auto-wired Component : JPA Repo to the Employee Details Table.
     */
    @Autowired
    EmpDetailRepository empDetailRepository;

    /**
     * Auto-wired Component : JPA Repo to the 'department' table.
     */
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * Auto-wired Component : JPA Repo to the 'holidays' table.
     */
    @Autowired
    HolidayRepository holidayRepository;

    /**
     * Auto-wired Component : JPA Repo to the 'admin' table.
     */
    @Autowired
    AdminRepository adminRepository;




    /**
     * Gets statistics about the company employees and departments to be display on the Admin Homepage.
     *
     * @param onload the onload boolean value signifying page load.
     * @return the statistics - the response values to be displayed in the UI.
     */
    @Override
    public AdminInfoResponse getStatistics(Boolean onload) {

        //If the page onload is true
        if (Boolean.TRUE.equals(onload)) {

            //Count the number of employees.
            Long numEmployees = empDetailRepository.count();

            //Count the number of departments
            Long numDepartments = departmentRepository.count();

            //Find all departments.
            List<DepartmentPOJO> deptDetails = departmentRepository.findAll();

            Map<String, String> deptInfo = new HashMap<>();
            for (DepartmentPOJO dept : deptDetails) {
                deptInfo.put(dept.getId(), dept.getDepartmentName());
            }

            //Fetch the upcoming holidays
            Map<String, String> holidayInfo = getUpcomingHolidays();

            //Return Success response.
            return new AdminInfoResponse(numEmployees, numDepartments, deptInfo, holidayInfo, REQUEST_STATUS.SUCCESS);

        } else {
            //Return BAD REQUEST response with null values.
            return new AdminInfoResponse(0l, 0l, Collections.emptyMap(), Collections.emptyMap(), REQUEST_STATUS.BAD_REQUEST);
        }
    }

    /**
     * Gets statistics about the department level employees and departments to be display on the Supervisor Homepage.
     *
     * @param onload     the onload boolean value signifying page load.
     * @param department the department - Which Department the Supervisor presides.
     * @return the supervisor stats response via the SupervisorInfoResponse object.
     */
    @Override
    public SupervisorInfoResponse getSupervisorStats(Boolean onload, String department) {
        //If the page onload is true
        if (Boolean.TRUE.equals(onload)) {

            //Count the number of employees in the department.
            Long numEmployees = empDetailRepository.countDistinctByDepartmentId(department);

            //Fetch the upcoming holidays
            Map<String, String> holidayInfo = getUpcomingHolidays();

            //Return the Success response.
            return new SupervisorInfoResponse(REQUEST_STATUS.SUCCESS, holidayInfo, numEmployees);

        } else {

            //Return the BAD_REQUEST response.
            return new SupervisorInfoResponse(REQUEST_STATUS.BAD_REQUEST, Collections.emptyMap(), 0l);
        }
    }

    /**
     * Gets the upcoming holidays for the next 7 days from the Holidays table in the DB.
     *
     * @return the Map of (Holiday Start date, Holiday Title)
     */
    private Map<String, String> getUpcomingHolidays() {

        LocalDate date = LocalDate.now();
        Date dateSQL = Date.valueOf(date);
        Date dateEnd = Date.valueOf(date.plusDays(seven));

        //Fetch all the holidays with in the next 7 days
        List<HolidayPOJO> upcomingHolidays = holidayRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(dateSQL, dateEnd);

        Map<String, String> holidayInfo = new HashMap<>();
        for (HolidayPOJO holiday : upcomingHolidays) {
            //Add to the Map the Holiday Start date vs the Holiday title.
            holidayInfo.put(holiday.getStartDate().toString(), holiday.getHolidayTitle());
        }

        //Return the Map of holidays & start dates.
        return holidayInfo;
    }


    /**
     * Logs new shift duration for all departments in the company.
     *
     * @param request the request for new Shift Duration
     * @return the shift creation response with status.
     */
    @Override
    public ShiftCreationResponse logNewShiftDuration(ShiftCreationRequest request) {

        //Checks if the Shift Duration is either 4, 6, or 8.
        if (getShiftDuration(request)) {

            AdminShiftPOJO adminShiftPOJO = new AdminShiftPOJO();
            LocalDate date = LocalDate.now();
            Date dateSQL = Date.valueOf(date);
            List<LocalTime> timeSlots = new ArrayList<>();

            // Add the current Date and the new shift duration value to the POJO
            adminShiftPOJO.setShiftCreationDate(dateSQL);
            adminShiftPOJO.setSlotType(request.getShiftDuration());

            DateFormat df = new SimpleDateFormat("HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, zero);
            cal.set(Calendar.MINUTE, zero);
            cal.set(Calendar.SECOND, zero);
            int startDate = cal.get(Calendar.DATE);

            //Use the new shift duration to divide the 24 hour day into shifts of 'shify duration' each.
            while (cal.get(Calendar.DATE) == startDate) {
                String[] splitTime = df.format(cal.getTime()).toString().split(":");
                LocalTime time = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));

                //Save the Shifts to List
                timeSlots.add(time);
                cal.add(Calendar.HOUR, request.getShiftDuration());
            }

            //Based on the number of shifts - divide the shift times
            switch (timeSlots.size()) {

                case SHIFT_DURATION_SIX:
                    adminShiftPOJO.setShift1StartTime(timeSlots.get(zero));
                    adminShiftPOJO.setShift2StartTime(timeSlots.get(one));
                    adminShiftPOJO.setShift3StartTime(timeSlots.get(two));
                    adminShiftPOJO.setShift4StartTime(timeSlots.get(three));
                    adminShiftPOJO.setShift5StartTime(timeSlots.get(four));
                    adminShiftPOJO.setShift6StartTime(timeSlots.get(five));
                    break;

                case SHIFT_DURATION_FOUR:
                    adminShiftPOJO.setShift1StartTime(timeSlots.get(zero));
                    adminShiftPOJO.setShift2StartTime(timeSlots.get(one));
                    adminShiftPOJO.setShift3StartTime(timeSlots.get(two));
                    adminShiftPOJO.setShift4StartTime(timeSlots.get(three));
                    break;

                case SHIFT_DURATION_THREE:
                    adminShiftPOJO.setShift1StartTime(timeSlots.get(zero));
                    adminShiftPOJO.setShift2StartTime(timeSlots.get(one));
                    adminShiftPOJO.setShift3StartTime(timeSlots.get(two));
                    break;

                default:

                    throw new RuntimeException("Invalid Length of Time Slots.");
            }

            try {

                //Save to DB
                adminRepository.saveAndFlush(adminShiftPOJO);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            //Return a Success Response
            return new ShiftCreationResponse(REQUEST_STATUS.SUCCESS, true);
        }

        //Return a Failure Response
        return new ShiftCreationResponse(REQUEST_STATUS.FAILED, false);
    }

    //Checks if the Shift Duration is either 4, 6, or 8.
    private boolean getShiftDuration(ShiftCreationRequest request) {
        return request.getShiftDuration() == four || request.getShiftDuration() == six || request.getShiftDuration() == eight;
    }

    /**
     * Gets shift times for the shifts set by the Admin, for the most recent date in the DB.
     *
     * @return the shift times via the ShiftTimingsResponse objects.
     */
    @Override
    public ShiftTimingsResponse getShiftTimes() {

        ShiftTimingsResponse shiftTimingsResponse = new ShiftTimingsResponse();
        AdminShiftPOJO adminShiftPOJO = adminRepository.findDistinctTopByOrderByShiftCreationDateDesc();
        if (null != adminShiftPOJO) {
            shiftTimingsResponse.setStatus(REQUEST_STATUS.SUCCESS);
            shiftTimingsResponse.setSlotType(adminShiftPOJO.getSlotType());
            List<String> shiftTimes = convertTimeFormat(adminShiftPOJO);
            List<String> finalShiftTimes = new ArrayList<>();
            if (adminShiftPOJO.getSlotType() == eight) {
                finalShiftTimes.add(shiftTimes.get(zero)+hyphen+shiftTimes.get(one));
                finalShiftTimes.add(shiftTimes.get(one)+hyphen+shiftTimes.get(two));
                finalShiftTimes.add(shiftTimes.get(two)+hyphen+shiftTimes.get(zero));
                shiftTimingsResponse.setShiftTimes(finalShiftTimes);

            }
            if (adminShiftPOJO.getSlotType() == six) {
                finalShiftTimes.add(shiftTimes.get(zero)+hyphen+shiftTimes.get(one));
                finalShiftTimes.add(shiftTimes.get(one)+hyphen+shiftTimes.get(two));
                finalShiftTimes.add(shiftTimes.get(two)+hyphen+shiftTimes.get(three));
                finalShiftTimes.add(shiftTimes.get(three)+hyphen+shiftTimes.get(zero));
                shiftTimingsResponse.setShiftTimes(finalShiftTimes);

            }

            if (adminShiftPOJO.getSlotType() == four) {
                finalShiftTimes.add(shiftTimes.get(zero)+hyphen+shiftTimes.get(one));
                finalShiftTimes.add(shiftTimes.get(one)+hyphen+shiftTimes.get(two));
                finalShiftTimes.add(shiftTimes.get(two)+hyphen+shiftTimes.get(three));
                finalShiftTimes.add(shiftTimes.get(three)+hyphen+shiftTimes.get(four));
                finalShiftTimes.add(shiftTimes.get(four)+hyphen+shiftTimes.get(five));
                finalShiftTimes.add(shiftTimes.get(five)+hyphen+shiftTimes.get(zero));
                shiftTimingsResponse.setShiftTimes(finalShiftTimes);
            }
        } else {
            shiftTimingsResponse.setStatus(REQUEST_STATUS.FAILED);
        }
        return shiftTimingsResponse;
    }

    private List<String> convertTimeFormat(AdminShiftPOJO adminShiftPOJO) {
        List<String> shiftTimes = new ArrayList<>();
        if(null!=adminShiftPOJO.getShift1StartTime()){
            String finalShift1 = convertedTime(adminShiftPOJO.getShift1StartTime().toString());
            shiftTimes.add(finalShift1);
        }
        if(null!=adminShiftPOJO.getShift2StartTime()) {
            String finalShift2 = convertedTime(adminShiftPOJO.getShift2StartTime().toString());
            shiftTimes.add(finalShift2);
        }
        if(null!=adminShiftPOJO.getShift3StartTime()){
            String finalShift3 = convertedTime(adminShiftPOJO.getShift3StartTime().toString());
            shiftTimes.add(finalShift3);
        }
        if(null!=adminShiftPOJO.getShift4StartTime()){
            String finalShift4 = convertedTime(adminShiftPOJO.getShift4StartTime().toString());
            shiftTimes.add(finalShift4);
        }
        if(null!=adminShiftPOJO.getShift5StartTime()) {
            String finalShift5 = convertedTime(adminShiftPOJO.getShift5StartTime().toString());
            shiftTimes.add(finalShift5);
        }
        if(null!=adminShiftPOJO.getShift6StartTime()){
            String finalShift6 = convertedTime(adminShiftPOJO.getShift6StartTime().toString());
            shiftTimes.add(finalShift6);
        }
        return shiftTimes;
    }

        private String convertedTime(String shiftTime){
            String finalShift="";
            String[] timeDivision = shiftTime.split(UTIL_CONSTANTS.COLON.getStringVal());
            String am =UTIL_CONSTANTS.AM.getStringVal();
            String colon = UTIL_CONSTANTS.COLON.getStringVal();
            String doubleZero = UTIL_CONSTANTS.DOUBLE_ZERO.getStringVal();
            String pm =UTIL_CONSTANTS.PM.getStringVal();
            String singleZero = UTIL_CONSTANTS.SINGLE_ZERO.getStringVal();


            if((Integer.parseInt(timeDivision[0]))>= TIME_TWELVE){
                int convertedShiftTime = Integer.parseInt(timeDivision[0])== TIME_TWELVE ?Integer.parseInt(timeDivision[0]):(Integer.parseInt(timeDivision[0])) - TIME_TWELVE;
                if(convertedShiftTime< TIME_TEN)
                    finalShift = finalShift+singleZero+convertedShiftTime +colon+doubleZero+pm;
                else
                    finalShift = finalShift+convertedShiftTime +colon+doubleZero+pm;
            }
            else if((Integer.parseInt(timeDivision[0]))< TIME_TWELVE){
                int convertedShiftTime = Integer.parseInt(timeDivision[0])==00? TIME_TWELVE :Integer.parseInt(timeDivision[0]);
                if(convertedShiftTime< TIME_TEN)
                    finalShift = finalShift+singleZero+convertedShiftTime+colon+doubleZero+am;
                else
                    finalShift = finalShift+convertedShiftTime+colon+doubleZero+am;
            }
            return finalShift;
        }
}

