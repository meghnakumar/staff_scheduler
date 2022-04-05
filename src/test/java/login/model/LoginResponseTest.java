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
    private static final String EMP_NUMBER = "employee";
    private static final String DEPT_ID="D01";
    private static final int ID = 1;
    @Test
    public void testGetterSetter(){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserType(USER_TYPE.ADMIN);
        loginResponse.setValid(true);
        loginResponse.setEmployeeNumber(EMP_NUMBER);
        loginResponse.setDepartmentId(DEPT_ID);
        loginResponse.setId(ID);
        assertEquals(USER_TYPE.ADMIN,loginResponse.getUserType());
        assertEquals(true,loginResponse.isValid());
        assertEquals(DEPT_ID,loginResponse.getDepartmentId());
        assertEquals(EMP_NUMBER,loginResponse.getEmployeeNumber());
        assertEquals(ID,loginResponse.getId());
    }
}
