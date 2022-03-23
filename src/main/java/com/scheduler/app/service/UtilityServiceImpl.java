package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.entity.AdminShiftPOJO;
import com.scheduler.app.model.entity.DepartmentPOJO;
import com.scheduler.app.model.entity.HolidayPOJO;
import com.scheduler.app.model.repo.AdminRepository;
import com.scheduler.app.model.repo.DepartmentRepository;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.repo.HolidayRepository;
import com.scheduler.app.model.request.ShiftCreationRequest;
import com.scheduler.app.model.response.InfoResponse;
import com.scheduler.app.model.response.ShiftCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class UtilityServiceImpl implements  UtilityService{

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public InfoResponse getStatistics(Boolean onload) {

        if(Boolean.TRUE.equals(onload)){

            Long numEmployees = empDetailRepository.count();
            Long numDepartments = departmentRepository.count();
            List<DepartmentPOJO> deptDetails = departmentRepository.findAll();
            LocalDate date = LocalDate.now();
            Date dateSQL = Date.valueOf(date);
            Date dateEnd = Date.valueOf(date.plusDays(7));
            List<HolidayPOJO> upcomingHolidays = holidayRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(dateSQL, dateEnd);

            Map<String, String> deptInfo = new HashMap<>();
            for (DepartmentPOJO dept: deptDetails) {
                deptInfo.put(dept.getId(), dept.getDepartmentName());
            }

            Map<String, String> holidayInfo = new HashMap<>();
            for (HolidayPOJO holiday: upcomingHolidays) {
                holidayInfo.put(holiday.getStartDate().toString(),holiday.getHolidayTitle());
            }

            return new InfoResponse(numEmployees, numDepartments, deptInfo, holidayInfo, REQUEST_STATUS.SUCCESS);
        } else {

            return new InfoResponse(0l,0l, Collections.emptyMap(), Collections.emptyMap(),REQUEST_STATUS.BAD_REQUEST);
        }
    }

    @Override
    public ShiftCreationResponse logNewShiftDuration(ShiftCreationRequest request) {

        if(request.getShiftDuration() == 4 || request.getShiftDuration() == 6 || request.getShiftDuration() == 8){

            AdminShiftPOJO adminShiftPOJO = new AdminShiftPOJO();
            LocalDate date = LocalDate.now();
            Date dateSQL = Date.valueOf(date);
            List<LocalTime> timeSlots = new ArrayList<>();

            adminShiftPOJO.setShiftCreationDate(dateSQL);
            adminShiftPOJO.setSlotType(request.getShiftDuration());

            DateFormat df = new SimpleDateFormat("HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            int startDate = cal.get(Calendar.DATE);
            while (cal.get(Calendar.DATE) == startDate) {
                String[] splitTime = df.format(cal.getTime()).toString().split(":");
                LocalTime time = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
                timeSlots.add(time);
                cal.add(Calendar.HOUR, request.getShiftDuration());
            }

            switch(timeSlots.size()){

                case 6 : adminShiftPOJO.setShift1StartTime(timeSlots.get(0));
                         adminShiftPOJO.setShift2StartTime(timeSlots.get(1));
                         adminShiftPOJO.setShift3StartTime(timeSlots.get(2));
                         adminShiftPOJO.setShift4StartTime(timeSlots.get(3));
                         adminShiftPOJO.setShift5StartTime(timeSlots.get(4));
                         adminShiftPOJO.setShift6StartTime(timeSlots.get(5));
                         break;

                case 4 : adminShiftPOJO.setShift1StartTime(timeSlots.get(0));
                         adminShiftPOJO.setShift2StartTime(timeSlots.get(1));
                         adminShiftPOJO.setShift3StartTime(timeSlots.get(2));
                         adminShiftPOJO.setShift4StartTime(timeSlots.get(3));
                         break;

                case 3 : adminShiftPOJO.setShift1StartTime(timeSlots.get(0));
                         adminShiftPOJO.setShift2StartTime(timeSlots.get(1));
                         adminShiftPOJO.setShift3StartTime(timeSlots.get(2));
                         break;

                default: throw new RuntimeException("Invalid Length of Time Slots.");
            }

            try{
                adminRepository.saveAndFlush(adminShiftPOJO);
            } catch (Exception exception){
                exception.printStackTrace();
            }
            return new ShiftCreationResponse(REQUEST_STATUS.SUCCESS, true);
        }

        return new ShiftCreationResponse(REQUEST_STATUS.FAILED, false);
    }
}
