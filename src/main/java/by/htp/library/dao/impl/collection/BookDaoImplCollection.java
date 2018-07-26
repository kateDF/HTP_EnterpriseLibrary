package by.htp.library.dao.impl.collection;

import java.util.List;

import by.htp.library.dao.BookDao;
import by.htp.library.entity.Book;

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
		throw new UnsupportedOperationException();
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

}
