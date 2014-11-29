package q3;

public class Project {
	
	private int cost;
	private String title;
	Project( int cost, String t) {
		
		this.cost = cost;
		title = t;
	}
	public Project () {
		
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
