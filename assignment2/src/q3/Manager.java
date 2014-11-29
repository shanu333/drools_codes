package q3;

public class Manager extends Employee{
	private SwipeCard card;
	private int numberOfEmployees;
	private Project project;
	
	public Manager(String na, String id,SwipeCard sc, int n, Project pro) {
		super(na,id);
		card = sc;
		numberOfEmployees = n;
		project = pro;
	}
	
	public SwipeCard getCard() {
		return card;
	}
	public void setCard(SwipeCard card) {
		this.card = card;
	}
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}
	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public boolean SwipeCard ( SwipeCard sc) {
		System.out.println("Authenticating : ");
		if (this.getEmpID().equals(sc.getEmpID())) {
			return true;
		} 
		return false;
	}
}
