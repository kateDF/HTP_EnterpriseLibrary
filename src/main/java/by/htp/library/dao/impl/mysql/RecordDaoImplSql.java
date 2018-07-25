package by.htp.library.dao.impl.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import by.htp.library.dao.RecordDao;
import by.htp.library.entity.Record;

public class RecordDaoImplSql extends AbstractMySqlUtilDao implements RecordDao {

	private static final String SQL_SELECT_RECORD_BY_ID_CARD_AND_ID_BOOK = "SELECT * FROM record WHERE id_card = 1 AND id_book = 1";
	private static final String SQL_UPDATE_RETURN_DATE = "UPDATE record SET return_date = ? WHERE id_record = ?";
	private static final String SQL_INSERT_RECORD = "INSERT INTO record (id_record, return_date, start_date, end_date, id_card, id_book) VALUES (null, null, ?, ?, ?, ?)";

	@Override
	public int create(int id_card, int id_book, LocalDate startDate) {
		int id_record = -1;
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_RECORD, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Date.valueOf(startDate));
			ps.setDate(2, Date.valueOf(startDate.plusDays(30)));
			ps.setInt(3, id_card);
			ps.setInt(4, id_book);
			int rs = ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				id_record = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return id_record;
	}

	@Override
	public List<Record> getRecordsByIdCard(int idCard) {
		return super.getRecordsByIdCard(idCard);
	}

	@Override
	public void setReturnDate(int id_record, LocalDate returnDate) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_UPDATE_RETURN_DATE);
			ps.setDate(1, Date.valueOf(returnDate));
			ps.setInt(2, id_record);
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

}
