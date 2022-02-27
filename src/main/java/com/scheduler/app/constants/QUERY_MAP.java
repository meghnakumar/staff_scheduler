package com.scheduler.app.constants;

public class QUERY_MAP {

    String FETCH_LOGIN_CREDENTIALS = "select e.employee_id, e.employee_number, e.email_id, e.login_password, e.job_type FROM empdetails e WHERE e.employee_id = ?1 ";


}
