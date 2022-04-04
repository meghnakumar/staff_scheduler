package admin.model.entity;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeCredsDTOTest {
    private EmployeeCredsDTO employeeCredsDTO;
    @Test
    public void readEmployeeCredsDTOTest() {

        employeeCredsDTO = new EmployeeCredsDTO(Integer.valueOf(1),"EMP001","emp1@gmail.com","abcxyz",Integer.valueOf(2),"D01");
        employeeCredsDTO.setEmailId("emp1@gmail.com");
        employeeCredsDTO.setDepartmentId("D01");
        employeeCredsDTO.setId(Integer.valueOf(1));
        employeeCredsDTO.setRoleId(Integer.valueOf(2));
        employeeCredsDTO.setEmployeeNumber("EMP001");

        assertEquals(Integer.valueOf(1), employeeCredsDTO.getId());
        assertEquals("EMP001", employeeCredsDTO.getEmployeeNumber());
        assertEquals("emp1@gmail.com", employeeCredsDTO.getEmailId());
        assertEquals("abcxyz",employeeCredsDTO.getLoginPassword());
        assertEquals(Integer.valueOf(2),employeeCredsDTO.getRoleId());
        assertEquals("D01",employeeCredsDTO.getDepartmentId());

    }
}


