package admin.service;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.admin.model.request.EmployeeCreationRequest;
import com.scheduler.app.admin.model.response.EmployeeCreationResponse;
import com.scheduler.app.admin.service.EmployeeCreationServiceImpl;
import com.scheduler.app.admin.util.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeCreationServiceImplUnitTest {

    private static final int EMPLOYEE_ID = 123;
    private static final long PHONE_NUMBER = 7L;
    private static final int JOB_TYPE = 1;
    private static final double MAX_AVAILABILITY_HOURS = 12.0;
    @InjectMocks
    private EmployeeCreationServiceImpl employeeCreationService = new EmployeeCreationServiceImpl();

    @Mock
    private EmpDetailRepository empDetailRepository;

    @Mock
    private EmpDetailPOJO empDetailPOJO;

    @Mock
    private MailService mailService;

    private EmployeeCreationRequest employeeCreationRequest;
    private EmployeeCreationResponse employeeCreationResponse;

    @Test
    public void createNewEmployeeTest(){
        setupEmployeeInputRequest();
        when(empDetailRepository.getDistinctFirstByEmployeeNumber(employeeCreationRequest.getEmployeeNumber())).thenReturn(null);
        when(empDetailRepository.saveAndFlush(any())).thenReturn(empDetailPOJO);
        /*when(mailService.sendMailToEmployee(any(),any(),any())).thenReturn(true);*/
        employeeCreationResponse = employeeCreationService.createNewEmployee(employeeCreationRequest);
        assertEquals(employeeCreationResponse.getStatus(),(REQUEST_STATUS.SUCCESS));
    }

    @Test
    public void createNewEmployeeFailTest(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO(1,"EMP001","demo@gmail.com","password",1,"01");
        setupEmployeeInputRequest();
        when(empDetailRepository.getDistinctFirstByEmployeeNumber(employeeCreationRequest.getEmployeeNumber())).thenReturn(emp);
        when(empDetailRepository.saveAndFlush(any())).thenReturn(empDetailPOJO);
        employeeCreationResponse = employeeCreationService.createNewEmployee(employeeCreationRequest);
        assertEquals(employeeCreationResponse.getStatus(),(REQUEST_STATUS.INVALID_REQUEST));
    }


    private void setupEmployeeInputRequest() {
        employeeCreationRequest = new EmployeeCreationRequest();
        employeeCreationRequest.setEmployeeID(EMPLOYEE_ID);
        employeeCreationRequest.setEmployeeNumber("EMP001");
        employeeCreationRequest.setEmailId("demo@abc.com");
        employeeCreationRequest.setDateOfJoining(LocalDate.now());
        employeeCreationRequest.setDepartmentId("DepartmentId");
        employeeCreationRequest.setFirstName("Some");
        employeeCreationRequest.setLastName("Person");
        employeeCreationRequest.setPhoneNumber(PHONE_NUMBER);
        employeeCreationRequest.setJobType(JOB_TYPE);
        employeeCreationRequest.setMaxAvailabilityHours(MAX_AVAILABILITY_HOURS);
    }
}
