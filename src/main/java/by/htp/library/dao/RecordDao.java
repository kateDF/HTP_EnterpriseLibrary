package by.htp.library.dao;

import java.time.LocalDate;
import java.util.List;

import by.htp.library.entity.Record;

public interface RecordDao {

	int create(int idCard, int idBook, LocalDate startDate);

	List<Record> getRecordsByIdCard(int idCard);

	void setReturnDate(int idRecord, LocalDate returnDate);

}
