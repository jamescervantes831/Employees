package com.cognixia.jump.employees.Doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.employees.connection.ConnectionManager;

public class EmployeeDOAImpl implements EmployeeDOA {
	
	Connection connection = ConnectionManager.getConnection();
	
	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Employees");
			while(rs.next()) {
				Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("name"), rs.getString("department"), rs.getInt("age"), rs.getInt("yearsOfService"), rs.getString("position"));
				employees.add(employee);
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = new Employee();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				employee.setId(rs.getInt("employee_id"));
				employee.setName(rs.getString("name"));
				employee.setDepartment(rs.getString("department"));
				employee.setAge(rs.getInt("age"));
				employee.setPosition(rs.getString("position"));
				employee.setYearsOfService(rs.getInt("yearsOfService"));
			}
			
			rs.close();
			statement.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public Employee getEmployeeByName(String name) {
		Employee employee = new Employee();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE name=?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				employee.setId(rs.getInt("employee_id"));
				employee.setName(rs.getString("name"));
				employee.setDepartment(rs.getString("department"));
				employee.setAge(rs.getInt("age"));
				employee.setPosition(rs.getString("position"));
				employee.setYearsOfService(rs.getInt("yearsOfService"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public boolean addEmployee(Employee emp) {
		int added = 0;
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO employees"
					+ " (employee_id, name, department, age, yearsOfService, position)"
					+ " VALUES(null, ?, ?, ?,?, ?)");
			statement.setString(1, emp.getName());
			statement.setString(2, emp.getDepartment());
			statement.setInt(3, emp.getAge());
			statement.setInt(4, emp.getYearsOfService());
			statement.setString(5, emp.getPosition());
			
			added = statement.executeUpdate();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(added == 1) return true;
		return false;
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		int updated = 0;
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE employees"
					+ " SET name=?, department=?, age=?, yearsOfService=?, position=?"
					+ " WHERE employee_id=?");
			
			statement.setString(1, emp.getName());
			statement.setString(2, emp.getDepartment());
			statement.setInt(3, emp.getAge());
			statement.setInt(4, emp.getYearsOfService());
			statement.setString(5, emp.getPosition());
			
			statement.setInt(6, emp.getId());
			
			updated = statement.executeUpdate();
			
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(updated == 1) return true;
		return false;
	}

	@Override
	public boolean deleteEmployee(Employee emp) {
		int deleted = 0;
			
		try {
			
			PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE employee_id=?");
			statement.setInt(1, emp.getId());
			deleted = statement.executeUpdate();
			statement.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(deleted == 1) return true;
		return false;
	}

}
