package q1;

public class point3d extends point{
	private int z;
	public point3d() {
		
	}
	public point3d(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public String toString() {
		return super.toString() + " z : " + z;
	}
}
