package algorithm;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.supervisor.model.request.RequiredRoleHours;
import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
import com.scheduler.app.algorithm.model.response.ShiftDetailsResponse;
import com.scheduler.app.supervisor.model.entity.ShiftDetailsPOJO;
import com.scheduler.app.supervisor.model.repo.DailyShiftRepository;
import com.scheduler.app.supervisor.model.repo.ScheduleRepository;
import com.scheduler.app.supervisor.model.repo.ShiftDetailsRepository;
import com.scheduler.app.supervisor.service.SchedulerServiceImpl;
import com.scheduler.app.staff.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
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

//    @Test
//    public void testGetEmployees(){
//        List<String> departmentList = new ArrayList<>();
//        departmentList.add("1");
//        ScheduleDetails sheduleDetails = new ScheduleDetails("20-08-2019",departmentList);
//        List<ScheduleDetails> output = new ArrayList<>();
//        output.add(sheduleDetails);
//        List<EmpAvailabilityPOJO> list = new ArrayList<>();
//        empAvailabilityPOJO.setAvailableDay("Wednesday");
//        empAvailabilityPOJO.setEmployeeNumber("1234");
//        empAvailabilityPOJO.setEmployeeId(1);
//        empAvailabilityPOJO.setStartTime(16);
//        empAvailabilityPOJO.setEndTime(20);
//        list.add(empAvailabilityPOJO);
//        when(empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(new Date(2020-03-02),"dummy",1)).thenReturn(list);
//        List<DailyShiftPOJO> dailyShiftList = new ArrayList<>();
//        DailyShiftPOJO dailyShiftPOJO = new DailyShiftPOJO();
//        dailyShiftPOJO.setId(1);
//        dailyShiftPOJO.setShiftType("shiftType");
//        DepartmentPOJO departmentPOJO = new DepartmentPOJO();
//        departmentPOJO.setDepartmentName("department");
//        departmentPOJO.setId("1");
//        dailyShiftPOJO.setDepartment(departmentPOJO);
//        dailyShiftPOJO.setRoleId(1);
//        dailyShiftList.add(dailyShiftPOJO);
//        when(dailyShiftRepository.findByShiftDate(new Date(2020-03-02))).thenReturn(dailyShiftList);
//        List<ScheduleDetails> scheduleDetails = new ArrayList<>();
//        scheduleDetails = schedulerService.getEmployees(new Date(2020-02-03));
//        assertNotNull(scheduleDetails);
//    }

//    @Test
//    public void testGetShiftRoleEmployees(){
//        List<EmpAvailabilityPOJO> list = new ArrayList<>();
//        empAvailabilityPOJO.setAvailableDay("Wednesday");
//        empAvailabilityPOJO.setEmployeeNumber("1234");
//        empAvailabilityPOJO.setEmployeeId(1);
//        empAvailabilityPOJO.setStartTime(16);
//        empAvailabilityPOJO.setEndTime(20);
//        list.add(empAvailabilityPOJO);
//        when(empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(new Date(2020-02-03),"1",1))
//                .thenReturn(list);
//        schedulerService.getShiftRoleEmployees(new Date(2020-02-03),"1",1);
//
//    }

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
    public void testGetScheduleByDateTimeDepartment(){
        ScheduleOutputResponse ScheduleOutputResponse = new ScheduleOutputResponse(REQUEST_STATUS.FAILED,false,null);
        ScheduleOutputRequest ScheduleOutputRequest = new ScheduleOutputRequest();
        ScheduleOutputRequest.setShiftDate(LocalDate.ofEpochDay(2020-11-02));
        ScheduleOutputRequest.setShiftTime(LocalTime.MAX);
        ScheduleOutputRequest.setDepartmentId("123");
        ScheduleOutputPOJO ScheduleOutputPOJO = new ScheduleOutputPOJO();
        ScheduleOutputPOJO.setDepartmentId("123");
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(ScheduleOutputPOJO));
        ScheduleOutputResponse = schedulerService.getScheduleByDateTimeDepartment(ScheduleOutputRequest);
        assertEquals(REQUEST_STATUS.SUCCESS, ScheduleOutputResponse.getStatus());
    }

    @Test
    public void testGetScheduleByDateTimeDepartmentWhenScheduleOutputNull(){
        ScheduleOutputResponse ScheduleOutputResponse = new ScheduleOutputResponse(REQUEST_STATUS.FAILED,false,null);
        ScheduleOutputRequest ScheduleOutputRequest = new ScheduleOutputRequest();
        ScheduleOutputRequest.setShiftDate(LocalDate.ofEpochDay(2020-11-02));
        ScheduleOutputRequest.setShiftTime(LocalTime.MAX);
        ScheduleOutputRequest.setDepartmentId("123");
        when(scheduleRepository.findById(any())).thenReturn(Optional.empty());
        ScheduleOutputResponse = schedulerService.getScheduleByDateTimeDepartment(ScheduleOutputRequest);
        assertEquals(REQUEST_STATUS.SUCCESS, ScheduleOutputResponse.getStatus());
    }

    @Test
    public void testGetScheduleByDateTimeDepartmentWhenEmptyRequest(){
        ScheduleOutputResponse ScheduleOutputResponse = new ScheduleOutputResponse(REQUEST_STATUS.SUCCESS,false,null);
        ScheduleOutputRequest ScheduleOutputRequest = new ScheduleOutputRequest();
        ScheduleOutputRequest.setShiftDate(null);
        ScheduleOutputRequest.setShiftTime(null);
        ScheduleOutputRequest.setDepartmentId(null);
        ScheduleOutputResponse = schedulerService.getScheduleByDateTimeDepartment(ScheduleOutputRequest);
        assertEquals(REQUEST_STATUS.INVALID_REQUEST, ScheduleOutputResponse.getStatus());
    }

}
