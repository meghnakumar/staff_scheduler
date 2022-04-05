package admin.model;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.staff.model.dto.EmployeeDetailDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDetailDTOTest {


    private static final Integer ID = 1;
    private static final String EMP_NUMBER = "EMP001";
    private static final String EMP_EMAIL = "test@gmail.com";
    private static final String EMP_DEPARTMENT = "D01";
    private static final String EMP_FIRSTNAME = "fname";
    private static final String EMP_LASTNAME = "lname";
    private static final Integer EMP_JOB_TYPE = 1;
    private static final String EMP_PASSWORD = "abcxyz";
    private static final Double EMP_MAX_HOURS = 8.00;
    private static final Long EMP_PHONE_NUMBER = 1234456L;
    private static final Integer EMP_ROLE = 2;
    private static final String EMP_SIN = "1242452";
    private static final byte[] EMP_PHOTO = {'a'};

    @Test
    public void readyEmployeeDetailDTOTest() {
        EmployeeDetailDTO employeeDetailDTO,employeeDetailDTO1;
        LocalDate date =LocalDate.now();
        employeeDetailDTO1 = new EmployeeDetailDTO(ID,EMP_NUMBER,EMP_EMAIL,EMP_PASSWORD,EMP_FIRSTNAME,EMP_LASTNAME,EMP_PHONE_NUMBER,EMP_ROLE,EMP_PHOTO,EMP_SIN,EMP_DEPARTMENT,date,EMP_JOB_TYPE,EMP_MAX_HOURS);
        employeeDetailDTO = new EmployeeDetailDTO();
        employeeDetailDTO.setId(ID);
        employeeDetailDTO.setEmployeeNumber(EMP_NUMBER);
        employeeDetailDTO.setEmailId(EMP_EMAIL);
        employeeDetailDTO.setDepartmentId(EMP_DEPARTMENT);
        employeeDetailDTO.setFirstName(EMP_FIRSTNAME);
        employeeDetailDTO.setLastName(EMP_LASTNAME);
        employeeDetailDTO.setDateOfJoining(date);
        employeeDetailDTO.setJobType(EMP_JOB_TYPE);
        employeeDetailDTO.setLoginPassword(EMP_PASSWORD);
        employeeDetailDTO.setMaxAvailabilityHours(EMP_MAX_HOURS);
        employeeDetailDTO.setPhoneNumber(EMP_PHONE_NUMBER);
        employeeDetailDTO.setRoleId(EMP_ROLE);
        employeeDetailDTO.setSinNumber(EMP_SIN);
        employeeDetailDTO.toString();
        employeeDetailDTO.hashCode();
        assertEquals(ID, employeeDetailDTO.getId());
        assertEquals(EMP_NUMBER, employeeDetailDTO.getEmployeeNumber());
        assertEquals(EMP_EMAIL, employeeDetailDTO.getEmailId());
        assertEquals(EMP_DEPARTMENT, employeeDetailDTO.getDepartmentId());
        assertEquals(EMP_FIRSTNAME, employeeDetailDTO.getFirstName());
        assertEquals(EMP_LASTNAME, employeeDetailDTO.getLastName());
        assertEquals(date, employeeDetailDTO.getDateOfJoining());
        assertEquals(EMP_JOB_TYPE, employeeDetailDTO.getJobType());
        assertEquals( EMP_PASSWORD, employeeDetailDTO.getLoginPassword());
        assertEquals(EMP_MAX_HOURS, employeeDetailDTO.getMaxAvailabilityHours());
        assertEquals(EMP_ROLE, employeeDetailDTO.getRoleId());
        assertEquals(EMP_SIN, employeeDetailDTO.getSinNumber());
    }
}
