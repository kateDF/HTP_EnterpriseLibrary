package by.htp.library.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.entity.EmployeeCard;
import by.htp.library.entity.Record;

public class EmployeeCardDaoImplSql extends AbstractMySqlUtilDao implements EmployeeCardDao {
	private static final String SQL_SELECT_BY_IDCARD = "SELECT id_card, full_name, phone_number, password FROM employee_card WHERE id_card = ?";
	private static final String SQL_SELECT_BY_IDCARD_AND_PASSWORD = "SELECT id_card, full_name, phone_number, password FROM employee_card WHERE id_card = ? AND password = ?";
	private static final String SQL_SELECT_ALL_EMPLOYEE_CARDS = "SELECT id_card, full_name, phone_number, password FROM employee_card";
	private static final String SQL_INSERT_EMPLOYEE_CARD = "INSERT INTO employee_card (id_card, full_name, phone_number, password) VALUES(null, ?, ?, ?)";

	@Override
	public EmployeeCard find(int idCard, String password) {
		Connection con = connect();
		EmployeeCard employeeCard = null;
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_IDCARD_AND_PASSWORD);
			ps.setInt(1, idCard);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employeeCard = createNewEmployee(rs);
			}
			List<Record> records = getRecordsByIdCard(idCard);
			employeeCard.setRecords(records);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return employeeCard;
	}

	@Override
	public EmployeeCard find(int idCard) {
		Connection con = connect();
		EmployeeCard employeeCard = null;
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_IDCARD);
			ps.setInt(1, idCard);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employeeCard = createNewEmployee(rs);
			}
			List<Record> records = getRecordsByIdCard(idCard);
			employeeCard.setRecords(records);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return employeeCard;
	}

	@Override
	public List<EmployeeCard> searchAll() {
		Connection con = connect();
		List<EmployeeCard> emplCards = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL_EMPLOYEE_CARDS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeCard card = createNewEmployee(rs);
				emplCards.add(card);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return emplCards;
	}

	@Override
	public EmployeeCard create(EmployeeCard employeeCard) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_EMPLOYEE_CARD, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, employeeCard.getFullName());
			ps.setString(2, employeeCard.getPhoneNumber());
			ps.setString(3, employeeCard.getPassword());
			int rs = ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				employeeCard.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return employeeCard;
	}

	private EmployeeCard createNewEmployee(ResultSet rs) throws SQLException {
		EmployeeCard card = new EmployeeCard();
		card.setId(rs.getInt("id_card"));
		card.setFullName(rs.getString("full_name"));
		card.setPhoneNumber(rs.getString("phone_number"));
		card.setPassword(rs.getString("password"));
		return card;
	}

}
