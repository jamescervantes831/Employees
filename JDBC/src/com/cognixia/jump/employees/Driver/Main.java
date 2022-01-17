package com.cognixia.jump.employees.Driver;

import java.util.Scanner;

import com.cognixia.jump.employees.Doa.Employee;
import com.cognixia.jump.employees.Doa.EmployeeDOAImpl;

public class Main {

	private static EmployeeDOAImpl empDOA = new EmployeeDOAImpl();
	
	public static void main(String[] args) {
		Main utils = new Main();
		Scanner sc = new Scanner(System.in);
		String response = "";
		
		while(!response.toLowerCase().equals("exit")){
			
			System.out.println("These are your options:");
			System.out.println("ADD EMPLOYEE || LIST EMPLOYEE INFORMATION || REMOVE EMPLOYEE || UPDATE EMPLOYEE || EXIT");
			response = sc.nextLine(); 
			
			if(response.toLowerCase().equals("add employee")) {
				Employee emp = utils.userResponse(sc);
				empDOA.addEmployee(emp);
				
			}else if(response.toLowerCase().equals("list employee information")) {
				
				System.out.println(utils.listEmpInfo(sc));
				
			}else if(response.toLowerCase().equals("remove employee") ) {
				
				utils.deleteEmployee(sc);
				
			}else if(response.toLowerCase().equals("update employee")) {
				System.out.println("Enter employee ID: ");
				int id = sc.nextInt();
				Employee emp = utils.userResponse(sc);
				empDOA.updateEmployee(id, emp);
			}
		}
		System.out.println("Goodbye");

	}
	
	//UTILITIES
	public Employee userResponse(Scanner sc) {
		
		System.out.print("Employee Name: ");
		String name = sc.nextLine();
		
		System.out.print("\nDepartment they work in: ");
		String department = sc.nextLine();
		
		System.out.println("\nAge: " );
		int age = sc.nextInt();
		
		System.out.print("\nYears of Service: ");
		int yearsOfService = sc.nextInt();
		
		System.out.print("\nPosition: ");
		String position = sc.next();
		
		return new Employee(0, name, department, age, yearsOfService, position);
	}
	
	public String listEmpInfo(Scanner sc) {
		
		String response = "";
		System.out.println("Employee by NAME or ID?");
		String choice = sc.nextLine();
		
		if(choice.toLowerCase().equals("name")) {
			System.out.print("Type employee name: ");
			String name = sc.nextLine();
			response += empDOA.getEmployeeByName(name).toString();
		}else if(choice.toLowerCase().equals("id")) {
			System.out.print("Type employee id: ");
			int id = sc.nextInt();
			response += empDOA.getEmployeeById(id).toString();
		}
		
		return response;
		
	}
	
	public void deleteEmployee(Scanner sc) {
		
		System.out.println("Remove by NAME or ID?");
		String choice = sc.nextLine();
		if(choice.toLowerCase().equals("name")) {
			System.out.print("Type employee name: ");
			String name = sc.nextLine();
			empDOA.deleteEmployee(name);
		}else if(choice.toLowerCase().equals("id")) {
			System.out.print("Type employee id: ");
			int id = sc.nextInt();
			empDOA.deleteEmployee(id);
		}
		
	}

}
