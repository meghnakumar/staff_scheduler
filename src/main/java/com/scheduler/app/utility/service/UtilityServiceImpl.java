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

@Service
public class UtilityServiceImpl implements  UtilityService {
    int zero = UTIL_CONSTANTS.ZERO.getNumVal();
    int one = UTIL_CONSTANTS.ONE.getNumVal();
    int two = UTIL_CONSTANTS.TWO.getNumVal();
    int three = UTIL_CONSTANTS.THREE.getNumVal();
    int four = UTIL_CONSTANTS.FOUR.getNumVal();
    int five = UTIL_CONSTANTS.FIVE.getNumVal();
    int six = UTIL_CONSTANTS.SIX.getNumVal();
    int seven = UTIL_CONSTANTS.SEVEN.getNumVal();
    int eight = UTIL_CONSTANTS.EIGHT.getNumVal();
    String hyphen=UTIL_CONSTANTS.HYPHEN.getStringVal();

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    AdminRepository adminRepository;

    private List<String> shiftTimes = new ArrayList<>();


    @Override
    public AdminInfoResponse getStatistics(Boolean onload) {

        if (Boolean.TRUE.equals(onload)) {

            Long numEmployees = empDetailRepository.count();
            Long numDepartments = departmentRepository.count();
            List<DepartmentPOJO> deptDetails = departmentRepository.findAll();

            Map<String, String> deptInfo = new HashMap<>();
            for (DepartmentPOJO dept : deptDetails) {
                deptInfo.put(dept.getId(), dept.getDepartmentName());
            }

            Map<String, String> holidayInfo = getUpcomingHolidays();

            return new AdminInfoResponse(numEmployees, numDepartments, deptInfo, holidayInfo, REQUEST_STATUS.SUCCESS);
        } else {

            return new AdminInfoResponse(0l, 0l, Collections.emptyMap(), Collections.emptyMap(), REQUEST_STATUS.BAD_REQUEST);
        }
    }

    @Override
    public SupervisorInfoResponse getSupervisorStats(Boolean onload, String department) {

        if (Boolean.TRUE.equals(onload)) {

            Long numEmployees = empDetailRepository.countDistinctByDepartmentId(department);
            Map<String, String> holidayInfo = getUpcomingHolidays();

            return new SupervisorInfoResponse(REQUEST_STATUS.SUCCESS, holidayInfo, numEmployees);

        } else {

            return new SupervisorInfoResponse(REQUEST_STATUS.BAD_REQUEST, Collections.emptyMap(), 0l);
        }
    }

    private Map<String, String> getUpcomingHolidays() {
        LocalDate date = LocalDate.now();
        Date dateSQL = Date.valueOf(date);
        Date dateEnd = Date.valueOf(date.plusDays(seven));
        List<HolidayPOJO> upcomingHolidays = holidayRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(dateSQL, dateEnd);

        Map<String, String> holidayInfo = new HashMap<>();
        for (HolidayPOJO holiday : upcomingHolidays) {
            holidayInfo.put(holiday.getStartDate().toString(), holiday.getHolidayTitle());
        }

        return holidayInfo;
    }

    @Override
    public ShiftCreationResponse logNewShiftDuration(ShiftCreationRequest request) {

        if (getShiftDuration(request)) {

            AdminShiftPOJO adminShiftPOJO = new AdminShiftPOJO();
            LocalDate date = LocalDate.now();
            Date dateSQL = Date.valueOf(date);
            List<LocalTime> timeSlots = new ArrayList<>();

            adminShiftPOJO.setShiftCreationDate(dateSQL);
            adminShiftPOJO.setSlotType(request.getShiftDuration());

            DateFormat df = new SimpleDateFormat("HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, zero);
            cal.set(Calendar.MINUTE, zero);
            cal.set(Calendar.SECOND, zero);
            int startDate = cal.get(Calendar.DATE);
            while (cal.get(Calendar.DATE) == startDate) {
                String[] splitTime = df.format(cal.getTime()).toString().split(":");
                LocalTime time = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
                timeSlots.add(time);
                cal.add(Calendar.HOUR, request.getShiftDuration());
            }

            switch (timeSlots.size()) {

                case 6:
                    adminShiftPOJO.setShift1StartTime(timeSlots.get(zero));
                    adminShiftPOJO.setShift2StartTime(timeSlots.get(one));
                    adminShiftPOJO.setShift3StartTime(timeSlots.get(two));
                    adminShiftPOJO.setShift4StartTime(timeSlots.get(three));
                    adminShiftPOJO.setShift5StartTime(timeSlots.get(four));
                    adminShiftPOJO.setShift6StartTime(timeSlots.get(five));
                    break;

                case 4:
                    adminShiftPOJO.setShift1StartTime(timeSlots.get(zero));
                    adminShiftPOJO.setShift2StartTime(timeSlots.get(one));
                    adminShiftPOJO.setShift3StartTime(timeSlots.get(two));
                    adminShiftPOJO.setShift4StartTime(timeSlots.get(three));
                    break;

                case 3:
                    adminShiftPOJO.setShift1StartTime(timeSlots.get(zero));
                    adminShiftPOJO.setShift2StartTime(timeSlots.get(one));
                    adminShiftPOJO.setShift3StartTime(timeSlots.get(two));
                    break;

                default:
                    throw new RuntimeException("Invalid Length of Time Slots.");
            }

            try {
                adminRepository.saveAndFlush(adminShiftPOJO);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return new ShiftCreationResponse(REQUEST_STATUS.SUCCESS, true);
        }

        return new ShiftCreationResponse(REQUEST_STATUS.FAILED, false);
    }

    private boolean getShiftDuration(ShiftCreationRequest request) {
        return request.getShiftDuration() == four || request.getShiftDuration() == six || request.getShiftDuration() == eight;
    }

    @Override
    public ShiftTimingsResponse getShiftTimes() {




        ShiftTimingsResponse shiftTimingsResponse = new ShiftTimingsResponse();
        AdminShiftPOJO adminShiftPOJO = adminRepository.findDistinctTopByOrderByShiftCreationDateDesc();
        if (null != adminShiftPOJO) {
            shiftTimingsResponse.setStatus(REQUEST_STATUS.SUCCESS);
            shiftTimingsResponse.setSlotType(adminShiftPOJO.getSlotType());
            convertTimeFormat(adminShiftPOJO);
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

    private void convertTimeFormat(AdminShiftPOJO adminShiftPOJO) {
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
    }

        private String convertedTime(String shiftTime){
            String finalShift="";
            String[] timeDivision = shiftTime.split(UTIL_CONSTANTS.COLON.getStringVal());
            String am =UTIL_CONSTANTS.AM.getStringVal();
            String colon = UTIL_CONSTANTS.COLON.getStringVal();
            String doubleZero = UTIL_CONSTANTS.DOUBLE_ZERO.getStringVal();
            String pm =UTIL_CONSTANTS.PM.getStringVal();
            String singleZero = UTIL_CONSTANTS.SINGLE_ZERO.getStringVal();

            if((Integer.parseInt(timeDivision[0]))>=12){
                int convertedShiftTime = Integer.parseInt(timeDivision[0])==12?Integer.parseInt(timeDivision[0]):(Integer.parseInt(timeDivision[0])) - 12;
                if(convertedShiftTime<10)
                    finalShift = finalShift+singleZero+convertedShiftTime +colon+doubleZero+pm;
                else
                    finalShift = finalShift+convertedShiftTime +colon+doubleZero+pm;
            }
            else if((Integer.parseInt(timeDivision[0]))<12){
                int convertedShiftTime = Integer.parseInt(timeDivision[0])==00?12:Integer.parseInt(timeDivision[0]);
                if(convertedShiftTime<10)
                    finalShift = finalShift+singleZero+convertedShiftTime+colon+doubleZero+am;
                else
                    finalShift = finalShift+convertedShiftTime+colon+doubleZero+am;
            }
            return finalShift;
        }


    public enum UTIL_CONSTANTS {
        ZERO(0),ONE(1),TWO(2),THREE(3),FOUR(4),
        FIVE(5),SIX(6),SEVEN(7),HYPHEN(" - "),EIGHT(8),
        COLON(":"),AM(" AM"),PM(" PM"),DOUBLE_ZERO("00"),
        SINGLE_ZERO("0");

        private int numVal;
        private String stringVal;

        UTIL_CONSTANTS(int numVal) {
            this.numVal = numVal;
        }
        UTIL_CONSTANTS(String stringVal) {
            this.stringVal = stringVal;
        }

        public String getStringVal(){
            return stringVal;
        }
        public int getNumVal() {
            return numVal;
        }
    }
}

