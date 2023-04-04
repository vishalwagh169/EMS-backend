package net.javaguides.springboot.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import net.javaguides.springboot.model.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	SessionFactory sf;

	public List<Employee> getallEmployee() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employee = criteria.list();
		session.close();
		return employee;
	}

	public boolean createEmployee(Employee employee) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.save(employee);
		tr.commit();
		session.close();
		return true;

	}

	public Optional<Employee> findById(int id) {
		Session session = sf.openSession();
		Employee employee = session.get(Employee.class, id);
		session.close();
		return Optional.ofNullable(employee);

	}

	public Employee save(Employee employee) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.saveOrUpdate(employee);
		tr.commit();
		session.close();
		return employee;
	}
	public void deleteEmployee(Employee employee) {
	    Session session = sf.openSession();
	    Transaction transaction = session.beginTransaction();
	    Employee employeeToDelete = session.get(Employee.class, employee.getId());
	    session.delete(employeeToDelete);
	    transaction.commit();
	    session.close();
	}



//	public void delete(Employee employee) {
//	    Session session = sf.openSession();
//	    Transaction transaction = session.beginTransaction();
//	    session.delete(employee);
//	    transaction.commit();
//	    session.close();
//	}


	


	

}
