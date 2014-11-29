package q1;

public class test3d {
	public static void print(String s) {
		System.out.println(s);
	}
	public static void main(String[] args) {
		
		// using line(x1, y1, x2, y2)
		line3d l = new line3d(1, 2, 3, 4,5 ,6);
		
		print(l.toString());
		print("Begin = " +l.getBegin().toString());
		print("End = " + l.getEnd().toString());
		
		point3d begin = new point3d(10, 20, 50);
		point3d end = new point3d(30, 40, 100);
		line3d l1 = new line3d (begin, end);

		print(l1.toString());
		print("Begin = " +l1.getBegin().toString());
		print("End = " + l1.getEnd().toString());
	}
}
