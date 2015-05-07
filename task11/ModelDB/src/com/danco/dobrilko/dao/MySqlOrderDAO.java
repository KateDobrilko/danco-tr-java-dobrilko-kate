package com.danco.dobrilko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.danco.dobrilko.abstractdao.AbstractJDBCDAO;
import com.danco.dobrilko.entity.Book;
import com.danco.dobrilko.entity.Order;
import com.danco.dobrilko.persistexception.PersistException;

public class MySqlOrderDAO extends AbstractJDBCDAO<Order, Integer> {
	@Override
	public String getSelectQuery() {

		return "SELECT executionDate, executed, sum, id  FROM bookdanco.Order ORDER BY ?;";

	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO bookdanco.Order (executionDate, executed, sum, id) \n"
				+ "VALUES (?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE bookdanco.Order SET executionDate=?, executed=?, sum=?  FROM bookdanco.Order WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM bookdanco.Order WHERE id= ?;";
	}

	public String getRelatedOrdersQuery() {
		return "SELECT * FROM Order WHERE id = (SELECT idO FROM Order_To_Book WHERE idB= ?)";
	}

	public String getRelatedBooksQuery() {
		return "SELECT * FROM Book WHERE id = (SELECT idB FROM Order_To_Book WHERE idO= ?)";
	}

	public String deleteBookToOrderQuery() {
		return "DELETE FROM Order__To_Book WHERE idBook= ?;";
	}

	public String deleteOrderToBookQuery() {
		return "DELETE FROM Order__To_Book WHERE idOrder= ?;";
	}

	@Override
	public Order create(Connection connection) throws PersistException {
		Order g = new Order();
		return persist(g, connection);
	}

	public MySqlOrderDAO() {
		super();
	}

	@Override
	protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Order> result = new LinkedList<Order>();
		try {
			while (rs.next()) {
				java.util.Date executionDate;
				if (rs.getDate("executionDate") != null) {
					executionDate = new java.util.Date(rs.getDate(
							"executionDate").getTime());
				} else {
					executionDate = null;

				}

				Double sum = rs.getDouble("sum");
				Integer id = rs.getInt("id");
				ArrayList<Book> books = new ArrayList<Book>();
				Order Order = new Order(executionDate, books, id, sum);
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
			if (object.getExecutionDate() == null) {
				statement.setNull(1, java.sql.Types.DATE);
			} else {
				statement.setDate(1, new java.sql.Date(object
						.getExecutionDate().getTime()));
			}
			statement.setBoolean(2, object.getExecuted());
			statement.setDouble(3, object.getSum());
			statement.setInt(4, object.getId());

		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForReading(PreparedStatement statement,
			String attributeToSortBy) throws PersistException {
		try {
			statement.setString(1, attributeToSortBy);

		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Order object) throws PersistException {
		try {
			if (object.getExecutionDate() == null) {
				statement.setNull(1, java.sql.Types.DATE);
			} else {
				statement.setDate(1, new java.sql.Date(object
						.getExecutionDate().getTime()));
			}
			statement.setBoolean(2, object.getExecuted());
			statement.setDouble(3, object.getSum());
			statement.setInt(4, object.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement,
			Integer id) throws PersistException {
		try {
			statement.setInt(1, id);

		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public String getSelectByPK() {
		return "SELECT * FROM bookdanco.Order WHERE id=?;";
	}

	protected List<Book> parseResultSetBook(ResultSet rs)
			throws PersistException {
		LinkedList<Book> result = new LinkedList<Book>();
		try {
			while (rs.next()) {

				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				java.util.Date arrivalDate;
				if (rs.getDate("arrivalDate") != null) {
					arrivalDate = new java.util.Date(rs.getDate("arrivalDate")
							.getTime());
				} else {
					arrivalDate = null;

					Double price = rs.getDouble("price");
					java.util.Date publicationDate = new java.util.Date(rs
							.getDate("publicationDate").getTime());
					Integer numberOfExemplars = rs.getInt("numberOfExemplars");

					Book book = new Book(name, author, price,
							numberOfExemplars, id, publicationDate, arrivalDate);
					result.add(book);
				}
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	public List<Order> getRelatedOrders(Connection connection, Book book)
			throws SQLException, PersistException {
		List<Order> orders = new ArrayList<Order>();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(getRelatedOrdersQuery());) {
			preparedStatement.setInt(1, book.getId());
			ResultSet rs = preparedStatement.executeQuery();

			orders = parseResultSet(rs);

		}

		return orders;
	}

	public List<Book> getRelatedBooks(Connection connection, Order order)
			throws SQLException, PersistException {
		List<Book> books = new ArrayList<Book>();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(getRelatedBooksQuery());) {
			preparedStatement.setInt(1, order.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			parseResultSetBook(resultSet);
		}
		return books;
	}

	public void deleteOrderToBookRelation(Connection connection, Order order)
			throws SQLException {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(deleteOrderToBookQuery());) {
			preparedStatement.setInt(1, order.getId());
			preparedStatement.executeUpdate();

		}
	}

	public void deleteBookToOrderRelation(Connection connection, Book book)
			throws SQLException {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(deleteBookToOrderQuery());) {
			preparedStatement.setInt(1, book.getId());
			preparedStatement.executeUpdate();

		}
	}

}
