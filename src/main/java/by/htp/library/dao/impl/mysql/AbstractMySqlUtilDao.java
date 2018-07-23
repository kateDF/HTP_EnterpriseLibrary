package by.htp.library.dao.impl.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Record;

public class AbstractMySqlUtilDao extends AbstractMySqlDao {
	private static final String SQL_SELECT_RECORDS_BY_ID_CARD = "SELECT id_record, return_date, start_date, end_date, id_card, book.id_book, title, author, description FROM record JOIN book ON record.id_book = book.id_book WHERE id_card = ?";

	protected List<Record> getRecordsByIdCard(int idCard) {
		Connection con = connect();
		List<Record> records = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_RECORDS_BY_ID_CARD);
			ps.setInt(1, idCard);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				record.setId(rs.getInt("id_record"));
				record.setReturnDate(toLocalDate(rs.getDate("return_date")));
				record.setStartDate(rs.getDate("start_date").toLocalDate());
				record.setEndDate(rs.getDate("end_date").toLocalDate());
				Book book = new Book(rs.getInt("book.id_book"), rs.getString("title"), rs.getString("author"),
						rs.getString("description"));
				record.setBook(book);
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return records;
	}

	private LocalDate toLocalDate(Date date) {
		return date == null ? null : date.toLocalDate();
	}

}
