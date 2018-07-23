package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;

public interface BookDao {

	Book getById(int id);

	List<Book> searchAll();

	List<Book> searchAllAvailable();

	void update(Book book);

	Book create(Book book);

}
