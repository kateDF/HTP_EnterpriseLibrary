package by.htp.library.dao;

import java.time.LocalDate;
import java.util.List;

import by.htp.library.entity.Record;

public interface RecordDao {

	int create(int id_card, int id_book, LocalDate startDate);

	List<Record> getRecordsByIdCard(int idCard);

	void setReturnDate(int id_record, LocalDate returnDate);

}
