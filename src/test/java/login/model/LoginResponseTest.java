package login.model;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.USER_TYPE;
import com.scheduler.app.login.model.response.LoginResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginResponseTest {


    @Test
    public void testGetterSetter(){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserType(USER_TYPE.ADMIN);
        loginResponse.setValid(true);
        loginResponse.setEmployeeNumber("employee");
        loginResponse.setDepartmentId("D01");
        loginResponse.setId(1);
        assertEquals(USER_TYPE.ADMIN,loginResponse.getUserType());
        assertEquals(true,loginResponse.isValid());
        assertEquals("D01",loginResponse.getDepartmentId());
        assertEquals("employee",loginResponse.getEmployeeNumber());
        assertEquals(1,loginResponse.getId());
    }
}
