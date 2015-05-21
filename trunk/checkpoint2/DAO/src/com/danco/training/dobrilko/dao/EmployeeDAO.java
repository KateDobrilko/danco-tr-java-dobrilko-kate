package com.danco.training.dobrilko.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.danco.training.dobrilko.abstractjdbcdao.AbstractJDBCDao;
import com.danco.training.dobrilko.entity.Employee;
import com.danco.training.dobrilko.persistexception.PersistException;

public class EmployeeDAO extends AbstractJDBCDao<Employee, Integer> {

	@Override
	public String getSelectQuery() {

		return "SELECT * FROM danco.Employee";

	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE danco.Employee SET f_name=?, l_name=?, b_date=?, gender=?, title=? WHERE id= ?;";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO danco.Employee (id, f_name, l_name, b_date, gender, title) \n"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Employee object) throws PersistException {
		try {
			statement.setInt(1, object.getId());
			statement.setString(2, object.getFirstName());
			statement.setString(3, object.getLastName());
			statement.setDate(4, new java.sql.Date(object.getBirthDate()
					.getTime()));
			statement.setBoolean(5, object.getGender());
			statement.setString(6, object.getTitle());
		} catch (Exception e) {

		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Employee object) throws PersistException {
		try {
			statement.setInt(6, object.getId());
			statement.setString(1, object.getFirstName());
			statement.setString(2, object.getLastName());
			statement.setDate(3, new java.sql.Date(object.getBirthDate()
					.getTime()));
			statement.setBoolean(4, object.getGender());
			statement.setString(5, object.getTitle());
		} catch (Exception e) {

		}

	}

	@Override
	protected List<Employee> parseResultSet(ResultSet rs)
			throws PersistException {
		LinkedList<Employee> result = new LinkedList<Employee>();
		try {
			while (rs.next()) {

				Integer id = rs.getInt("id");
				String firstName = rs.getString("f_name");
				String lastName = rs.getString("l_name");
				java.util.Date birthDate = new java.util.Date(rs.getDate(
						"b_date").getTime());
				Boolean gender = rs.getBoolean("gender");
				String title = rs.getString("title");

				Employee employee = new Employee(id, firstName, lastName,
						gender, birthDate, title);
				result.add(employee);
			}

		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

}
