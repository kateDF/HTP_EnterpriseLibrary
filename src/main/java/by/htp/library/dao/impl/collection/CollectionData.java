package by.htp.library.dao.impl.collection;

import java.util.ArrayList;
import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.EmployeeCard;
import by.htp.library.entity.Librarian;
import by.htp.library.entity.Record;

public class CollectionData {

	private static CollectionData instance;
	private List<Book> books = new ArrayList<>();
	private List<EmployeeCard> readers = new ArrayList<>();

	private CollectionData() {

	}

	public static CollectionData getInstance() {
		if (instance == null) {
			instance = new CollectionData();
		}
		return instance;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<EmployeeCard> getReaders() {
		return readers;
	}

	public void setReaders(List<EmployeeCard> readers) {
		this.readers = readers;
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void addReaders(EmployeeCard reader) {
		readers.add(reader);
	}

	public Librarian getLibrarian() {
		return new Librarian(1, "librarian", "passw666");
	}

	public List<Record> getAllRecords() {
		List<Record> records = new ArrayList<>();
		for (EmployeeCard employee : readers) {
			records.addAll(employee.getRecords());
		}
		return records;
	}

}
