package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.EmployeeCard;

public interface EmployeeCardDao {

	List<EmployeeCard> searchAll();

	EmployeeCard find(int idCard, String password);

	EmployeeCard getById(int idCard);

	EmployeeCard create(EmployeeCard employeeCard);

}
