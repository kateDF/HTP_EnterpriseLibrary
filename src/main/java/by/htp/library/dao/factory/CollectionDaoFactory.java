package by.htp.library.dao.factory;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.RecordDao;
import by.htp.library.dao.impl.collection.BookDaoImplCollection;
import by.htp.library.dao.impl.collection.EmployeeCardDaoImplCollection;
import by.htp.library.dao.impl.collection.LibrarianDaoImplCollection;
import by.htp.library.dao.impl.collection.RecordDaoImplCollection;

public class CollectionDaoFactory extends DaoFactory {

	@Override
	public BookDao getBookDao() {
		return new BookDaoImplCollection();
	}

	@Override
	public EmployeeCardDao getEmployeeCardDao() {
		return new EmployeeCardDaoImplCollection();
	}

	@Override
	public LibrarianDao getLibrarianDao() {
		return new LibrarianDaoImplCollection();
	}

	@Override
	public RecordDao getRecordDao() {
		return new RecordDaoImplCollection();
	}

}
