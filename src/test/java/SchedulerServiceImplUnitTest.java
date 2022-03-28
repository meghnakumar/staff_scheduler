import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.entity.*;
import com.scheduler.app.model.repo.*;
import com.scheduler.app.model.request.RequiredRoleHours;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.request.ShiftDetailsRequest;
import com.scheduler.app.model.response.ScheduleResponse;
import com.scheduler.app.model.response.ShiftDetailsResponse;
import com.scheduler.app.service.SchedulerService;
import com.scheduler.app.service.SchedulerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public class SchedulerServiceImplUnitTest {

    @InjectMocks
    private SchedulerServiceImpl schedulerService = new SchedulerServiceImpl();

    @Mock
    private EmpAvailabilityRepository empAvailabilityRepository;

    @Mock
    private DailyShiftRepository dailyShiftRepository;

    @Mock
    private EmployeeHistoryRepository employeeHistoryRepository;

    @Mock
    private EmpDetailRepository empDetailRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ShiftDetailsRepository shiftDetailsRepository;

    private ShiftDetailsResponse shiftDetailsResponse = new ShiftDetailsResponse(REQUEST_STATUS.SUCCESS,true);
    private EmpAvailabilityPOJO empAvailabilityPOJO = new EmpAvailabilityPOJO();
    @Test
    public void testSaveShiftDetails(){
        ShiftDetailsRequest shiftDetailsRequest = new ShiftDetailsRequest();
        RequiredRoleHours hours = new RequiredRoleHours();
        hours.setEmployeeHours(20.9);
        hours.setRoleId(1);
        List<RequiredRoleHours> hourList = new ArrayList<>();
        hourList.add(hours);
        shiftDetailsRequest.setShiftRoleHours(hourList);
        shiftDetailsRequest.setSlotType(4);
        shiftDetailsRequest.setStartTime("16:00");
        shiftDetailsRequest.setDepartmentId("1");
        shiftDetailsRequest.setEndTime("18:00");
        shiftDetailsRequest.setShiftDate("2020-09-09");
        List<ShiftDetailsPOJO> shiftDetailsPOJOS = new ArrayList<>();
        ShiftDetailsPOJO shiftDetailsPOJO = new ShiftDetailsPOJO();
        shiftDetailsPOJO.setDepartmentId("1");
        shiftDetailsPOJOS.add(shiftDetailsPOJO);
        when(shiftDetailsRepository.findByShiftDateAndDepartmentIdAndStartTime(any(),any(),any())).thenReturn(shiftDetailsPOJOS);
        shiftDetailsResponse = schedulerService.saveShiftDetails(shiftDetailsRequest);
        assertEquals(REQUEST_STATUS.SUCCESS, shiftDetailsResponse.getStatus());
    }

    @Test
    public void testGetEmployees(){
        List<String> departmentList = new ArrayList<>();
        departmentList.add("1");
        ScheduleDetails sheduleDetails = new ScheduleDetails("20-08-2019",departmentList);
        List<ScheduleDetails> output = new ArrayList<>();
        output.add(sheduleDetails);
        List<EmpAvailabilityPOJO> list = new ArrayList<>();
        empAvailabilityPOJO.setAvailableDay("Wednesday");
        empAvailabilityPOJO.setEmployeeNumber("1234");
        empAvailabilityPOJO.setEmployeeId(1);
        empAvailabilityPOJO.setStartTime(16);
        empAvailabilityPOJO.setEndTime(20);
        list.add(empAvailabilityPOJO);
        when(empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(new Date(2020-03-02),"dummy",1)).thenReturn(list);
        List<DailyShiftPOJO> dailyShiftList = new ArrayList<>();
        DailyShiftPOJO dailyShiftPOJO = new DailyShiftPOJO();
        dailyShiftPOJO.setId(1);
        dailyShiftPOJO.setShiftType("shiftType");
        DepartmentPOJO departmentPOJO = new DepartmentPOJO();
        departmentPOJO.setDepartmentName("department");
        departmentPOJO.setId("1");
        dailyShiftPOJO.setDepartment(departmentPOJO);
        dailyShiftPOJO.setRoleId(1);
        dailyShiftList.add(dailyShiftPOJO);
        when(dailyShiftRepository.findByShiftDate(new Date(2020-03-02))).thenReturn(dailyShiftList);
        List<ScheduleDetails> scheduleDetails = new ArrayList<>();
        scheduleDetails = schedulerService.getEmployees(new Date(2020-02-03));
        assertNotNull(scheduleDetails);
    }

    @Test
    public void testGetShiftRoleEmployees(){
        List<EmpAvailabilityPOJO> list = new ArrayList<>();
        empAvailabilityPOJO.setAvailableDay("Wednesday");
        empAvailabilityPOJO.setEmployeeNumber("1234");
        empAvailabilityPOJO.setEmployeeId(1);
        empAvailabilityPOJO.setStartTime(16);
        empAvailabilityPOJO.setEndTime(20);
        list.add(empAvailabilityPOJO);
        when(empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(new Date(2020-02-03),"1",1))
                .thenReturn(list);
        schedulerService.getShiftRoleEmployees(new Date(2020-02-03),"1",1);

    }

    @Test
    public void testGetEmpHistory(){
        List<EmpHistoryPOJO> empHistoryPOJOS = new ArrayList<>();
        EmpHistoryPOJO empHistoryPOJO = new EmpHistoryPOJO();
        empHistoryPOJO.setId(1);
        empHistoryPOJO.setEmployeeId(123);
        empHistoryPOJOS.add(empHistoryPOJO);
        when(employeeHistoryRepository.findEmpHistoryById(1)).thenReturn(empHistoryPOJOS);
        schedulerService.getEmpHistory(1);
    }

    @Test
    public void testGetScheduleByDateTime(){
        ScheduleResponse scheduleResponse = new ScheduleResponse(REQUEST_STATUS.FAILED,false,null);
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setShiftDate(LocalDate.ofEpochDay(2020-11-02));
        scheduleRequest.setShiftTime(LocalTime.MAX);
        SchedulePOJO schedulePOJO = new SchedulePOJO();
        schedulePOJO.setPrimaryEmpNo(123);
        DepartmentPOJO departmentPOJO = new DepartmentPOJO();
        departmentPOJO.setId("1");
        schedulePOJO.setDepartment(departmentPOJO);
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(schedulePOJO));
        scheduleResponse = schedulerService.getScheduleByDateTime(scheduleRequest);
        assertEquals(REQUEST_STATUS.SUCCESS, scheduleResponse.getStatus());
    }

    @Test
    public void testGetScheduleByDateTimeWhenScheduleOutputNull(){
        ScheduleResponse scheduleResponse = new ScheduleResponse(REQUEST_STATUS.FAILED,false,null);
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setShiftDate(LocalDate.ofEpochDay(2020-11-02));
        scheduleRequest.setShiftTime(LocalTime.MAX);
        when(scheduleRepository.findById(any())).thenReturn(Optional.empty());
        scheduleResponse = schedulerService.getScheduleByDateTime(scheduleRequest);
        assertEquals(REQUEST_STATUS.SUCCESS, scheduleResponse.getStatus());
    }

    @Test
    public void testGetScheduleByDateTimeWhenEmptyRequest(){
        ScheduleResponse scheduleResponse = new ScheduleResponse(REQUEST_STATUS.SUCCESS,true,null);
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setShiftDate(null);
        scheduleRequest.setShiftTime(null);
        scheduleResponse = schedulerService.getScheduleByDateTime(scheduleRequest);
        assertEquals(REQUEST_STATUS.INVALID_REQUEST, scheduleResponse.getStatus());
    }

}
