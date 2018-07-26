package by.htp.library.dao.impl.collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.EmployeeCard;
import by.htp.library.entity.Librarian;
import by.htp.library.entity.Record;

public class CollectionData {

	private static final String FILE = "data.bin";

	private static CollectionData instance;
	private List<Book> books = new ArrayList<>();
	private List<EmployeeCard> readers = new ArrayList<>();

	private CollectionData() {

	}

	public static CollectionData getInstance() {
		if (instance == null) {
			instance = new CollectionData();
			instance.read();
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

	public void saveData() {
		try {
			FileOutputStream fos = new FileOutputStream(FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(books);
			oos.writeObject(readers);
			oos.close();
		} catch (IOException e) {
			System.err.println("Can not save data");
		}

	}

	public void read() {
		try {
			FileInputStream fis = new FileInputStream(FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			books = (List<Book>) ois.readObject();
			readers = (List<EmployeeCard>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e1) {
			// first time application may not have file
			System.out.println("Application starts first time");
		} catch (ClassNotFoundException | IOException e2) {
			System.err.println("Can not read data");
			System.exit(1);
		}
	}

}
