package login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.USER_TYPE;
import com.scheduler.app.login.controller.LoginController;
import com.scheduler.app.login.model.request.LoginRequest;
import com.scheduler.app.login.model.response.LoginResponse;
import com.scheduler.app.login.service.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController = new LoginController();

    @Mock
    private LoginServiceImpl loginService;

    private LoginRequest loginRequest;
    private LoginResponse loginResponse;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void inputCredentialsTest() throws Exception {
        loginRequest = new LoginRequest();
        loginResponse = new LoginResponse();
        loginRequest.setUserID("demo");
        loginRequest.setPassword("Something");
        loginResponse.setUserType(USER_TYPE.ADMIN);
        when(loginService.inputCredentials(loginRequest)).thenReturn(loginResponse);
        mockMvc.perform(post("/login/request").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andDo(print()).andExpect(status().isOk());

    }
}
