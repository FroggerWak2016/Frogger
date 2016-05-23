package v2;

public class Dimension {

	private int width;
	private int height;
	private Koordinate koordinate = new Koordinate(0,0);
	
	public Dimension(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Dimension [width=" + width + ", height=" + height + "]";
	}

	
	
}
