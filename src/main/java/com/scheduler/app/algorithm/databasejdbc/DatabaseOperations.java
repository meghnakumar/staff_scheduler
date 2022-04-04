package com.scheduler.app.algorithm.databasejdbc;

import com.scheduler.app.algorithm.model.entity.EligibleEmployees;
import com.scheduler.app.algorithm.model.entity.InsertScheduleParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
	private static Connection connection = DatabaseConnection.getConnection();


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
				Time starttime = rs.getTime(COLUMN_INDEX.ONE.getNumVal());
				Time endtime = rs.getTime(COLUMN_INDEX.TWO.getNumVal());
				String empId = rs.getString(COLUMN_INDEX.THREE.getNumVal());
				int totalHours = rs.getInt(COLUMN_INDEX.FOUR.getNumVal());
				list.add(new EligibleEmployees(starttime, endtime, empId, totalHours));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void truncateScheduleOutput(){
		Connection connection=DatabaseConnection.getConnection();
		try {

			String query="truncate table scheduleoutput";
			Statement statement=connection.createStatement();
			statement.execute(query);

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
			ps.setInt(COLUMN_INDEX.ONE.getNumVal(), totalHoursWeekly);
			ps.setInt(COLUMN_INDEX.TWO.getNumVal(), Integer.parseInt(employeeId));
			ps.execute();
			connection.commit();
			ps.close();

		}
		catch(SQLException e) {
			System.out.println("INSERTION FAILED");
			e.printStackTrace();
		}

	}

	public static void insert(InsertScheduleParam insertScheduleParam) {
		Connection connection = DatabaseConnection.getConnection();

		try {
			String query = "Insert into  scheduleoutput (department_id, employee_id, shift_date, start_time, end_time, role_id, emp_hours) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(COLUMN_INDEX.ONE.getNumVal(), insertScheduleParam.deptId);
			ps.setInt(COLUMN_INDEX.TWO.getNumVal(), Integer.parseInt(insertScheduleParam.empno));
			ps.setDate(COLUMN_INDEX.THREE.getNumVal(), insertScheduleParam.shift_date);
			ps.setTime(COLUMN_INDEX.FOUR.getNumVal(), insertScheduleParam.startTime);
			ps.setTime(COLUMN_INDEX.FIVE.getNumVal(), insertScheduleParam.endTime);
			ps.setInt(COLUMN_INDEX.SIX.getNumVal(), Integer.parseInt(insertScheduleParam.roleId));
			ps.setInt(COLUMN_INDEX.SEVEN.getNumVal(), Integer.parseInt(insertScheduleParam.emp_hours));
			ps.execute();
			connection.commit();
			ps.close();

		}
		catch(SQLException e) {
			System.out.println("INSERTION FAILED");
			e.printStackTrace();
		}

	}
	public enum COLUMN_INDEX {
		ONE(1),TWO(2),THREE(3),FOUR(4),
		FIVE(5),SIX(6),SEVEN(7);
		private int numVal;
		COLUMN_INDEX(int numVal) {
			this.numVal = numVal;
		}
		public int getNumVal() {
			return numVal;
		}
	}
}
