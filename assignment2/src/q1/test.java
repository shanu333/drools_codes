package q1;

public class test {
	public static void print(String s) {
		System.out.println(s);
	}
	public static void main(String[] args) {
		
		// using line(x1, y1, x2, y2)
		line l = new line(1, 2, 3, 4);
		
		print(l.toString());
		print("Begin = " +l.getBegin().toString());
		print("End = " + l.getEnd().toString());
		
		point begin = new point(10, 20);
		point end = new point(30, 40);
		line l1 = new line (begin, end);

		print(l1.toString());
		print("Begin = " +l1.getBegin().toString());
		print("End = " + l1.getEnd().toString());
	}
}
