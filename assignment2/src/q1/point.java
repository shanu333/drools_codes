package q1;

public class point {
	private int x;
	private int y;
	public point() {
		
	}
	public point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public point(point x) {
		this.setXY(x.getX(), x.getY());
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public point getXY()
	{
		return this;
	}
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	public String toString() {
		return " x : " + x + " y : " + y;
	}
}
