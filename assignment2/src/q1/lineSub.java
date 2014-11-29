package q1;


public class lineSub extends point {
	
	private point end;
	public lineSub(int x1 ,int y1, int x2, int y2) {
		super(x1, y1);
		end = new point(x2, y2);		
	}
	public lineSub(point begin, point end) {
		super.setXY(begin.getX(), begin.getY());
		this.end = end;
	}
	public point getBegin() {
		return super.getXY();
	}
	public point getEnd() {
		return end;
	}
	public void setBegin(point begin) {
		super.setXY(begin.getX(), begin.getY());
	}
	public void setEnd(point End) {
		this.end = End;
	}
	public int getBeginX() {
		return super.getX();
	}
	public int getBeginY() {
		return super.getY();
	}

	public int getEndX() {
		return end.getX();
	}
	public int getEndY() {
		return end.getY();
	}
	public String toString()
	{
		return "points are :" + super.toString() + " and " + end.toString(); 
 	}
	public int getLength() {
		double a,b;
		a = (double)(getBeginX() - getEndX());
		a = a * a;
		b = (double) (getBeginY() - getEndY());
		b = b * b;
		return (int) Math.sqrt(a+b);
	}
	
}
