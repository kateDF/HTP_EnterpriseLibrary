package by.htp.library.dao.impl.collection;

import java.util.List;

import by.htp.library.dao.BookDao;
import by.htp.library.entity.Book;
import by.htp.library.entity.DbEntity;

public class BookDaoImplCollection implements BookDao {

	private CollectionData data = CollectionData.getInstance();

	@Override
	public Book getById(int id) {
		for (Book b : data.getBooks()) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
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

	private static <T extends DbEntity> int extractNextId(List<T> list) {
		int maxId = 0;
		for (DbEntity entity : list) {
			if (maxId < entity.getId()) {
				maxId = entity.getId();
			}
		}
		return maxId + 1;
	}

}
