package by.htp.library.dao.impl.mysql;

import java.util.List;

import by.htp.library.dao.RecordDao;
import by.htp.library.entity.Record;

public class RecordDaoImplSql extends AbstractMySqlUtilDao implements RecordDao {

	public List<Record> getRecordsByIdCard(int idCard) {
		return super.getRecordsByIdCard(idCard);
	}

}
