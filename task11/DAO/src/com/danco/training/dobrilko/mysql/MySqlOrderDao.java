package com.danco.training.dobrilko.mysql;

import com.danco.training.dobrilko.dao.AbstractJDBCDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Order;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class MySqlOrderDao extends AbstractJDBCDao<Order, Integer> {

	private Logger logger = Logger.getLogger(MySqlBookDao.class);

	@Override
	public String getSelectQuery(String parameterName) {

		StringBuilder sb = new StringBuilder();
		try {
			Order.class.getDeclaredField(parameterName);
			sb.append("SELECT id,  status, sum, dateOfExecution FROM Bookshop.order ORDER BY ");
			sb.append(parameterName);
			sb.append(";");

		} catch (NoSuchFieldException e) {
			logger.error(e);
		} catch (SecurityException e) {
			logger.error(e);
		}

		return sb.toString();

	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO Bookshop.Order (id, sum, status, dateOfExecution) \n"
				+ "VALUES (?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Bookshop.Order SET  bookId = ? sum=? status = ? dateOfExecution=? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM Bookshop.Order WHERE id= ?;";
	}

	@Override
	public Order create(Connection connection) throws PersistException {
		Order g = new Order();
		return persist(g, connection);
	}

	public MySqlOrderDao() {
		super();
	}

	@Override
	protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Order> result = new LinkedList<Order>();
		try {
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDateOfExecution(rs.getDate("dateOfExecution"));
				order.setStatus(rs.getBoolean("status"));
				order.setSum(rs.getDouble("sum"));
				result.add(order);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Order object) throws PersistException {
		try {
			statement.setInt(1, object.getId());

			statement.setDate(2, new java.sql.Date(object.getDateOfExecution()
					.getTime()));
			statement.setBoolean(3, object.getStatus());
			statement.setDouble(4, object.getSum());
		
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Order object) throws PersistException {
		try {
			statement.setInt(1, object.getId());
			statement.setDate(3, new java.sql.Date(object.getDateOfExecution()
					.getTime()));
			statement.setBoolean(3, object.getStatus());
			statement.setDouble(4, object.getSum());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

}
