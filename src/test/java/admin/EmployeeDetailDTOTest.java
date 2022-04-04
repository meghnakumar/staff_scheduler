package admin;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.admin.model.dto.EmployeeDetailDTO;
import com.scheduler.app.constants.REQUEST_STATUS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDetailDTOTest {

    private EmployeeDetailDTO employeeDetailDTO;

    @Test
    public void readyEmployeeDetailDTOTest() {
        LocalDate date =LocalDate.now();
        employeeDetailDTO = new EmployeeDetailDTO();
        employeeDetailDTO.setId(1);
        employeeDetailDTO.setEmployeeNumber("EMP001");
        employeeDetailDTO.setEmailId("test@gmail.com");
        employeeDetailDTO.setDepartmentId("D01");
        employeeDetailDTO.setFirstName("fname");
        employeeDetailDTO.setLastName("lname");
        employeeDetailDTO.setDateOfJoining(date);
        employeeDetailDTO.setJobType(1);
        employeeDetailDTO.setLoginPassword("test");
        employeeDetailDTO.setMaxAvailabilityHours(8.00);
        employeeDetailDTO.setPhoneNumber(1234456L);
        employeeDetailDTO.setRoleId(2);
        employeeDetailDTO.setSinNumber("1242452");
        employeeDetailDTO.toString();
        employeeDetailDTO.hashCode();
        assertEquals(Integer.valueOf(1), employeeDetailDTO.getId());
        assertEquals("EMP001", employeeDetailDTO.getEmployeeNumber());
        assertEquals("test@gmail.com", employeeDetailDTO.getEmailId());
        assertEquals("D01", employeeDetailDTO.getDepartmentId());
        assertEquals("fname", employeeDetailDTO.getFirstName());
        assertEquals("lname", employeeDetailDTO.getLastName());
        assertEquals(date, employeeDetailDTO.getDateOfJoining());
        assertEquals(Integer.valueOf(1), employeeDetailDTO.getJobType());
        assertEquals( "test", employeeDetailDTO.getLoginPassword());
        assertEquals(Double.valueOf(8.00), employeeDetailDTO.getMaxAvailabilityHours());
        assertEquals(Integer.valueOf(2), employeeDetailDTO.getRoleId());
        assertEquals("1242452", employeeDetailDTO.getSinNumber());
    }
}
