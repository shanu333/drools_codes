package q1;

public class test2 {
	public static void print(String s) {
		System.out.println(s);
	}
	public static void main(String[] args) {
		
		// using line(x1, y1, x2, y2)
		lineSub l = new lineSub(1, 2, 3, 4);
		
		print(l.toString());
		print("Begin = " +l.getBegin().toString());
		print("End = " + l.getEnd().toString());
		
		point begin = new point(10, 20);
		point end = new point(30, 40);
		lineSub l1 = new lineSub (begin, end);

		print(l1.toString());
		print("Begin = " +l1.getBegin().toString());
		print("End = " + l1.getEnd().toString());
		print("length = " + l1.getLength());
	}
}
