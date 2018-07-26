package by.htp.library.dao.factory;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.RecordDao;
import by.htp.library.dao.impl.mysql.BookDaoImplSql;
import by.htp.library.dao.impl.mysql.EmployeeCardDaoImplSql;
import by.htp.library.dao.impl.mysql.LibrarianDaoImplMySql;
import by.htp.library.dao.impl.mysql.RecordDaoImplSql;

public class MySqlDaoFactory extends DaoFactory {

	@Override
	public BookDao getBookDao() {
		return new BookDaoImplSql();
	}

	@Override
	public EmployeeCardDao getEmployeeCardDao() {
		return new EmployeeCardDaoImplSql();
	}

	@Override
	public LibrarianDao getLibrarianDao() {
		return new LibrarianDaoImplMySql();
	}

	@Override
	public RecordDao getRecordDao() {
		return new RecordDaoImplSql();
	}

}
