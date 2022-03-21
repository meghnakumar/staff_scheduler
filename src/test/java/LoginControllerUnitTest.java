

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.constants.USER_TYPE;
import com.scheduler.app.controller.LoginController;
import com.scheduler.app.model.request.LoginRequest;
import com.scheduler.app.model.response.LoginResponse;
import com.scheduler.app.service.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
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
                .content(new ObjectMapper().writeValueAsString(loginRequest))).andDo(print()).andExpect(status().isOk());

    }
}
