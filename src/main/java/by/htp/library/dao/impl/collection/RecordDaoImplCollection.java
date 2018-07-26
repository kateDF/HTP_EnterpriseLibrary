package by.htp.library.dao.impl.collection;

import java.time.LocalDate;
import java.util.List;

import by.htp.library.dao.RecordDao;
import by.htp.library.entity.Book;
import by.htp.library.entity.EmployeeCard;
import by.htp.library.entity.Record;

public class RecordDaoImplCollection extends AbstractCollectionUtilDao implements RecordDao {

	@Override
	public int create(int idCard, int idBook, LocalDate startDate) {
		Book book = getBookById(idBook);
		EmployeeCard reader = getEmployeeCardById(idCard);

		Record record = new Record();
		record.setBook(book);
		record.setStartDate(startDate);
		record.setEndDate(startDate.plusDays(30));
		record.setId(extractNextId(data.getAllRecords()));

		reader.addRecord(record);

		return record.getId();
	}

	@Override
	public List<Record> getRecordsByIdCard(int idCard) {
		for (EmployeeCard employee : data.getReaders()) {
			if (employee.getId() == idCard) {
				return employee.getRecords();
			}
		}
		return null;
	}

	@Override
	public void setReturnDate(int idRecord, LocalDate returnDate) {
		for (Record record : data.getAllRecords()) {
			if (record.getId() == idRecord) {
				record.setReturnDate(returnDate);
			}
		}
	}

}
