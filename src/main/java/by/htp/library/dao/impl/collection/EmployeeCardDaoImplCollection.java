package by.htp.library.dao.impl.collection;

import java.util.List;

import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.entity.EmployeeCard;

public class EmployeeCardDaoImplCollection extends AbstractCollectionDao implements EmployeeCardDao {

	@Override
	public List<EmployeeCard> searchAll() {
		return data.getReaders();
	}

	@Override
	public EmployeeCard find(int idCard, String password) {
		for (EmployeeCard employee : data.getReaders()) {
			if (employee.getId() == idCard && employee.getPassword() == password) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public EmployeeCard find(int idCard) {
		for (EmployeeCard employee : data.getReaders()) {
			if (employee.getId() == idCard) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public EmployeeCard create(EmployeeCard employeeCard) {
		employeeCard.setId(extractNextId(data.getReaders()));
		data.addReaders(employeeCard);
		return employeeCard;
	}

}
