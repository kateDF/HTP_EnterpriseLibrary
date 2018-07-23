package by.htp.library.dao;

import by.htp.library.entity.EmployeeCard;

public interface EmployeeCardDao {

	EmployeeCard find(int idCard, String password);

	EmployeeCard create(EmployeeCard employeeCard);

}
