package com.scheduler.app.algorithm.databasejdbc;

import com.scheduler.app.algorithm.model.entity.EligibleEmployees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
	public static Connection connection = DatabaseConnection.getConnection();

	public static List<EligibleEmployees> getEligibleEmployees(
			String roleId
			, String shiftDate
			, String deptId) {
		List<EligibleEmployees> list = new ArrayList<>();
		try {

			String query = "SELECT starttime, endtime, empavailablitynew.employee_id, total_hours_weekly FROM CSCI5308_20_DEVINT.empavailablitynew, CSCI5308_20_DEVINT.emphistory  where emphistory.employee_id = empavailablitynew.employee_id and role_id = " + roleId + " and shiftdate = '"+ shiftDate +"' and department_id = '" + deptId + "' order by emphistory.total_hours_weekly;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Time starttime = rs.getTime(1);
				Time endtime = rs.getTime(2);
				String empId = rs.getString(3);
				int totalHours = rs.getInt(4);
				list.add(new EligibleEmployees(starttime, endtime, empId, totalHours));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void truncateEmpHistory(){
		Connection connection=DatabaseConnection.getConnection();
		try {

			String query="truncate table emphistory";
			Statement statement=connection.createStatement();
			statement.executeQuery(query);

			connection.commit();

		}
		catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
	}
	public static void updateEmpHistory(int totalHoursWeekly, String employeeId) {
		Connection connection=DatabaseConnection.getConnection();
		try {
			String query = "update emphistory set total_hours_weekly = ? where employee_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, totalHoursWeekly);
			ps.setInt(2, Integer.parseInt(employeeId));
			ps.executeUpdate();
			connection.commit();
			ps.close();

		}
		catch(SQLException e) {
			System.out.println("INSERTION FAILED");
			e.printStackTrace();
		}

	}
	public static void insert(String deptId, String empno, Time startTime, Time endTime, Date shift_date, String roleId, String emp_hours) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			String query = "Insert into  scheduleoutput (department_id, employee_id, shift_date, start_time, end_time, role_id, emp_hours) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, deptId);
			ps.setInt(2, Integer.parseInt(empno));
			ps.setDate(3, shift_date);
			ps.setTime(4, startTime);
			ps.setTime(5, endTime);
			ps.setInt(6, Integer.parseInt(roleId));
			ps.setInt(7, Integer.parseInt(emp_hours));
			
			connection.commit();
			ps.close();

		}
		catch(SQLException e) {
			System.out.println("INSERTION FAILED");
			e.printStackTrace();
		}
	}
	
}
