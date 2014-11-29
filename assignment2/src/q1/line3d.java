package q1;

public class line3d {
	private point3d begin;
	private point3d end;
	public line3d(int x1 ,int y1, int z1, int x2, int y2, int z2) {
		begin = new point3d(x1, y1, z1);
		end = new point3d(x2, y2, z2);		
	}
	public line3d(point3d begin, point3d end) {
		this.begin = begin;
		this.end = end;
	}
	public point3d getBegin() {
		return begin;
	}
	public point3d getEnd() {
		return end;
	}
	public void setBegin(point3d begin) {
		this.begin = begin;
	}
	public void setEnd(point3d End) {
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
