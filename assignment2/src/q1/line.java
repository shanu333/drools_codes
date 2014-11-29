package q1;

public class line {
	private point begin;
	private point end;
	public line(int x1 ,int y1, int x2, int y2) {
		begin = new point(x1, y1);
		end = new point(x2, y2);		
	}
	public line(point begin, point end) {
		this.begin = begin;
		this.end = end;
	}
	public point getBegin() {
		return begin;
	}
	public point getEnd() {
		return end;
	}
	public void setBegin(point begin) {
		this.begin = begin;
	}
	public void setEnd(point End) {
		this.end = End;
	}
	public int getBeginX() {
		return begin.getX();
	}
	public int getBeginY() {
		return begin.getY();
	}

	public int getEndX() {
		return end.getX();
	}
	public int getEndY() {
		return end.getY();
	}
	public String toString()
	{
		return "points are :" + begin.toString() + " and " + end.toString(); 
 	}
	public int getLength() {
		return (0);
	}
	
}
