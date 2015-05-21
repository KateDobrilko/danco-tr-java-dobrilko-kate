package com.danco.training.dobrilko.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.connectionmanager.ConnectionManager;
import com.danco.training.dobrilko.csvworker.CSVWorker;
import com.danco.training.dobrilko.dao.EmployeeDAO;
import com.danco.training.dobrilko.entity.Employee;
import com.danco.training.dobrilko.genericdao.GenericDAO;
import com.danco.training.dobrilko.persistexception.PersistException;

public class Controller {
	private GenericDAO<Employee, Integer> dao = new EmployeeDAO();

	private Logger logger = Logger.getLogger(Controller.class);

	public void saveOrUpdateFromCSV(String path) {

		List<Employee> employees = CSVWorker.parseCSVString(CSVWorker
				.readCSVFile(path));

		Connection connection = ConnectionManager.getConnection();
		try {
			connection.setAutoCommit(false);

			for (Employee employee : employees) {

				Boolean flag = false;
				if (!dao.getAll(connection).isEmpty()) {
					for (Employee dbEmployee : dao.getAll(connection)) {
						if ((employee.getFirstName().equals(dbEmployee
								.getFirstName()))
								&& (employee.getLastName().equals(dbEmployee
										.getLastName()))
								&& (employee.getBirthDate().equals(dbEmployee
										.getBirthDate()))) {
							dbEmployee.setTitle(employee.getTitle());
							dbEmployee.setId(employee.getId());
							dao.update(dbEmployee, connection);
							flag = true;
						}
					}
				}
				if (flag == false) {
					dao.save(employee, connection);
				}

			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1);
			}
			logger.error(e);
		}

	}
}
