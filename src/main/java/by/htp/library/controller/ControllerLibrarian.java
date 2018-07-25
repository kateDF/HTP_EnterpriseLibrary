package by.htp.library.controller;

import static by.htp.library.controller.ControllerUtils.inputDate;
import static by.htp.library.controller.ControllerUtils.inputInt;
import static by.htp.library.controller.ControllerUtils.inputString;
import static by.htp.library.controller.ControllerUtils.inputStringPassword;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.RecordDao;
import by.htp.library.entity.Book;
import by.htp.library.entity.EmployeeCard;
import by.htp.library.entity.Record;

public class ControllerLibrarian {

	private static Scanner scan = new Scanner(System.in);
	protected BookDao bookDao;
	protected EmployeeCardDao emplDao;
	protected LibrarianDao librarianDao;
	protected RecordDao recordDao;

	public ControllerLibrarian(BookDao bookDao, EmployeeCardDao emplDao, LibrarianDao librarianDao,
			RecordDao recordDao) {
		this.bookDao = bookDao;
		this.emplDao = emplDao;
		this.librarianDao = librarianDao;
		this.recordDao = recordDao;
	}

	public void myMenu() {

		int action = -1;
		do {
			System.out.println("1. Add new reader");
			System.out.println("2. Add new book");
			System.out.println("3. Add new record");
			System.out.println("4. Show available books");
			System.out.println("5. Show full list of readers");
			System.out.println("0. Exit");
			action = inputInt("Enter menu number", 0, 5);

			switch (action) {
			case 1:
				addNewReader();
				break;
			case 2:
				addNewBook();
				break;
			case 3:
				addNewRecord();
				break;
			case 4:
				showListBooksAndUpdate();
				break;
			case 5:
				showReadersAndCloseRecords();
				break;
			}

		} while (action != 0);

		System.out.println("Log out. Have a good day!");
	}

	private void addNewReader() {
		String fullName = inputString("Enter full name", 1, 50);
		String phoneNumber = inputString("Enter phoneNumber", 1, 50);
		String password = inputStringPassword("Enter password");
		System.out.println(
				"Confirm new reader. Name: " + fullName + ". Phone number: " + phoneNumber + ". Password: " + password);
		int confirmation = inputInt("Enter 1 to confirm, 0 to exit", 0, 1);
		if (confirmation == 1) {
			EmployeeCard newEmplCard = new EmployeeCard();
			newEmplCard.setFullName(fullName);
			newEmplCard.setPhoneNumber(phoneNumber);
			newEmplCard.setPassword(password);

			EmployeeCard newEmplInBD = emplDao.create(newEmplCard);
			System.out.println("New reader login " + newEmplInBD.getId() + " : password " + newEmplInBD.getPassword());
		} else {
			System.out.println("Return to librarian menu");
		}
	}

	private void addNewBook() {
		String title = inputString("Enter book title", 1, 50);
		String author = inputString("Enter book author", 1, 50);
		String description = inputString("Enter description (optional)", 0, 100);
		System.out
				.println("Confirm new book. Title: " + title + ". Author: " + author + ". Description: " + description);
		int confirmation = inputInt("Enter 1 to confirm, 0 to exit", 0, 1);
		if (confirmation == 1) {
			Book book = new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setDescription(description);
			Book bookDB = bookDao.create(book);
		} else {
			System.out.println("Return to librarian menu");
		}
	}

	private void addNewRecord() {
		int typeOfAddition = inputInt("1. Add with reader id and book id. \n0. Return to main menu", 0, 2);
		if (typeOfAddition == 1) {
			int id_reader = inputInt("Enter reader id", 1, Integer.MAX_VALUE);
			EmployeeCard reader = emplDao.find(id_reader);
			if (reader == null) {
				System.err.println("No such reader index");
			} else if (isItPossibleToTakeBook(reader)) {
				int id_book = -1;
				Book book = null;
				do {
					id_book = inputInt("Enter book id", 1, Integer.MAX_VALUE);
					book = bookDao.getById(id_book);
					if (book == null || !isBookAvailable(book)) {
						System.out.println("Available books");
						showListBooks(bookDao.searchAllAvailable());
					}

				} while (book == null || !isBookAvailable(book));

				if (id_book > 0) {
					int dateInp = inputInt(
							"For using today's date as a start date: enter 0 \nFor enter different date: enter 1", 0,
							1);
					LocalDate startDate = null;
					if (dateInp == 0) {
						startDate = LocalDate.now();
					} else {
						startDate = inputDate("Input date with a format yyyy-mm-dd");
					}
					System.out.println("New record: ReaderId " + id_reader + ". " + reader.getFullName() + ". \nBookId "
							+ id_book + ". Title: " + book.getTitle() + ". \nStart date: " + startDate + " - End date: "
							+ startDate.plusDays(30));
					int confirmation = inputInt("Enter 1 to confirm, 0 to exit", 0, 1);
					if (confirmation == 1) {
						recordDao.create(id_reader, id_book, startDate);
					}
				}
			}
		}
	}

	private void updateBook(int id) {
		String title = inputString("Enter book title", 1, 50);
		String author = inputString("Enter book author", 1, 50);
		String description = inputString("Enter description (optional)", 0, 100);
		System.out.println(
				"Confirm update book. Title: " + title + ". Author: " + author + ". Description: " + description);
		int confirmation = inputInt("Enter 1 to confirm, 0 to exit", 0, 1);
		if (confirmation == 1) {
			Book book = new Book();
			book.setId(id);
			book.setTitle(title);
			book.setAuthor(author);
			book.setDescription(description);
			bookDao.update(book);
		} else {
			System.out.println("Return to librarian menu");
		}
	}

	private void showListBooksAndUpdate() {
		System.out.println("Available books:");
		List<Book> availableBook = bookDao.searchAllAvailable();
		showListBooks(availableBook);

		int selectBookNumberToEdit = inputInt("To update book enter book number.\nReturn to main menu: enter 0", 0,
				availableBook.size());

		if (selectBookNumberToEdit > 0) {
			int id = availableBook.get(selectBookNumberToEdit - 1).getId();
			updateBook(id);
		}
	}

	private void showReadersAndCloseRecords() {
		List<EmployeeCard> cards = emplDao.searchAll();
		showListReaders(cards);
		int selectRecords = inputInt("To show list of records: enter reader number. \nReturn to main menu: enter 0", 0,
				cards.size());
		if (selectRecords > 0) {
			List<Record> recordOfReader = recordDao.getRecordsByIdCard(cards.get(selectRecords - 1).getId());
			showListRecords(recordOfReader);
			int recordToInsertReturnDate = inputInt(
					"To insert return date: enter record number.\nReturn to main menu: enter 0", 0,
					recordOfReader.size());
			if (recordToInsertReturnDate > 0) {
				setReturnDate(recordOfReader.get(recordToInsertReturnDate - 1).getId());
			}
		}
	}

	private void setReturnDate(int id_record) {
		int dateVariant = inputInt("For using today's date: enter 0 \nFor enter different date: enter 1", 0, 1);
		LocalDate returnDate = null;
		if (dateVariant == 0) {
			returnDate = LocalDate.now();
		} else {
			returnDate = inputDate("Input date with a format yyyy-mm-dd");
		}
		int confirmation = inputInt("Enter 1 to confirm, 0 to exit", 0, 1);
		if (confirmation == 1) {
			recordDao.setReturnDate(id_record, returnDate);
		}
	}

	public void showListBooks(List<Book> books) {
		int i = 1;
		for (Book book : books) {
			System.out.println(
					i++ + ". Id: " + book.getId() + ". Title: " + book.getTitle() + ", author: " + book.getAuthor());
		}
	}

	public void showListReaders(List<EmployeeCard> readers) {
		int i = 1;
		for (EmployeeCard rc : readers) {
			System.out.println(i++ + ". " + rc.getFullName() + " phoneNumber: " + rc.getPhoneNumber()
					+ ". Login(id_card): " + rc.getId() + ", pass: " + rc.getPassword());
		}
	}

	private void showListRecords(List<Record> records) {
		int i = 1;
		for (Record record : records) {
			System.out.println(i++ + ". " + record.getBook().getTitle() + ". End reading period: " + record.getEndDate()
					+ (record.getReturnDate() != null ? " return " + record.getReturnDate() : " should be return"));
		}
	}

	private boolean isItPossibleToTakeBook(EmployeeCard reader) {
		if (reader.hasDebt()) {
			System.err.println(
					"Reader: id:" + reader.getId() + " " + reader.getFullName() + " has a debt and can't take a book");
			return false;
		}
		if (reader.hasMaxNumberOfBooks(3)) {
			System.err.println("Reader: id:" + reader.getId() + " " + reader.getFullName()
					+ " can't take a book because has already took 3 or more book and hasn't return them yet");
			return false;
		}
		return true;
	}

	private boolean isBookAvailable(Book book) {
		List<Book> availableBooks = bookDao.searchAllAvailable();
		for (Book b : availableBooks) {
			if (b.getId() == book.getId()) {
				return true;
			}
		}
		System.out.println("Book is not available");
		return false;
	}

}
