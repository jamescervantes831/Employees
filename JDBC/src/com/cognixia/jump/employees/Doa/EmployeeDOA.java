package com.cognixia.jump.employees.Doa;

import java.util.List;

public interface EmployeeDOA {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByName(String name);
	
	public boolean addEmployee(Employee emp);
	public boolean updateEmployee(Employee emp);
	public boolean deleteEmployee(Employee emp);
}
