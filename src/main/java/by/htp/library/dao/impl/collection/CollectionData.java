package by.htp.library.dao.impl.collection;

import java.util.ArrayList;
import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Librarian;

public class CollectionData {

	private static CollectionData instance;
	private List<Book> books = new ArrayList<>();

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

	public void addBook(Book book) {
		books.add(book);
	}

	public Librarian getLibrarian() {
		return new Librarian(1, "librarian", "passw666");
	}

}
