package by.htp.library.dao.impl.collection;

import java.time.LocalDate;
import java.util.List;

import by.htp.library.dao.RecordDao;
import by.htp.library.entity.Record;

public class RecordDaoImplCollection implements RecordDao {

	@Override
	public int create(int id_card, int id_book, LocalDate startDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Record> getRecordsByIdCard(int idCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReturnDate(int id_record, LocalDate returnDate) {
		// TODO Auto-generated method stub

	}

}
