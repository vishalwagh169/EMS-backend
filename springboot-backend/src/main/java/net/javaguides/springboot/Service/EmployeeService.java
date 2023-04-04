package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Dao.EmployeeDao;
import net.javaguides.springboot.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao dao;

	public List<Employee> getallemployee() {
		List<Employee> employee = dao.getallEmployee();
		return employee;
	}

	public boolean createEmployee(Employee employee) {
		boolean inserted = dao.createEmployee(employee);
		return inserted;

	}

	public Optional<Employee> findById(int id) {
		Optional<Employee> employee = dao.findById(id);
		return employee;
	}

	public Employee save(Employee employee) {
		Employee savedEmployee = dao.save(employee);
		return savedEmployee;
	}

	public void deleteEmployee(Employee employee) {
	    dao.deleteEmployee(employee);
	}


	

}
