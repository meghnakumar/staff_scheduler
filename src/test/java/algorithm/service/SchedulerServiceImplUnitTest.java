package algorithm.service;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import com.scheduler.app.supervisor.model.request.RequiredRoleHours;
import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
import com.scheduler.app.supervisor.model.response.ShiftDetailsResponse;
import com.scheduler.app.supervisor.model.entity.ShiftDetailsPOJO;
import com.scheduler.app.supervisor.model.repo.DailyShiftRepository;
import com.scheduler.app.supervisor.model.repo.ScheduleRepository;
import com.scheduler.app.supervisor.model.repo.ShiftDetailsRepository;
import com.scheduler.app.supervisor.service.SchedulerServiceImpl;
import com.scheduler.app.staff.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(
        locations = "classpath:application.test.properties")
public class SchedulerServiceImplUnitTest {

    private static final int EMPLOYEE_ID = 123;
    private static final int EPOCH_DAY = 2020 - 11 - 02;
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

    private static final Double TOTAL_EMP_HOURS = 20.9;
    private static final Double EMP_HOURS = 9.0;
    private static final int ROLE_ID_1 = 1;
    private static final int ROLE_ID_2 = 2;
    private static final Time START_TIME= Time.valueOf("16:00:00");
    private static final Time END_TIME = Time.valueOf("20:00:00");
    private static final String DEPT_ID_123 = "123";
    private static final String DEPT_ID_1 = "1";
    private static final Integer SLOT_TYPE = 4;
    private static final String SHIFT_DATE = "2020-09-09";
    private static final String TIME_16 = "16:00";
    private static final String TIME_18 = "18:00";
    private static final LocalDate SHIFT_DATE_2_11 = LocalDate.ofEpochDay(2020-11-02);
    private static final Date Date_4_4 = Date.valueOf("2022-04-04");
    private static final String DEPT_ID_D01 = "D01";
    private static final String DEPT_NAME = "name";
    private static final int EMP_ID_1 = 1;
    private static final Date DATE_3_3= Date.valueOf("2022-03-03");


    @Test
    public void testSaveShiftDetails(){
        ShiftDetailsRequest shiftDetailsRequest = new ShiftDetailsRequest();
        RequiredRoleHours hours = new RequiredRoleHours();
        hours.setEmployeeHours(TOTAL_EMP_HOURS);
        hours.setRoleId(ROLE_ID_1);
        List<RequiredRoleHours> hourList = new ArrayList<>();
        hourList.add(hours);
        shiftDetailsRequest.setShiftRoleHours(hourList);
        shiftDetailsRequest.setSlotType(SLOT_TYPE);
        shiftDetailsRequest.setStartTime(TIME_16);
        shiftDetailsRequest.setDepartmentId(DEPT_ID_1);
        shiftDetailsRequest.setEndTime(TIME_18);
        shiftDetailsRequest.setShiftDate(SHIFT_DATE);
        List<ShiftDetailsPOJO> shiftDetailsPOJOS = new ArrayList<>();
        ShiftDetailsPOJO shiftDetailsPOJO = new ShiftDetailsPOJO();
        shiftDetailsPOJO.setDepartmentId(DEPT_ID_1);
        shiftDetailsPOJOS.add(shiftDetailsPOJO);
        when(shiftDetailsRepository.findByShiftDateAndDepartmentIdAndStartTime(any(),any(),any())).thenReturn(shiftDetailsPOJOS);
        shiftDetailsResponse = schedulerService.saveShiftDetails(shiftDetailsRequest);
        assertEquals(REQUEST_STATUS.SUCCESS, shiftDetailsResponse.getStatus());
    }

    @Test
    public void testGetScheduleByDateTimeDepartment(){
        ScheduleOutputResponse ScheduleOutputResponse = new ScheduleOutputResponse(REQUEST_STATUS.FAILED,false,null);
        ScheduleOutputRequest ScheduleOutputRequest = new ScheduleOutputRequest();
        ScheduleOutputRequest.setShiftDate(LocalDate.ofEpochDay(EPOCH_DAY));
        ScheduleOutputRequest.setShiftTime(LocalTime.MAX);
        ScheduleOutputRequest.setDepartmentId(DEPT_ID_123);
        ScheduleOutputPOJO ScheduleOutputPOJO = new ScheduleOutputPOJO();
        ScheduleOutputPOJO.setDepartmentId(DEPT_ID_123);
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(ScheduleOutputPOJO));
        ScheduleOutputResponse = schedulerService.getScheduleByDateTimeDepartment(ScheduleOutputRequest);
        assertEquals(REQUEST_STATUS.SUCCESS, ScheduleOutputResponse.getStatus());
    }

    @Test
    public void testGetScheduleByDateTimeDepartmentWhenScheduleOutputNull(){
        ScheduleOutputResponse ScheduleOutputResponse = new ScheduleOutputResponse(REQUEST_STATUS.FAILED,false,null);
        ScheduleOutputRequest ScheduleOutputRequest = new ScheduleOutputRequest();
        ScheduleOutputRequest.setShiftDate(SHIFT_DATE_2_11);
        ScheduleOutputRequest.setShiftTime(LocalTime.MAX);
        ScheduleOutputRequest.setDepartmentId(DEPT_ID_123);
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

//    @Test
//    public void testAlgoImplementation(){
//        DailyShiftPOJO dailyShiftPOJO = new DailyShiftPOJO();
//        dailyShiftPOJO.setShiftType(String.valueOf(SLOT_TYPE));
//        dailyShiftPOJO.setShiftDate(Date_4_4);
//        dailyShiftPOJO.setRoleId(ROLE_ID_2);
//        dailyShiftPOJO.setEmployeeHours(EMP_HOURS);
//        DepartmentPOJO departmentPOJO = new DepartmentPOJO();
//        departmentPOJO.setDepartmentName(DEPT_NAME);
//        departmentPOJO.setId(DEPT_ID_D01);
//        dailyShiftPOJO.setDepartment(departmentPOJO);
//        dailyShiftPOJO.setStartTime(START_TIME);
//        dailyShiftPOJO.setEndTime(END_TIME);
//        List<DailyShiftPOJO> dailyShiftPOJOS = new ArrayList<>();
//        dailyShiftPOJOS.add(dailyShiftPOJO);
//        when(dailyShiftRepository.findAll()).thenReturn(dailyShiftPOJOS);
//        boolean result = schedulerService.algoImplementation();
//        assertTrue(result);
//    }
}
