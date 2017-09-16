package main;

public class Employee {
	private int empId;
	private String empfName;
	private String emplName;
	private String userId;
	
	public Employee(int empId, String empfName, String emplName, String userId) {
		this.empId = empId;
		this.empfName = empfName;
		this.emplName = emplName;
		this.userId = userId;
	}
	
	public int getEmpId() {
		return empId;
	}
	
	public String getEmpName() {
		return empfName;
	}
	
	public String getEmplName() {
		return emplName;
	}
	
	public String getUserId() {
		return userId;
	}
}
