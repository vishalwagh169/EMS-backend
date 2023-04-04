package net.javaguides.springboot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.Exception.ResourceNotFoundException;
import net.javaguides.springboot.Service.EmployeeService;
import net.javaguides.springboot.model.Employee;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@CrossOrigin(origins = "http://localhost:4200")

	@GetMapping("/employees")
//	@GetMapping("/allemployee")
//	http://localhost:8080/api/v1/employees
	public List<Employee> getallemployee() {
		List<Employee> employee = service.getallemployee();
		return employee;
	}

//	Create Employee REST API
//	(2)	http://localhost:8080/api/v1/employees

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		service.createEmployee(employee);
		return employee;
	}

//Get employee by Id api	
//	http://localhost:8080/api/v1/employees/5
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

//	Update Employee api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
		Employee employee = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with id :" + id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setId(employeeDetails.getId());

		Employee updatedEmployee = service.save(employee);

		return ResponseEntity.ok(updatedEmployee);

	}



	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id) {
		Employee employee = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with id :" + id));
		service.deleteEmployee(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}


}
