package q3;

public class tester {
	
	public static void main(String[] args) {
		int n = 1000;
		Project pro = new Project(n, "exap");
		String eid = "1001";
		SwipeCard sc = new SwipeCard("as");
		Manager m = new Manager("shan", eid, sc, n, pro);
		System.out.println(m.SwipeCard(m.getCard()));
		System.out.println(m.SwipeCard(sc));
	}
}
