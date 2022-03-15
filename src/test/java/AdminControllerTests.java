
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.controller.AdminController;
import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.response.HolidayCreationResponse;
import com.scheduler.app.service.EmployeeCreationService;
import com.scheduler.app.service.HolidayCreationService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public class AdminControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    private AdminController adminController = new AdminController();

    private EmployeeCreationRequest request = new EmployeeCreationRequest();

    @Mock
    private EmployeeCreationService employeeCreationService;

    private HolidayCreationResponse holidayReponse;

    private HolidayCreationRequest holidayCreationRequest = new HolidayCreationRequest();
    @Mock
    private HolidayCreationService holidayCreationService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeCreationResponse employeeCreationResponse = new EmployeeCreationResponse(REQUEST_STATUS.SUCCESS,true);
        request.setEmployeeNumber("123456");
        when(employeeCreationService.createNewEmployee(request)).thenReturn(employeeCreationResponse);
        mockMvc.perform(post("/admin/create/employee").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(request))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testDeclareHoliday() throws Exception {
        holidayCreationRequest.setHolidayTitle("Holiday");
        holidayReponse = new HolidayCreationResponse(REQUEST_STATUS.SUCCESS, true);
        when(holidayCreationService.addNewHoliday(holidayCreationRequest)).thenReturn(holidayReponse);
        mockMvc.perform(post("/admin/create/holiday").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(holidayCreationRequest))).andDo(print()).andExpect(status().isOk());
    }

}
