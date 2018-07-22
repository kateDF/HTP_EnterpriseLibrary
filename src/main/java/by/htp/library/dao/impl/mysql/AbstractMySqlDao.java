package by.htp.library.dao.impl.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class AbstractMySqlDao {

	protected Connection connect() {
		Connection conn = null;

		try {
			ResourceBundle rb = ResourceBundle.getBundle("db_config");
			String driver = rb.getString("db.driver");
			String url = rb.getString("db.url");
			String login = rb.getString("db.login");
			String pass = rb.getString("db.pass");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, login, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	protected void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
