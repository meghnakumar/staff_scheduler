package staff;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.staff.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.staff.model.request.StaffAvailabilityRequest;
import com.scheduler.app.staff.model.response.EmployeeDetailsResponse;
import com.scheduler.app.staff.model.response.StaffAvailabilityResponse;
import com.scheduler.app.staff.service.StaffAvailabilityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void inputStaffAvailabilityTest() {
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber("EMP001");
        staffAvailabilityRequest = new StaffAvailabilityRequest();
        staffAvailabilityRequest.setEmployeeNumber("EMP001");
        staffAvailabilityRequest.setAvailableDate("2020-05-05");
        staffAvailabilityRequest.setStartTime("08:00");
        staffAvailabilityRequest.setEndTime("12:00");
        staffAvailabilityRequest.setAvailableDay("Friday");
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
        staffAvailabilityRequest.setEmployeeNumber("EMP0017");
        staffAvailabilityRequest.setAvailableDay("Friday");
        List<StaffAvailabilityRequest> list = new ArrayList<>();
        list.add(staffAvailabilityRequest);
        when(empDetailRepository.getTopByEmployeeNumber(staffAvailabilityRequest.getEmployeeNumber())).thenReturn(null);
        staffAvailabilityResponse = staffAvailabilityService.inputStaffAvailability(list);
        assertEquals(staffAvailabilityResponse.getStatus(), (REQUEST_STATUS.INVALID_REQUEST));
    }

    @Test
    public void fetchEmployeeInfoAvailableinDB() {
        EmpDetailPOJO emp = new EmpDetailPOJO();
        String employeeNumber = "EMP001";
        when(empDetailRepository.getTopByEmployeeNumber(employeeNumber)).thenReturn(emp);
        employeeDetailsResponse = staffAvailabilityService.fetchEmployeeInfo(employeeNumber);
        assertEquals(REQUEST_STATUS.SUCCESS, employeeDetailsResponse.getStatus());
    }

    @Test
    public void fetchEmployeeInfoNotAvailableinDB() {
        String employeeNumber = "EMP025";
        when(empDetailRepository.getTopByEmployeeNumber(employeeNumber)).thenReturn(null);
        employeeDetailsResponse = staffAvailabilityService.fetchEmployeeInfo(employeeNumber);
        assertEquals(REQUEST_STATUS.INVALID_REQUEST, employeeDetailsResponse.getStatus());
    }
}
