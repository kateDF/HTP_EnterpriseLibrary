package by.htp.library.controller;

import static by.htp.library.controller.ControllerUtils.inputInt;
import static by.htp.library.controller.ControllerUtils.inputString;

import java.util.Scanner;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.RecordDao;
import by.htp.library.dao.factory.DaoFactory;
import by.htp.library.dao.factory.DaoFactory.DaoTypes;
import by.htp.library.entity.EmployeeCard;
import by.htp.library.entity.Librarian;

public class Controller {

	protected Scanner scan = new Scanner(System.in);
	protected BookDao bookDao;
	protected EmployeeCardDao emplDao;
	protected LibrarianDao librarianDao;
	protected RecordDao recordDao;

	public void start() {

		System.out.println("Prefered dao type?");
		System.out.println("1. MySql");
		System.out.println("2. Collection");
		int daoType = inputInt("Enter menu number", 1, 2);
		if (daoType == 1) {
			bookDao = DaoFactory.getFactory(DaoTypes.MYSQL).getBookDao();
			emplDao = DaoFactory.getFactory(DaoTypes.MYSQL).getEmployeeCardDao();
			librarianDao = DaoFactory.getFactory(DaoTypes.MYSQL).getLibrarianDao();
			recordDao = DaoFactory.getFactory(DaoTypes.MYSQL).getRecordDao();
		} else if (daoType == 2) {
		}

		System.out.println("Who are you?");
		System.out.println("1. Librarian");
		System.out.println("2. Reader");
		int userType = inputInt("Enter menu number", 1, 2);
		if (userType == 1) {
			Librarian librarian = authorizeAsLibrarian();
			ControllerLibrarian controllerLibrarian = new ControllerLibrarian(bookDao, emplDao, librarianDao,
					recordDao);
			controllerLibrarian.myMenu();
		} else if (userType == 2) {
			showReaderMenu();
		}
	}

	private Librarian authorizeAsLibrarian() {
		Librarian librarian = null;
		while (librarian == null) {
			String login = inputString("Enter login ", 1, 50);
			String password = inputString("Enter password", 6, 50);
			librarian = librarianDao.findByLoginAndPassword(login, password);
			if (librarian == null) {
				System.out.println("Authorise failed: incorrect login or password");
			}
		}
		System.out.println("Authorise as a librarian: success ");
		return librarian;
	}

	private void showReaderMenu() {
		EmployeeCard employeeCard = null;
		while (employeeCard == null) {
			int login = inputInt("Enter login (id your card)", 1, Integer.MAX_VALUE);
			String password = inputString("Enter password", 6, 50);
			employeeCard = emplDao.find(login, password);
			if (employeeCard == null) {
				System.out.println("Authorise failed: incorrect login or password");
			}
		}
		System.out.println("Authorise success " + "message = books that  should return");
	}

}
