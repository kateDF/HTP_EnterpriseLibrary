package by.htp.library.dao.impl.collection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import by.htp.library.dao.BookDao;
import by.htp.library.entity.Book;
import by.htp.library.entity.Record;

public class BookDaoImplCollection extends AbstractCollectionUtilDao implements BookDao {

	@Override
	public Book getById(int id) {
		return getBookById(id);
	}

	@Override
	public List<Book> searchAll() {
		return data.getBooks();
	}

	@Override
	public List<Book> searchAllAvailable() {
		List<Book> allBooks = data.getBooks();
		List<Book> unavailableBooks = searchAllUnavailable();

		List<Book> availableBook = new ArrayList<>(CollectionUtils.subtract(allBooks, unavailableBooks));
		return availableBook;
	}

	@Override
	public void update(Book book) {
		for (Book b : data.getBooks()) {
			if (b.getId() == book.getId()) {
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
				b.setDescription(book.getDescription());
				break;
			}
		}
	}

	@Override
	public Book create(Book book) {
		book.setId(extractNextId(data.getBooks()));
		data.addBook(book);
		return book;
	}

	private List<Book> searchAllUnavailable() {
		List<Record> records = data.getAllRecords();
		List unavailableBooks = new ArrayList<>();
		for (Record record : records) {
			if (record.getReturnDate() == null) {
				unavailableBooks.add(record.getBook());
			}
		}
		return unavailableBooks;
	}

}
