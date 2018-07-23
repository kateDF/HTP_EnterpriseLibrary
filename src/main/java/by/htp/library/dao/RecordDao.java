package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Record;

public interface RecordDao {

	List<Record> getRecordsByIdCard(int idCard);

}
