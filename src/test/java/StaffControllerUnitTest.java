

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.controller.StaffController;
import com.scheduler.app.model.request.StaffAvailabilitiesRequest;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.StaffAvailabilityResponse;
import com.scheduler.app.service.StaffAvailabilityService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public class StaffControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private StaffController staffController = new StaffController();

    @Mock
    private StaffAvailabilityService staffAvailabilityService;

    private StaffAvailabilityResponse staffAvailabilityResponse = new StaffAvailabilityResponse();
    private StaffAvailabilityRequest staffAvailabilityRequest = new StaffAvailabilityRequest();
    private StaffAvailabilitiesRequest staffAvailabilitiesRequest = new StaffAvailabilitiesRequest();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(staffController).build();
    }

    @Test
    public void inputStaffAvailabilityTest() throws Exception {
        staffAvailabilityRequest.setAvailableDay("Wednesday");
        staffAvailabilityRequest.setEmployeeNumber("123456");
        List<StaffAvailabilityRequest> requests = new ArrayList<>();
        requests.add(staffAvailabilityRequest);
        staffAvailabilitiesRequest.setStaffAvailabilityRequest(requests);
        staffAvailabilityResponse.setStatus(REQUEST_STATUS.SUCCESS);
        when(staffAvailabilityService.inputStaffAvailability(requests)).thenReturn(staffAvailabilityResponse);
        mockMvc.perform(post("/staff/input/availability").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(staffAvailabilitiesRequest))).andDo(print()).andExpect(status().isOk());


    }
}
