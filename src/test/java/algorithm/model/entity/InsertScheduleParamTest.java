package algorithm.model.entity;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.EligibleEmployees;
import com.scheduler.app.algorithm.model.entity.InsertScheduleParam;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class InsertScheduleParamTest {
    private InsertScheduleParam insertScheduleParamTest;
    final static private String DEPT_ID = "D01";
    final static private String EMP_NO = "EMP001";
    final static private Time START_TIME = Time.valueOf("16:00:00");
    final static private Time END_TIME = Time.valueOf("20:00:00");
    final static private Date SHIFT_DATE = Date.valueOf("2022-2-2");
    final static private String ROLE_ID = "1";
    final static private String EMP_HOURS = "56";

    @Test
    public void readyInsertScheduleParamTest() {

        insertScheduleParamTest = new InsertScheduleParam();

        insertScheduleParamTest.setDeptId(DEPT_ID);
        insertScheduleParamTest.setEmpno(EMP_NO);
        insertScheduleParamTest.setShift_date(SHIFT_DATE);
        insertScheduleParamTest.setStartTime(START_TIME);
        insertScheduleParamTest.setEndTime(END_TIME);
        insertScheduleParamTest.setRoleId(ROLE_ID);
        insertScheduleParamTest.setEmp_hours(EMP_HOURS);

        assertEquals(DEPT_ID, insertScheduleParamTest.getDeptId());
        assertEquals(EMP_NO, insertScheduleParamTest.getEmpno());
        assertEquals(EMP_HOURS, insertScheduleParamTest.getEmp_hours());
        assertEquals(ROLE_ID, insertScheduleParamTest.getRoleId());
        assertEquals(SHIFT_DATE, insertScheduleParamTest.getShift_date());
        assertEquals(START_TIME, insertScheduleParamTest.getStartTime());
        assertEquals(END_TIME, insertScheduleParamTest.getEndTime());

    }
}



