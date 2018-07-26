package by.htp.library.controller;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.RecordDao;

public class AbstractController {

	protected BookDao bookDao;
	protected EmployeeCardDao emplDao;
	protected LibrarianDao librarianDao;
	protected RecordDao recordDao;

	public AbstractController() {

	}

	public AbstractController(BookDao bookDao, EmployeeCardDao emplDao, LibrarianDao librarianDao,
			RecordDao recordDao) {
		this.bookDao = bookDao;
		this.emplDao = emplDao;
		this.librarianDao = librarianDao;
		this.recordDao = recordDao;
	}

}
