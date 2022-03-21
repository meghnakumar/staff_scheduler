import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.request.LoginRequest;
import com.scheduler.app.model.response.LoginResponse;
import com.scheduler.app.service.LoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public class LoginServiceImplUnitTest {

    @InjectMocks
    private LoginServiceImpl loginService = new LoginServiceImpl();

    @Mock
    private EmpDetailRepository empDetailRepository;

    private LoginRequest loginRequest;
    private LoginResponse loginResponse;

    @Test
    public void inputCredentialsForRole_0_Test(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO();
        emp.setEmployeeNumber("EMP001");
        emp.setLoginPassword("Password");
        emp.setRoleId(0);
        loginRequest = new LoginRequest();
        loginRequest.setUserID("EMP001");
        loginRequest.setPassword("Password");
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.SUCCESS));

    }

    @Test
    public void inputCredentialsForRole_1_Test(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO();
        emp.setEmployeeNumber("EMP001");
        emp.setLoginPassword("Password");
        emp.setRoleId(1);
        loginRequest = new LoginRequest();
        loginRequest.setUserID("EMP001");
        loginRequest.setPassword("Password");
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.SUCCESS));
    }
    @Test
    public void inputCredentialsForRole_2_Test(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO();
        emp.setEmployeeNumber("EMP001");
        emp.setLoginPassword("Password");
        emp.setRoleId(2);
        loginRequest = new LoginRequest();
        loginRequest.setUserID("EMP001");
        loginRequest.setPassword("Password");
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.SUCCESS));

    }

    @Test
    public void inputCredentialsForRole_3_Test(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO();
        emp.setEmployeeNumber("EMP001");
        emp.setLoginPassword("Password");
        emp.setRoleId(3);
        loginRequest = new LoginRequest();
        loginRequest.setUserID("EMP001");
        loginRequest.setPassword("Password");
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.INVALID_REQUEST));

    }
    @Test
    public void inputCredentialsForIncorrectPasswordTest(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO();
        emp.setEmployeeNumber("EMP001");
        emp.setLoginPassword("abcd");
        emp.setRoleId(0);
        loginRequest = new LoginRequest();
        loginRequest.setUserID("EMP001");
        loginRequest.setPassword("Password");
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.INCORRECT_PASSWORD));
    }

    @Test
    public void inputCredentialsForNoDataFromDBTest(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO();
        emp.setEmployeeNumber("EMP001");
        emp.setLoginPassword("abcd");
        emp.setRoleId(0);
        loginRequest = new LoginRequest();
        loginRequest.setUserID("EMP001");
        loginRequest.setPassword("Password");
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(null);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.ERROR));
    }

    @Test
    public void inputCredentialsWhenNoInputDataTest(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO();
        emp.setEmployeeNumber("EMP001");
        loginRequest = new LoginRequest();
        loginRequest.setUserID(null);
        loginRequest.setPassword(null);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(null);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.BAD_REQUEST));
    }
}
