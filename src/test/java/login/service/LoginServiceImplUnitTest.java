package login.service;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.login.model.request.LoginRequest;
import com.scheduler.app.login.model.response.LoginResponse;
import com.scheduler.app.login.service.LoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceImplUnitTest {

    @InjectMocks
    private LoginServiceImpl loginService = new LoginServiceImpl();

    @Mock
    private EmpDetailRepository empDetailRepository;

    private LoginRequest loginRequest;
    private LoginResponse loginResponse;

    private static final String EMP_NUM = "EMP001";
    private static final String EMP_PWD = "Password";
    private static final int ROLE_ID_0 = 0;
    private static final int ROLE_ID_1 = 1;
    private static final int ROLE_ID_2 = 2;
    private static final int ROLE_ID_3 = 3;
    private static final int ROLE_ID_5 = 5;
    private static final String DEPT_ID_01 ="01";
    private static final String EMP_PWD_abc = "abcd";
    private static final String EMAIL = "demo@gmail.com";

    @Test
    public void inputCredentialsForRole_0_Test(){
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber(EMP_NUM);
        emp.setLoginPassword(EMP_PWD);
        emp.setRoleId(ROLE_ID_0);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(EMP_NUM);
        loginRequest.setPassword(EMP_PWD);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.SUCCESS));
    }

    @Test
    public void inputCredentialsForRole_1_Test(){
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber(EMP_NUM);
        emp.setLoginPassword(EMP_PWD);
        emp.setRoleId(ROLE_ID_1);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(EMP_NUM);
        loginRequest.setPassword(EMP_PWD);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.SUCCESS));
    }

    @Test
    public void inputCredentialsForRole_2_Test(){
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber(EMP_NUM);
        emp.setLoginPassword(EMP_PWD);
        emp.setRoleId(ROLE_ID_2);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(EMP_NUM);
        loginRequest.setPassword(EMP_PWD);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.SUCCESS));
    }

    @Test
    public void inputCredentialsForRole_3_Test(){
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber(EMP_NUM);
        emp.setLoginPassword(EMP_PWD);
        emp.setRoleId(ROLE_ID_3);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(EMP_NUM);
        loginRequest.setPassword(EMP_PWD);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.SUCCESS));
    }

    @Test
    public void inputCredentialsForRole_4_Test(){
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber(EMP_NUM);
        emp.setLoginPassword(EMP_PWD);
        emp.setRoleId(ROLE_ID_5);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(EMP_NUM);
        loginRequest.setPassword(EMP_PWD);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.INVALID_REQUEST));
    }

    @Test
    public void inputCredentialsForIncorrectPasswordTest(){
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber(EMP_NUM);
        emp.setLoginPassword(EMP_PWD_abc);
        emp.setRoleId(ROLE_ID_0);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(EMP_NUM);
        loginRequest.setPassword(EMP_PWD);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(emp);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.INCORRECT_PASSWORD));
    }

    @Test
    public void inputCredentialsForNoDataFromDBTest(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO(ROLE_ID_1,EMP_NUM,EMAIL,EMP_PWD_abc,ROLE_ID_0,DEPT_ID_01);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(EMP_NUM);
        loginRequest.setPassword(EMP_PWD);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(null);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.ERROR));
    }

    @Test
    public void inputCredentialsWhenNoInputDataTest(){
        EmployeeCredsDTO emp = new EmployeeCredsDTO(ROLE_ID_1,EMP_NUM,EMAIL,EMP_PWD,ROLE_ID_1,DEPT_ID_01);
        loginRequest = new LoginRequest();
        loginRequest.setUserID(null);
        loginRequest.setPassword(null);
        when(empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID())).thenReturn(null);
        loginResponse = loginService.inputCredentials(loginRequest);
        assertEquals(loginResponse.getStatus(),(REQUEST_STATUS.BAD_REQUEST));
    }
}
