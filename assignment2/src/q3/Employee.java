package q3;

public class Employee {
	private String name;
	private String empID;
	private int salary;
	
	Employee(String name, String empId) {
		this.name = name;
		empID = empId;
		salary = 100000;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
}
