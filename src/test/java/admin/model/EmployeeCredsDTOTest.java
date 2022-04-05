package admin.model;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeCredsDTOTest {
    private static final int ROLE_ID = 2;
    private static final int ID = 1;
    private EmployeeCredsDTO employeeCredsDTO;

    @Test
    public void readEmployeeCredsDTOTest() {

        employeeCredsDTO = new EmployeeCredsDTO(Integer.valueOf(ID),"EMP001","emp1@gmail.com","abcxyz",Integer.valueOf(ROLE_ID),"D01");
        employeeCredsDTO.setEmailId("emp1@gmail.com");
        employeeCredsDTO.setDepartmentId("D01");
        employeeCredsDTO.setId(Integer.valueOf(ID));
        employeeCredsDTO.setRoleId(Integer.valueOf(ROLE_ID));
        employeeCredsDTO.setEmployeeNumber("EMP001");

        assertEquals(Integer.valueOf(ID), employeeCredsDTO.getId());
        assertEquals("EMP001", employeeCredsDTO.getEmployeeNumber());
        assertEquals("emp1@gmail.com", employeeCredsDTO.getEmailId());
        assertEquals("abcxyz",employeeCredsDTO.getLoginPassword());
        assertEquals(Integer.valueOf(ROLE_ID),employeeCredsDTO.getRoleId());
        assertEquals("D01",employeeCredsDTO.getDepartmentId());

    }
}


