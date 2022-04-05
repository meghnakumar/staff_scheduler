package admin.integration;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.controller.AdminController;
import com.scheduler.app.admin.model.request.EmployeeCreationRequest;
import com.scheduler.app.admin.model.request.HolidayCreationRequest;
import com.scheduler.app.supervisor.model.request.ShiftCreationRequest;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.admin.model.response.EmployeeCreationResponse;
import com.scheduler.app.admin.model.response.HolidayCreationResponse;
import com.scheduler.app.supervisor.model.response.ShiftCreationResponse;
import com.scheduler.app.admin.service.HolidayCreationService;
import com.scheduler.app.admin.util.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(
        locations = "classpath:application.test.properties")
public class AdminFunctionalitiesIntegrationTests {

    private static final int SHIFT_DURATION = 4;
    private static final int EMPLOYEE_ID = 123;
    private static final long PHONE_NUMBER = 7L;
    private static final double MAX_AVAILABILITY_HOURS = 12.0;
    @Autowired
    private AdminController adminController;

    private AdminInfoResponse adminInfoResponse;

    @Mock
    private MailService mailService;

    private EmployeeCreationRequest employeeCreationRequest;
    private EmployeeCreationResponse employeeCreationResponse;

    @Autowired
    private HolidayCreationService holidayCreationService;

    private HolidayCreationRequest holidayCreationRequest;
    private HolidayCreationResponse holidayCreationResponse;

    private ShiftCreationRequest shiftCreationRequest;
    private ShiftCreationResponse shiftCreationResponse;

    @Test
    public void getInfoForAdmin(){
        adminInfoResponse = adminController.getStatistic(true);
        assertNotNull(adminInfoResponse.getDeptInfo());
    }

    @Test
    public void checkEmployeeAlreadyExistsInDB(){
        setupEmployeeInputRequest();
        when(mailService.sendMailToEmployee(any(),any(),any())).thenReturn(true);
        employeeCreationResponse = adminController.createEmployee(employeeCreationRequest);
        assertEquals(REQUEST_STATUS.INVALID_REQUEST,employeeCreationResponse.getStatus());
    }

    @Test
    public void addHolidayToDB(){
        String start_date = "2022-05-01";
        String end_date = "2022-05-05";
        Date startdate = Date.valueOf(start_date);
        Date enddate = Date.valueOf(end_date);
        holidayCreationRequest = new HolidayCreationRequest();
        holidayCreationRequest.setHolidayId(1);
        holidayCreationRequest.setHolidayTitle("Christmas");
        holidayCreationRequest.setStartDate(startdate);
        holidayCreationRequest.setEndDate(enddate);
        holidayCreationResponse = adminController.createHoliday(holidayCreationRequest);
        assertEquals(REQUEST_STATUS.SUCCESS,holidayCreationResponse.getStatus());
    }

    @Test
    public void saveShiftDurationToDBTest(){
        shiftCreationRequest = new ShiftCreationRequest();
        shiftCreationRequest.setShiftDuration(SHIFT_DURATION);
        shiftCreationResponse = adminController.logShiftDuration(shiftCreationRequest);
        assertEquals(REQUEST_STATUS.SUCCESS,shiftCreationResponse.getStatus());
    }


    private void setupEmployeeInputRequest() {
        employeeCreationRequest = new EmployeeCreationRequest();
        employeeCreationRequest.setEmployeeID(EMPLOYEE_ID);
        employeeCreationRequest.setEmployeeNumber("DEMO001");
        employeeCreationRequest.setEmailId("demo1@abc.com");
        employeeCreationRequest.setDateOfJoining(LocalDate.now());
        employeeCreationRequest.setDepartmentId("DepartmentId");
        employeeCreationRequest.setFirstName("Some");
        employeeCreationRequest.setLastName("Person");
        employeeCreationRequest.setRoleId(1);
        employeeCreationRequest.setPhoneNumber(PHONE_NUMBER);
        employeeCreationRequest.setJobType(1);
        employeeCreationRequest.setMaxAvailabilityHours(MAX_AVAILABILITY_HOURS);
    }


}
