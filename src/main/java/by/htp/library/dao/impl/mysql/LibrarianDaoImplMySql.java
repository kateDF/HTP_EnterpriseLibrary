package by.htp.library.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.library.dao.LibrarianDao;
import by.htp.library.entity.Librarian;

public class LibrarianDaoImplMySql extends AbstractMySqlDao implements LibrarianDao {

	private static final String SQL_SELECT_BY_LOGIN_AND_PASSWORD = "SELECT id_librarian, login, password FROM librarian WHERE login = ? AND password = ?";

	public Librarian findByLoginAndPassword(String login, String password) {
		Connection con = connect();
		Librarian librarian = null;

		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_LOGIN_AND_PASSWORD);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				librarian = new Librarian(rs.getInt("id_librarian"), rs.getString("login"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

		return librarian;
	}

}
