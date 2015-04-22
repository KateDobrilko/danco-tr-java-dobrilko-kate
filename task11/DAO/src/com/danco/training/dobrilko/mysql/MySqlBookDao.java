package com.danco.training.dobrilko.mysql;

import com.danco.training.dobrilko.dao.AbstractJDBCDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlBookDao extends AbstractJDBCDao<Book, Integer> {

	private class PersistBook extends Book {
		
		private static final long serialVersionUID = 4641921138250005105L;

		public void setId(int id) {
			super.setId(id);
		}
	}

	@Override
	public String getSelectQuery() {
		return "SELECT name, author, price, id, dateOfAddition, dateOfPublication, ordered FROM Bookshop.Book";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO Bookshop.book (name, author, price, id, dateOfAddition, dateOfPublication, ordered) \n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Bookshop.book SET name=? author=? price=?  dateOfAddition=? dateOfPublication=? ordered=? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM Bookshop.book WHERE id= ?;";
	}

	@Override
	public Book create() throws PersistException {
		Book g = new Book();
		return persist(g);
	}

	public MySqlBookDao(Connection connection) {
		super(connection);
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Book> result = new LinkedList<Book>();
		try {
			while (rs.next()) {
				PersistBook Book = new PersistBook();
				Book.setId(rs.getInt("id"));
				Book.setName(rs.getString("name"));
				Book.setAuthor(rs.getString("author"));
				Book.setUnclaimed(rs.getBoolean("unclaimed"));
				Book.setOrdered(rs.getBoolean("ordered"));
				Book.setDateOfAddition(rs.getDate("dateOfAddition"));
				Book.setDateOfPublication(rs.getDate("dateOfPublication"));
				Book.setPrice(rs.getDouble("price"));
				result.add(Book);
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
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

}
