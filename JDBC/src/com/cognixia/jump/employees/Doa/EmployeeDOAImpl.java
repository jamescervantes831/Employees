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
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

}
