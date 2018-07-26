package by.htp.library.controller;

import static by.htp.library.controller.ControllerUtils.inputInt;
import static by.htp.library.controller.ControllerUtils.showListBooks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.RecordDao;
import by.htp.library.entity.Book;
import by.htp.library.entity.EmployeeCard;
import by.htp.library.entity.Record;

public class ControllerReader extends AbstractController {

	private static Scanner scan = new Scanner(System.in);

	public ControllerReader(BookDao bookDao, EmployeeCardDao emplDao, LibrarianDao librarianDao, RecordDao recordDao) {
		super(bookDao, emplDao, librarianDao, recordDao);
	}

	public void mainMenu(EmployeeCard reader) {
		int action = -1;
		do {
			System.out.println("1. Show full list of books");
			System.out.println("2. Show available book");
			System.out.println("3. Show books which are not returned");
			System.out.println("0. Exit");
			action = inputInt("Enter menu number", 0, 3);

			switch (action) {
			case 1:
				showFullListOfBooks();
				break;
			case 2:
				showAvailableBooks();
				break;
			case 3:
				showNoReturnBooks(reader);
				break;
			}

		} while (action != 0);

		System.out.println("Log out. Have a good day!");
	}

	private void showFullListOfBooks() {
		List<Book> allBooks = bookDao.searchAll();
		showBooks(allBooks);
	}

	private void showAvailableBooks() {
		List<Book> availableBooks = bookDao.searchAllAvailable();
		showBooks(availableBooks);
	}

	private void showBooks(List<Book> books) {
		int switchToMenu = -1;
		do {
			showListBooks(books);
			showBookInfo(books);
			switchToMenu = inputInt("Enter 1 to show list of book \nEnter 0 to return to main menu", 0, 1);
		} while (switchToMenu == 1);
	}

	private void showBookInfo(List<Book> allBooks) {
		int bookInf = inputInt("Enter book number to show full information \nEnter 0 to return to books menu", 0,
				Integer.MAX_VALUE);
		if (bookInf > 0) {
			Book book = bookDao.getById(allBooks.get(bookInf - 1).getId());
			System.out.println(book);
		}
	}

	private void showNoReturnBooks(EmployeeCard reader) {
		List<Record> noReturnRecords = reader.getRecordsWithoutReturnDate();
		if (noReturnRecords != null) {
			int i = 1;
			for (Record record : noReturnRecords) {
				System.out.println(i++ + ". Book: id=" + record.getBook().getId() + " " + record.getBook().getTitle()
						+ ", author: " + record.getBook().getAuthor() + ". End reading date: " + record.getEndDate());
				if (LocalDate.now().isAfter(record.getEndDate())) {
					System.err.println("You have a dept. "
							+ ChronoUnit.DAYS.between(record.getEndDate(), LocalDate.now()) + " days overdue");
				}
			}
		} else {
			System.out.println("You have already returned all books");
		}
		inputInt("Enter 0 to return to main menu", 0, 0);
	}

}
