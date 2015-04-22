package com.danco.training.dobrilko.mysql;

import com.danco.training.dobrilko.dao.AbstractJDBCDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlOrderDao extends AbstractJDBCDao<Order, Integer> {

	private class PersistOrder extends Order {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4641921138250005105L;

		public void setId(int id) {
			super.setId(id);
		}
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id, bookId, status, dateOfExecution FROM Bookshop.order";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO Bookshop.Order (id, bookId, status, dateOfExecution) \n"
				+ "VALUES (?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Bookshop.Order SET  bookId = ? status = ? dateOfExecution=? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM Bookshop.Order WHERE id= ?;";
	}

	@Override
	public Order create() throws PersistException {
		Order g = new Order();
		return persist(g);
	}

	public MySqlOrderDao(Connection connection) {
		super(connection);
	}

	@Override
	protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Order> result = new LinkedList<Order>();
		try {
			while (rs.next()) {
				PersistOrder Order = new PersistOrder();
				Order.setId(rs.getInt("id"));
				Order.setBookId(rs.getInt("bookId"));
				Order.setDateOfExecution(rs.getDate("dateOfExecution"));
				Order.setStatus(rs.getBoolean("status"));
				result.add(Order);
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
			statement.setInt(2, object.getBookId());
			statement.setDate(3, new java.sql.Date(object.getDateOfExecution()
					.getTime()));
			statement.setBoolean(4, object.getStatus());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Order object) throws PersistException {
		try {
			statement.setInt(1, object.getId());
			statement.setInt(2, object.getBookId());
			statement.setDate(3, new java.sql.Date(object.getDateOfExecution()
					.getTime()));
			statement.setBoolean(4, object.getStatus());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

}
