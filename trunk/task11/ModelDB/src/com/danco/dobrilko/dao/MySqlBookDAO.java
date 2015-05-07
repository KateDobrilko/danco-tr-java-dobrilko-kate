package com.danco.dobrilko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.danco.dobrilko.abstractdao.AbstractJDBCDAO;
import com.danco.dobrilko.entity.Book;
import com.danco.dobrilko.persistexception.PersistException;

public class MySqlBookDAO extends AbstractJDBCDAO<Book, Integer> {

	@Override
	public String getSelectQuery() {

		return "SELECT * FROM bookdanco.Book ORDER BY ?;";

	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO bookdanco.book (name, author, price, numberOfExemplars, publicationDate, arrivalDate, id) \n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE bookdanco.book SET name=?, author=?, price=?, numberOfExemplars=?, publicationDate=?, arrivalDate=? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM bookdanco.book WHERE id= ?;";
	}

	public String deleteRelatedReplyQuery() {
		return "DELETE FROM Reply WHERE idBook = ?;";
	}

	@Override
	public Book create(Connection connection) throws PersistException {
		Book g = new Book();
		return persist(g, connection);
	}

	public MySqlBookDAO() {
		super();
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
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
				}
				Double price = rs.getDouble("price");
				java.util.Date publicationDate = new java.util.Date(rs.getDate(
						"publicationDate").getTime());
				Integer numberOfExemplars = rs.getInt("numberOfExemplars");

				Book book = new Book(name, author, price, numberOfExemplars,
						id, publicationDate, arrivalDate);
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
			statement.setInt(4, object.getNumberOfExemplars());
			statement.setDate(5, new java.sql.Date(object.getPublicationDate()
					.getTime()));
			if (object.getArrivalDate() == null) {
				statement.setNull(6, java.sql.Types.DATE);
			} else {
				statement.setDate(6, new java.sql.Date(object.getArrivalDate()
						.getTime()));
			}

			statement.setInt(7, object.getId());
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
			Book object) throws PersistException {
		try {
			statement.setString(1, object.getName());
			statement.setString(2, object.getAuthor());
			statement.setDouble(3, object.getPrice());
			statement.setInt(4, object.getNumberOfExemplars());
			statement.setDate(5, new java.sql.Date(object.getPublicationDate()
					.getTime()));
			if (object.getArrivalDate() == null) {
				statement.setNull(6, java.sql.Types.DATE);
			} else {
				statement.setDate(6, new java.sql.Date(object.getArrivalDate()
						.getTime()));
			}
			statement.setInt(7, object.getId());
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
		return "SELECT * FROM bookdanco.Book WHERE id=?;";
	}

	public void deleteRelatedReplies(Connection connection, Book book)
			throws SQLException {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(deleteRelatedReplyQuery());) {
			preparedStatement.setInt(1, book.getId());
			preparedStatement.executeUpdate();

		}

	}

}
