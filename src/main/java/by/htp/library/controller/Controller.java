package by.htp.library.controller;

import static by.htp.library.controller.ControllerUtils.inputInt;
import static by.htp.library.controller.ControllerUtils.inputString;
import static by.htp.library.controller.ControllerUtils.isItPossibleToTakeBook;

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

	private Scanner scan = new Scanner(System.in);
	private BookDao bookDao;
	private EmployeeCardDao emplDao;
	private LibrarianDao librarianDao;
	private RecordDao recordDao;

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
			bookDao = DaoFactory.getFactory(DaoTypes.COLLECTION).getBookDao();
			emplDao = DaoFactory.getFactory(DaoTypes.COLLECTION).getEmployeeCardDao();
			librarianDao = DaoFactory.getFactory(DaoTypes.COLLECTION).getLibrarianDao();
			recordDao = DaoFactory.getFactory(DaoTypes.COLLECTION).getRecordDao();
		}

		System.out.println("Who are you?");
		System.out.println("1. Librarian");
		System.out.println("2. Reader");
		int userType = inputInt("Enter menu number", 1, 2);
		if (userType == 1) {
			Librarian librarian = authorizeAsLibrarian();
			ControllerLibrarian controllerLibrarian = new ControllerLibrarian(bookDao, emplDao, librarianDao,
					recordDao);
			controllerLibrarian.mainMenu();
		} else if (userType == 2) {
			EmployeeCard reader = authorizeAsReader();
			ControllerReader controllerReader = new ControllerReader(bookDao, emplDao, librarianDao, recordDao);
			showMessageAboutDebts(reader);
			controllerReader.mainMenu(reader);
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

	private EmployeeCard authorizeAsReader() {
		EmployeeCard employeeCard = null;
		while (employeeCard == null) {
			int login = inputInt("Enter login (id your card)", 1, Integer.MAX_VALUE);
			String password = inputString("Enter password", 6, 50);
			employeeCard = emplDao.find(login, password);
			if (employeeCard == null) {
				System.out.println("Authorise failed: incorrect login or password");
			}
		}
		System.out.println("Authorise success ");
		return employeeCard;
	}

	private void showMessageAboutDebts(EmployeeCard reader) {
		System.out.println();
		isItPossibleToTakeBook(reader);
	}

}
