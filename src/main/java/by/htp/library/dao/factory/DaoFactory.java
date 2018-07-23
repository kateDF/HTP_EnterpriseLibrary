package by.htp.library.dao.factory;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.RecordDao;

public abstract class DaoFactory {

	public enum DaoTypes {
		MYSQL, COLLECTION
	}

	public abstract BookDao getBookDao();

	public abstract EmployeeCardDao getEmployeeCardDao();

	public abstract LibrarianDao getLibrarianDao();

	public abstract RecordDao getRecordDao();

	public static DaoFactory getFactory(DaoTypes type) {
		switch (type) {
		case MYSQL:
			return new MySqlDaoFactory();
		case COLLECTION:
			return new CollectionDaoFactory();
		default:
			throw new IllegalArgumentException();
		}
	}

}
