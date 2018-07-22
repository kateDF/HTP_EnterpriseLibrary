package by.htp.library.dao;

import by.htp.library.entity.Librarian;

public interface LibrarianDao {

	Librarian findByLoginAndPassword(String login, String password);

}
