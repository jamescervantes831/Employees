package com.cognixia.jump.employees.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private static String [][] data = {
			{"Christian Booboo", "Bobby Buflay", "James Cervantes", "Johnny Bravo", "Party Pooper", "Ready Or Not", "Mary J. Blidge"},
			{"Athletics", "Education", "Sanitation"},
			{"Quarterback", "Principal", "Collector"}
	};
	
	@BeforeAll
	public static void setup() {
		conn = ConnectionManager.getConnection();
		System.out.println("Connection has been made!");
		employees = empDoa.getAllEmployees();
		System.out.println("Got list of employees");
		emp = employees.get(0);
		System.out.println("Got employee by ID");
	}
	@AfterAll
	public static void cleanUp() {
		try{
			conn.close();
			System.out.println("Connection Closed");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetAllEmployees() {
		//execute
		List<Employee> actual = empDoa.getAllEmployees();
		
		//assert
		assertEquals(employees.get(0).getName(), actual.get(0).getName());
	}
	
	@Test
	void testGetEmployeeById() {
		//setup
		Employee expected = employees.get(new Random().nextInt(employees.size()));
		//execute
		Employee actual = empDoa.getEmployeeById(expected.getId());
		//assert
		assertEquals(expected.getName(), actual.getName());
	}
	
	@Test
	void testAddEmployee() {
		//setup
		int lengthBefore = employees.size();
		String name = data[0][new Random().nextInt(data[0].length)];
		int depPos = new Random().nextInt(data[1].length);
		
		//execute
		empDoa.addEmployee(new Employee(0, 
						name,
						data[1][depPos],
						new Random().nextInt(21)+20,
						new Random().nextInt(21),
						data[2][depPos]));
		
		int lengthAfter = empDoa.getAllEmployees().size();
		
		//assert
		assertTrue(lengthBefore < lengthAfter);
	}
	
	@Test
	void testGetEmployeeByName() {
		//setup
		String expected = employees.get(new Random().nextInt(employees.size())).getName();
		
		//execute
		String actual = empDoa.getEmployeeByName(expected).getName();
		
		//assert
		assertEquals(expected, actual);
	}
	
	@Test
	void testUpdateandDeleteEmployee() {
		
		//setup
		//update and delete
		String name = data[0][new Random().nextInt(data[0].length)];
		int depPos = new Random().nextInt(data[1].length);
		String department = data[1][depPos];
		String position = data[2][depPos];
		int id = employees.get(new Random().nextInt(employees.size())).getId();
		
		//execute
		boolean complete = empDoa.updateEmployee(id, new Employee(
							  0,//ID 
							  name,
							  department,
							  new Random().nextInt(40)+21, //age 
							  new Random().nextInt(20), //yearsOfService
							  position));
		
		complete = empDoa.deleteEmployee(emp.getName());
		//assert
		assertTrue(complete);
	}
}
