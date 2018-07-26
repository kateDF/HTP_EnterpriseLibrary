package by.htp.library.dao.impl.collection;

import by.htp.library.entity.Book;
import by.htp.library.entity.EmployeeCard;

public class AbstractCollectionUtilDao extends AbstractCollectionDao {

	public Book getBookById(int id) {
		for (Book b : data.getBooks()) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
	}

	public EmployeeCard getEmployeeCardById(int idCard) {
		for (EmployeeCard employee : data.getReaders()) {
			if (employee.getId() == idCard) {
				return employee;
			}
		}
		return null;
	}

}
