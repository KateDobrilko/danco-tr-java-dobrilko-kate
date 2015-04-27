package com.danco.training.dobrilko.mysql;

import com.danco.training.dobrilko.dao.AbstractJDBCDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Book;



import com.danco.training.dobrilko.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class MySqlBookDao extends AbstractJDBCDao<Book, Integer> {

	private Logger logger = Logger.getLogger(MySqlBookDao.class);

	@Override
	public String getSelectQuery(String parameterName) {

		StringBuilder sb = new StringBuilder();
		try {
			Book.class.getDeclaredField(parameterName);
			sb.append("SELECT name, author, price, id, dateOfAddition, dateOfPublication, ordered, unclaimed, orderId FROM Bookshop.Book ORDER BY ");
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
		return "INSERT INTO Bookshop.book (name, author, price, id, dateOfAddition, dateOfPublication, ordered, orderId) \n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Bookshop.book SET name=? author=? price=?  dateOfAddition=? dateOfPublication=? ordered=? orderId=? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM Bookshop.book WHERE id= ?;";
	}

	@Override
	public Book create(Connection connection) throws PersistException {
		Book g = new Book();
		return persist(g, connection);
	}

	public MySqlBookDao() {
		super();
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Book> result = new LinkedList<Book>();
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setUnclaimed(rs.getBoolean("unclaimed"));
				book.setOrdered(rs.getBoolean("ordered"));
				book.setDateOfAddition(rs.getDate("dateOfAddition"));
				book.setDateOfPublication(rs.getDate("dateOfPublication"));
				book.setPrice(rs.getDouble("price"));
				Order order = new Order();
				order.setId(rs.getInt("orderId"));
				book.setOrder(new Order());
				result.add(book);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Book object) throws PersistException {
		try {
			statement.setString(1, object.getName());
			statement.setString(2, object.getAuthor());
			statement.setDouble(3, object.getPrice());
			statement.setInt(4, object.getId());
			statement.setDate(5, new java.sql.Date(object.getDateOfAddition()
					.getTime()));
			statement.setDate(6, new java.sql.Date(object
					.getDateOfPublication().getTime()));
			statement.setBoolean(7, object.isOrdered());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Book object) throws PersistException {
		try {
			statement.setString(1, object.getName());
			statement.setString(2, object.getAuthor());
			statement.setDouble(3, object.getPrice());
			statement.setInt(4, object.getId());
			statement.setDate(5, new java.sql.Date(object.getDateOfAddition()
					.getTime()));
			statement.setDate(6, new java.sql.Date(object
					.getDateOfPublication().getTime()));
			statement.setBoolean(7, object.isOrdered());
			statement.setInt(8, object.getOrder().getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	

}
