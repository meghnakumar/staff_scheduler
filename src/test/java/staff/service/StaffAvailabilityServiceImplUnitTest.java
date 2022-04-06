package staff.service;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJOId;
import com.scheduler.app.staff.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.staff.model.request.EmployeeAvailabilityExistsRequest;
import com.scheduler.app.staff.model.request.StaffAvailabilityRequest;
import com.scheduler.app.staff.model.response.EmployeeAvailabilityExistsResponse;
import com.scheduler.app.staff.model.response.EmployeeDetailsResponse;
import com.scheduler.app.staff.model.response.StaffAvailabilityResponse;
import com.scheduler.app.staff.service.StaffAvailabilityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StaffAvailabilityServiceImplUnitTest {

    @InjectMocks
    private StaffAvailabilityServiceImpl staffAvailabilityService = new StaffAvailabilityServiceImpl();

    private StaffAvailabilityRequest staffAvailabilityRequest;
    private StaffAvailabilityResponse staffAvailabilityResponse;
    private EmployeeDetailsResponse employeeDetailsResponse;

    @Mock
    private EmpDetailRepository empDetailRepository;

    @Mock
    private EmpAvailabilityRepository empAvailabilityRepository;

    @Mock
    private EmployeeHistoryRepository employeeHistoryRepository;

    private static final String EMP_NUM = "EMP001";
    private static final int ID = 1;
    private static final String AVAILABLE_DATE = "2020-05-05";
    private static final String START_TIME = "08:00:00";
    private static final String END_TIME = "12:00:00";
    private static final String AVAILABLE_DAY = "Friday";
    private static final String EMP_NUM_17 = "EMP0017";
    private static final String EMP_NUM_25 = "EMP0025";

    @Test
    public void inputStaffAvailabilityTest() {
        EmpDetailPOJO emp = new EmpDetailPOJO();
         emp.setId(ID);
        emp.setEmployeeNumber(EMP_NUM);
        staffAvailabilityRequest = new StaffAvailabilityRequest();
        staffAvailabilityRequest.setEmployeeNumber(EMP_NUM);
        staffAvailabilityRequest.setAvailableDate(AVAILABLE_DATE);
        staffAvailabilityRequest.setStartTime(START_TIME);
        staffAvailabilityRequest.setEndTime(END_TIME);
        staffAvailabilityRequest.setAvailableDay(AVAILABLE_DAY);
        List<StaffAvailabilityRequest> list = new ArrayList<>();
        list.add(staffAvailabilityRequest);
        when(empDetailRepository.getTopByEmployeeNumber(staffAvailabilityRequest.getEmployeeNumber())).thenReturn(emp);
        when(empAvailabilityRepository.saveAndFlush(any())).thenReturn(null);
        when(employeeHistoryRepository.saveAndFlush(any())).thenReturn(null);
        staffAvailabilityResponse = staffAvailabilityService.inputStaffAvailability(list);
        assertEquals(staffAvailabilityResponse.getStatus(), (REQUEST_STATUS.SUCCESS));
    }

    @Test
    public void inputStaffAvailabilityWhenEmployeeNotinDBTest() {
        staffAvailabilityRequest = new StaffAvailabilityRequest();
        staffAvailabilityRequest.setEmployeeNumber(EMP_NUM_17);
        staffAvailabilityRequest.setAvailableDay(AVAILABLE_DAY);
        List<StaffAvailabilityRequest> list = new ArrayList<>();
        list.add(staffAvailabilityRequest);
        when(empDetailRepository.getTopByEmployeeNumber(staffAvailabilityRequest.getEmployeeNumber())).thenReturn(null);
        staffAvailabilityResponse = staffAvailabilityService.inputStaffAvailability(list);
        assertEquals(staffAvailabilityResponse.getStatus(), (REQUEST_STATUS.INVALID_REQUEST));
    }

    @Test
    public void fetchEmployeeInfoAvailableinDB() {
        EmpDetailPOJO emp = new EmpDetailPOJO();
        when(empDetailRepository.getTopByEmployeeNumber(EMP_NUM)).thenReturn(emp);
        employeeDetailsResponse = staffAvailabilityService.fetchEmployeeInfo(EMP_NUM);
        assertEquals(REQUEST_STATUS.SUCCESS, employeeDetailsResponse.getStatus());
    }

    @Test
    public void fetchEmployeeInfoNotAvailableinDB() {

        when(empDetailRepository.getTopByEmployeeNumber(EMP_NUM_25)).thenReturn(null);
        employeeDetailsResponse = staffAvailabilityService.fetchEmployeeInfo(EMP_NUM_25);
        assertEquals(REQUEST_STATUS.INVALID_REQUEST, employeeDetailsResponse.getStatus());
    }

    @Test
    public void checkEmployeeAvailabilityTest(){
        EmployeeAvailabilityExistsResponse response = new EmployeeAvailabilityExistsResponse();
        EmployeeAvailabilityExistsRequest request = new EmployeeAvailabilityExistsRequest();
        request.setEmployeeNumber("employee");
        String date_1 = "2022-05-04";
        String date_2 = "2022-05-05";
        List<String> dates = new ArrayList<>();
        dates.add(date_2);
        dates.add(date_1);
        request.setDates(dates);
        when(empAvailabilityRepository.existsById(any())).thenReturn(true);
        response = staffAvailabilityService.checkEmployeeAvailability(request);
        assertEquals(REQUEST_STATUS.SUCCESS,response.getStatus());



    }
}
