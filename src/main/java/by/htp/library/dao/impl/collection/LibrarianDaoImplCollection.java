package by.htp.library.dao.impl.collection;

import by.htp.library.dao.LibrarianDao;
import by.htp.library.entity.Librarian;

public class LibrarianDaoImplCollection extends AbstractCollectionDao implements LibrarianDao {

	@Override
	public Librarian findByLoginAndPassword(String login, String password) {
		Librarian librarian = data.getLibrarian();
		if (librarian.getLogin().equals(login) && librarian.getPassword().equals(password)) {
			return librarian;
		}
		return null;
	}

}
