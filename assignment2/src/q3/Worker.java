package q3;

public class Worker extends Employee{
	Worker(String name, String empId) {
		super(name, empId);
		// TODO Auto-generated constructor stub
	}
	private Manager m;
	private String type_of_work;
	
	public Manager getM() {
		return m;
	}
	public void setM(Manager m) {
		this.m = m;
	}
	public String getType_of_work() {
		return type_of_work;
	}
	public void setType_of_work(String type_of_work) {
		this.type_of_work = type_of_work;
	}
	
}
