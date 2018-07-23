package by.htp.library.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.dao.BookDao;
import by.htp.library.entity.Book;

public class BookDaoiImplSql extends AbstractMySqlDao implements BookDao {
	private static final String SQL_SELECT_BOOK_BY_ID = "SELECT id_book, title, author, description FROM book WHERE id_book = ?";
	private static final String SQL_SELECT_ALL_BOOKS = "SELECT id_book, title, author, description FROM book";
	private static final String SQL_SELECT_ALL_AVAILABLE_BOOKS = "SELECT * FROM book WHERE id_book NOT IN (SELECT id_book FROM record WHERE return_date IS null)";
	private static final String SQL_INSERT_BOOK = "INSERT INTO book (id_book, title, author, description) VALUES(null, ?, ?, ?)";
	private static final String SQL_UPDATE_BOOK = "UPDATE book SET title = ?, author= ?, description = ? WHERE id_book = ?";

	@Override
	public Book getById(int id) {
		Connection con = connect();
		Book book = null;
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOOK_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getInt("id_book"), rs.getString("title"), rs.getString("author"),
						rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return book;
	}

	@Override
	public List<Book> searchAll() {
		Connection con = connect();
		List<Book> listBook = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL_BOOKS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs.getInt("id_book"), rs.getString("title"), rs.getString("author"),
						rs.getString("description"));
				listBook.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return listBook;
	}

	@Override
	public List<Book> searchAllAvailable() {
		Connection con = connect();
		List<Book> listBook = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL_AVAILABLE_BOOKS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs.getInt("id_book"), rs.getString("title"), rs.getString("author"),
						rs.getString("description"));
				listBook.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return listBook;
	}

	@Override
	public void update(Book book) {
		Connection con = connect();

		try {
			PreparedStatement ps = con.prepareStatement(SQL_UPDATE_BOOK);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getDescription());
			ps.setInt(4, book.getId());

			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public Book create(Book book) {
		Connection con = connect();

		try {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getDescription());
			int rs = ps.executeUpdate();

			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				book.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return book;
	}

}
