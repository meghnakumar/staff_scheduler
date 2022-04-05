package utility.service;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.entity.AdminShiftPOJO;
import com.scheduler.app.supervisor.model.response.SupervisorInfoResponse;
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
import com.scheduler.app.utility.service.UtilityServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilityServiceImplUnitTest {

    /**
     * The enum Employee Detail values.
     */
    public enum EMPLOYEE_DETAILS_MAP {

        INVALID_DURATION(9),

        SHIFT_DURATION_FOUR(4),

        SHIFT_DURATION_EIGHT(8) ,

        SHIFT_DURATION_SIX(6),

        EMPLOYEE_COUNT(2L),

        DEPARTMENT_COUNT(5L),

        DATE(20);

        private long longVal;
        private int numVal;

        EMPLOYEE_DETAILS_MAP(int numVal) {
            this.numVal = numVal;
        }
        EMPLOYEE_DETAILS_MAP(long longVal){
            this.longVal = longVal;
        }

        public int getNumVal() {
            return numVal;
        }
        public long getLongVal(){
            return longVal;
        }
    }

    @InjectMocks
    private UtilityServiceImpl utilityService = new UtilityServiceImpl();

    @Mock
    private EmpDetailRepository empDetailRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private HolidayRepository holidayRepository;

    @Mock
    private AdminRepository adminRepository;

    ShiftCreationResponse shiftCreationResponse = new ShiftCreationResponse(REQUEST_STATUS.FAILED,false);
    AdminInfoResponse adminInfoResponse = new AdminInfoResponse();
    ShiftTimingsResponse shiftTimingsResponse = new ShiftTimingsResponse(REQUEST_STATUS.SUCCESS,4,null);

    @Test
    public void testSuccessGetStatistics(){
        DepartmentPOJO departmentPOJO = new DepartmentPOJO();
        departmentPOJO.setId("1");
        departmentPOJO.setDepartmentName("Android");
        List<DepartmentPOJO> departmentList = new ArrayList<>();
        departmentList.add(departmentPOJO);
        when(empDetailRepository.count()).thenReturn(EMPLOYEE_DETAILS_MAP.EMPLOYEE_COUNT.getLongVal());
        when(departmentRepository.count()).thenReturn(EMPLOYEE_DETAILS_MAP.DEPARTMENT_COUNT.getLongVal());
        when(departmentRepository.findAll()).thenReturn(departmentList);
        HolidayPOJO holidayPOJO = new HolidayPOJO();
        holidayPOJO.setId(1);
        holidayPOJO.setHolidayTitle("Christmas");
        holidayPOJO.setStartDate(new Date(EMPLOYEE_DETAILS_MAP.DATE.getNumVal()));
        List<HolidayPOJO> holidayList = new ArrayList<>();
        holidayList.add(holidayPOJO);
        when(holidayRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(any(),any())).thenReturn(holidayList);
        adminInfoResponse = utilityService.getStatistics(true);
        Assert.assertNotNull(adminInfoResponse);
    }

    @Test
    public void testFailGetStatistics(){
        adminInfoResponse = utilityService.getStatistics(false);
        assertEquals(REQUEST_STATUS.BAD_REQUEST, adminInfoResponse.getStatus());
    }

    @Test
    public void testGetSupervisorStatistics(){
        SupervisorInfoResponse supervisorInfoResponse = new SupervisorInfoResponse();
        supervisorInfoResponse = utilityService.getSupervisorStats(false,null);
        assertEquals(REQUEST_STATUS.BAD_REQUEST, supervisorInfoResponse.getStatus());
    }

    @Test
    public void testLogNewShiftDuration6(){
        ShiftCreationRequest shiftCreationRequest = new ShiftCreationRequest();
        shiftCreationRequest.setShiftDuration(EMPLOYEE_DETAILS_MAP.SHIFT_DURATION_SIX.getNumVal());
        shiftCreationResponse = utilityService.logNewShiftDuration(shiftCreationRequest);
        assertEquals(REQUEST_STATUS.SUCCESS,shiftCreationResponse.getStatus());
    }

    @Test
    public void testLogNewShiftDuration8(){
        ShiftCreationRequest shiftCreationRequest = new ShiftCreationRequest();
        shiftCreationRequest.setShiftDuration(EMPLOYEE_DETAILS_MAP.SHIFT_DURATION_EIGHT.getNumVal());
        shiftCreationResponse = utilityService.logNewShiftDuration(shiftCreationRequest);
        assertEquals(REQUEST_STATUS.SUCCESS,shiftCreationResponse.getStatus());
    }
    @Test
    public void testLogNewShiftDuration4(){
        ShiftCreationRequest shiftCreationRequest = new ShiftCreationRequest();
        shiftCreationRequest.setShiftDuration(EMPLOYEE_DETAILS_MAP.SHIFT_DURATION_FOUR.getNumVal());
        shiftCreationResponse = utilityService.logNewShiftDuration(shiftCreationRequest);
        assertEquals(REQUEST_STATUS.SUCCESS,shiftCreationResponse.getStatus());
    }

    @Test
    public void testLogNewShiftDurationInvalidSlot() throws RuntimeException{
        ShiftCreationRequest shiftCreationRequest = new ShiftCreationRequest();
        shiftCreationRequest.setShiftDuration(EMPLOYEE_DETAILS_MAP.INVALID_DURATION.getNumVal());
        shiftCreationResponse = utilityService.logNewShiftDuration(shiftCreationRequest);
    }

    @Test
    public void testGetShiftTime_4(){
        AdminShiftPOJO adminShiftPOJO = new AdminShiftPOJO();
        adminShiftPOJO.setSlotType(EMPLOYEE_DETAILS_MAP.SHIFT_DURATION_FOUR.getNumVal());
        adminShiftPOJO.setShift1StartTime(LocalTime.NOON);
        adminShiftPOJO.setShift2StartTime(LocalTime.now());
        adminShiftPOJO.setShift3StartTime(LocalTime.MAX);
        adminShiftPOJO.setShift4StartTime(LocalTime.MIN);
        adminShiftPOJO.setShift5StartTime(LocalTime.MIDNIGHT);
        adminShiftPOJO.setShift6StartTime(LocalTime.NOON);
        when(adminRepository.findDistinctTopByOrderByShiftCreationDateDesc()).thenReturn(adminShiftPOJO);
        shiftTimingsResponse = utilityService.getShiftTimes();
        assertNotNull(shiftTimingsResponse.getShiftTimes());
        //assertEquals(REQUEST_STATUS.SUCCESS,shiftTimingsResponse.getStatus());
    }

    @Test
    public void testGetShiftTime_6(){
        AdminShiftPOJO adminShiftPOJO = new AdminShiftPOJO();
        adminShiftPOJO.setSlotType(EMPLOYEE_DETAILS_MAP.SHIFT_DURATION_SIX.getNumVal());
        adminShiftPOJO.setShift1StartTime(LocalTime.NOON);
        adminShiftPOJO.setShift2StartTime(LocalTime.now());
        adminShiftPOJO.setShift3StartTime(LocalTime.MAX);
        adminShiftPOJO.setShift4StartTime(LocalTime.MIN);
        adminShiftPOJO.setShift5StartTime(LocalTime.MIDNIGHT);
        adminShiftPOJO.setShift6StartTime(LocalTime.NOON);
        when(adminRepository.findDistinctTopByOrderByShiftCreationDateDesc()).thenReturn(adminShiftPOJO);
        shiftTimingsResponse = utilityService.getShiftTimes();
        assertNotNull(shiftTimingsResponse.getShiftTimes());
    }

    @Test
    public void testGetShiftTime_8(){
        AdminShiftPOJO adminShiftPOJO = new AdminShiftPOJO();
        adminShiftPOJO.setSlotType(EMPLOYEE_DETAILS_MAP.SHIFT_DURATION_EIGHT.getNumVal());
        adminShiftPOJO.setShift1StartTime(LocalTime.NOON);
        adminShiftPOJO.setShift2StartTime(LocalTime.now());
        adminShiftPOJO.setShift3StartTime(LocalTime.MAX);
        adminShiftPOJO.setShift4StartTime(LocalTime.MIN);
        adminShiftPOJO.setShift5StartTime(LocalTime.MIDNIGHT);
        adminShiftPOJO.setShift6StartTime(LocalTime.NOON);
        when(adminRepository.findDistinctTopByOrderByShiftCreationDateDesc()).thenReturn(adminShiftPOJO);
        shiftTimingsResponse = utilityService.getShiftTimes();
        assertNotNull(shiftTimingsResponse.getShiftTimes());
    }

    @Test
    public void testFailGetShiftTime(){
        when(adminRepository.findDistinctTopByOrderByShiftCreationDateDesc()).thenReturn(null);
        shiftTimingsResponse = utilityService.getShiftTimes();
        assertEquals(REQUEST_STATUS.FAILED,shiftTimingsResponse.getStatus());
    }
}
