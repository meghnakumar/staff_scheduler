

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.StaffAvailabilityResponse;
import com.scheduler.app.service.StaffAvailabilityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public  class StaffAvailabilityServiceImplUnitTest {

    @InjectMocks
    private StaffAvailabilityServiceImpl staffAvailabilityService = new StaffAvailabilityServiceImpl();

    private StaffAvailabilityRequest staffAvailabilityRequest;
    private StaffAvailabilityResponse staffAvailabilityResponse;

    @Mock
    private EmpDetailRepository empDetailRepository;

    @Mock
    private EmpAvailabilityRepository empAvailabilityRepository;

    @Test
    public void inputStaffAvailabilityTest(){
        EmpDetailPOJO emp = new EmpDetailPOJO();
        emp.setEmployeeNumber("EMP001");
        staffAvailabilityRequest = new StaffAvailabilityRequest();
        staffAvailabilityRequest.setEmployeeNumber("EMP001");
        staffAvailabilityRequest.setAvailableDay("Friday");
        List<StaffAvailabilityRequest> list = new ArrayList<>();
        list.add(staffAvailabilityRequest);
        when(empDetailRepository.getTopByEmployeeNumber(staffAvailabilityRequest.getEmployeeNumber())).thenReturn(emp);
        when(empAvailabilityRepository.saveAndFlush(any())).thenReturn(null);
        staffAvailabilityResponse = staffAvailabilityService.inputStaffAvailability(list);
        assertEquals(staffAvailabilityResponse.getStatus(),(REQUEST_STATUS.SUCCESS));
    }

    @Test
    public void inputStaffAvailabilityWhenEmployeeNotinDBTest(){
        staffAvailabilityRequest = new StaffAvailabilityRequest();
        staffAvailabilityRequest.setEmployeeNumber("EMP0017");
        staffAvailabilityRequest.setAvailableDay("Friday");
        List<StaffAvailabilityRequest> list = new ArrayList<>();
        list.add(staffAvailabilityRequest);
        when(empDetailRepository.getTopByEmployeeNumber(staffAvailabilityRequest.getEmployeeNumber())).thenReturn(null);
        staffAvailabilityResponse = staffAvailabilityService.inputStaffAvailability(list);
        assertEquals(staffAvailabilityResponse.getStatus(),(REQUEST_STATUS.INVALID_REQUEST));
    }



}
