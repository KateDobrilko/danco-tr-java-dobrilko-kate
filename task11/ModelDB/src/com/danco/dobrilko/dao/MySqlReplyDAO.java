package com.danco.dobrilko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.danco.dobrilko.abstractdao.AbstractJDBCDAO;
import com.danco.dobrilko.entity.Book;
import com.danco.dobrilko.entity.Reply;
import com.danco.dobrilko.persistexception.PersistException;

public class MySqlReplyDAO extends AbstractJDBCDAO<Reply, Integer> {
	@Override
	public String getSelectQuery() {

		return "SELECT number, executed, id, idBook  FROM bookdanco.Reply Reply BY ?;";

	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO bookdanco.Reply (number, executed, idBook, id) \n"
				+ "VALUES (?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE bookdanco.Reply SET number=?, executed=?, idBook=?  FROM bookdanco.Reply WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM bookdanco.Reply WHERE id= ?;";
	}

	public String getRelatedReplys(Integer ReplyId) {
		return " " + ReplyId.toString();
	}

	@Override
	public Reply create(Connection connection) throws PersistException {
		Reply g = new Reply();
		return persist(g, connection);
	}

	public MySqlReplyDAO() {
		super();
	}

	@Override
	protected List<Reply> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Reply> result = new LinkedList<Reply>();
		try {
			while (rs.next()) {

				Boolean executed = rs.getBoolean("executed");
				Integer number = rs.getInt("number");
				Integer id = rs.getInt("id");
				Integer bookId = rs.getInt("idBook");
				result.add(new Reply(id, bookId, number, executed));
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Reply object) throws PersistException {
		try {
			statement.setInt(1, object.getId());
			statement.setBoolean(2, object.getExecuted());
			statement.setInt(3, object.getBook().getId());
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
			Reply object) throws PersistException {
		try {
			statement.setInt(1, object.getId());
			statement.setBoolean(2, object.getExecuted());
			statement.setInt(3, object.getBook().getId());
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

	protected Book parseResultSetBook(ResultSet rs) throws PersistException {
		Book result = new Book();
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
					result = book;
				}
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	public Book getRelatedBook(Connection connection, Reply reply)
			throws SQLException, PersistException {
		Book book = new Book();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(getRelatedBookQuery());) {
			preparedStatement.setInt(1, reply.getBookId());
			ResultSet resultSet = preparedStatement.executeQuery();
			parseResultSetBook(resultSet);
		}
		return book;
	}

	private String getRelatedBookQuery() {
		return "SELECT * FROM Order WHERE id= ?";
	}

	@Override
	public String getSelectByPK() {
		return "SELECT * FROM bookdanco.Reply WHERE id=?;";
	}
}
