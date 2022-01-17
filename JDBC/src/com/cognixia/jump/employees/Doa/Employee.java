package com.cognixia.jump.employees.Doa;

public class Employee {
	
	private int id;
	private String name;
	private String department;
	private int age;
	private int yearsOfService;
	private String position;
	
	public Employee() {}
	
	public Employee(int id, String name, String department, int age, int yearsOfService, String position) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.age = age;
		this.yearsOfService = yearsOfService;
		this.position = position;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getYearsOfService() {
		return yearsOfService;
	}

	public void setYearsOfService(int yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	/**this.id = id;
		this.name = name;
		this.department = department;
		this.age = age;
		this.yearsOfService = yearsOfService;
		this.position = position;*/
	@Override
	public String toString() {
		return "id= "+ this.id + "\n"+
				"name= " + this.name + "\n"+
				"department= " + this.department+ "\n"+
				"age= "  + this.age+ "\n"+
				"yearsOfService= " + this.yearsOfService + "\n"+
				"position= " + this.position+ "\n";
	}
}
