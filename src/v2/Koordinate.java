package v2;

public class Koordinate {

	private int x;
	private int y;
	
	public Koordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Koordinate [x=" + x + ", y=" + y + "]";
	}
	
	public boolean istInDimension(Dimension d) {
		
		if(this.getX() < d.getWidth() && this.getX() >= 0) {
			if(this.getY() < d.getHeight() && this.getY() >= 0) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
