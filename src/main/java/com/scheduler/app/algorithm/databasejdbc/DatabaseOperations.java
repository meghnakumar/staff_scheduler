package com.scheduler.app.algorithm.databasejdbc;

import com.scheduler.app.algorithm.model.entity.EligibleEmployees;
import com.scheduler.app.algorithm.model.entity.InsertScheduleParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type - Database Operations.
 * This class is responsible for performing the DB operations, transactions or otherwise,
 * on the database to which a connection has been established.
 */
public class DatabaseOperations {

	// Global connection object - obtains a new connection using the DatabaseConnection class.
	private static Connection connection = new DatabaseConnection().openConnection();

	/**
	 * The enum for mapping Column indices in the tables.
	 */
	public enum COLUMN_INDEX {

		COLUMN_ONE(1),

		COLUMN_TWO(2),

		COLUMN_THREE(3),

		COLUMN_FOUR(4),

		COLUMN_FIVE(5),

		COLUMN_SIX(6),

		COLUMN_SEVEN(7);

		private int numVal;

		COLUMN_INDEX(int numVal) {
			this.numVal = numVal;
		}
		public int getNumVal() {
			return numVal;
		}
	}


	/**
	 * Gets the employees eligible to be considered for the shifts.
	 *
	 * @param roleId    the role id
	 * @param shiftDate the shift date
	 * @param deptId    the dept id
	 * @return the list of eligible employees
	 */
	public static List<EligibleEmployees> getEligibleEmployees(String roleId, String shiftDate, String deptId) {

		List<EligibleEmployees> list = new ArrayList<>();
		try {

			//The Query
			String query = "SELECT start_time, end_time, empavailability.employee_id, total_hours_weekly FROM empavailability, emphistory  where emphistory.employee_id = empavailability.employee_id and role_id = " + roleId + " and shift_date = '"+ shiftDate +"' and department_id = '" + deptId + "' order by emphistory.total_hours_weekly;";
			Statement statement = connection.createStatement();

			//Execute the query to get eligible employees
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Time starttime = rs.getTime(COLUMN_INDEX.COLUMN_ONE.getNumVal());
				Time endtime = rs.getTime(COLUMN_INDEX.COLUMN_TWO.getNumVal());
				String empId = rs.getString(COLUMN_INDEX.COLUMN_THREE.getNumVal());
				int totalHours = rs.getInt(COLUMN_INDEX.COLUMN_FOUR.getNumVal());
				list.add(new EligibleEmployees(starttime, endtime, empId, totalHours));
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		//Return the list of employees obtained after processing the result set.
		return list;
	}

	/**
	 * Truncate schedule output.
	 * This method cleans the schedule output table every time a new schedule is generated.
	 * This makes sure that the schedule being sent to the front-end will always have the latest generated schedule.
	 */
	public static void truncateScheduleOutput(){

		try {

			String query="truncate table scheduleoutput";
			Statement statement = connection.createStatement();
			//Execute the truncation of the table values.
			statement.execute(query);
			connection.commit();
			statement.close();
		}
		catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
	}

	/**
	 * Updates the Employee History table for each employee to log the accumulation
	 * of the number of hours that employee has worked in the last week.
	 * This is done to later use the data to make sure all employees get a fair distribution of work hours.
	 *
	 * @param totalHoursWeekly the total hours weekly
	 * @param employeeId       the employee id
	 */
	public static void updateEmpHistory(int totalHoursWeekly, String employeeId) {

		try {

			String query = "update emphistory set total_hours_weekly = ? where employee_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(COLUMN_INDEX.COLUMN_ONE.getNumVal(), totalHoursWeekly);
			ps.setInt(COLUMN_INDEX.COLUMN_TWO.getNumVal(), Integer.parseInt(employeeId));
			//Execute the update query to update the employee history data.
			ps.execute();
			connection.commit();
			ps.close();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Inserts the final generated output to the database table..
	 *
	 * @param insertScheduleParam the insert schedule param
	 */
	public static void insertFinalSchedule(InsertScheduleParam insertScheduleParam) {


		try {

			String query = "Insert into  scheduleoutput (department_id, employee_id, shift_date, start_time, end_time, role_id, emp_hours) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(COLUMN_INDEX.COLUMN_ONE.getNumVal(), insertScheduleParam.deptId);
			ps.setInt(COLUMN_INDEX.COLUMN_TWO.getNumVal(), Integer.parseInt(insertScheduleParam.empno));
			ps.setDate(COLUMN_INDEX.COLUMN_THREE.getNumVal(), insertScheduleParam.shift_date);
			ps.setTime(COLUMN_INDEX.COLUMN_FOUR.getNumVal(), insertScheduleParam.startTime);
			ps.setTime(COLUMN_INDEX.COLUMN_FIVE.getNumVal(), insertScheduleParam.endTime);
			ps.setInt(COLUMN_INDEX.COLUMN_SIX.getNumVal(), Integer.parseInt(insertScheduleParam.roleId));
			ps.setInt(COLUMN_INDEX.COLUMN_SEVEN.getNumVal(), Integer.parseInt(insertScheduleParam.emp_hours));
			//Execute the insert query.
			ps.execute();
			connection.commit();
			ps.close();

		}

		catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
