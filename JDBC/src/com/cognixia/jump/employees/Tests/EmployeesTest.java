package com.cognixia.jump.employees.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cognixia.jump.employees.Doa.Employee;
import com.cognixia.jump.employees.Doa.EmployeeDOAImpl;
import com.cognixia.jump.employees.connection.ConnectionManager;

class EmployeesTest {
	
	private static Connection conn = null;;
	private static List<Employee> employees = new ArrayList<Employee>();
	private static EmployeeDOAImpl empDoa = new EmployeeDOAImpl();
	private static Employee emp = new Employee();
	
	@BeforeAll
	public static void setup() {
		conn = ConnectionManager.getConnection();
		System.out.println("Connection has been made!");
		employees = empDoa.getAllEmployees();
		System.out.println("Got list of employees");
		emp = empDoa.getEmployeeById(1);
		System.out.println("Got employee by ID");
	}
	@AfterAll
	public static void cleanUp() {
		try{
			conn.close();
			System.out.println();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testGetAllEmployees() {
		 
		//execute
		List<Employee> actual = new ArrayList<Employee>();
		actual = empDoa.getAllEmployees();
		
		//assert
		assertEquals(employees, actual);
	}
	@Test
	void testGetEmployeeById() {
		
		//execute
		Employee actual = new Employee();
		actual = empDoa.getEmployeeById(1);
		
		//assert
		assertEquals(emp, actual);
	}
}
