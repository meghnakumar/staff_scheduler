package utility;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.utility.controller.UtilityController;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.supervisor.model.response.ShiftTimingsResponse;
import com.scheduler.app.utility.service.UtilityService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilityControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UtilityController utilityController;

    @Mock
    private UtilityService utilityService;

    private ShiftDetailsRequest shiftDetailsRequest = new ShiftDetailsRequest();


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(utilityController).build();
    }

    @Test
    public void testGetShiftTimes() throws Exception {
        ShiftTimingsResponse shiftTimingsResponse = new ShiftTimingsResponse();
        when(utilityService.getShiftTimes()).thenReturn(shiftTimingsResponse);
        mockMvc.perform(get("/utility/fetch/shifts"))
                .andDo(print()).andExpect(status().isOk());
    }

}
