package by.htp.library.dao.impl.collection;

import java.util.List;

import by.htp.library.dao.EmployeeCardDao;
import by.htp.library.entity.EmployeeCard;

public class EmployeeCardDaoImplCollection extends AbstractCollectionUtilDao implements EmployeeCardDao {

	@Override
	public List<EmployeeCard> searchAll() {
		return data.getReaders();
	}

	@Override
	public EmployeeCard find(int idCard, String password) {
		for (EmployeeCard employee : data.getReaders()) {
			if (employee.getId() == idCard && employee.getPassword().equals(password)) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public EmployeeCard getById(int idCard) {
		return getEmployeeCardById(idCard);
	}

	@Override
	public EmployeeCard create(EmployeeCard employeeCard) {
		employeeCard.setId(extractNextId(data.getReaders()));
		data.addReaders(employeeCard);
		data.saveData();
		return employeeCard;
	}

}
