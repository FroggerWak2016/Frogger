package frogger;

public class Frosch {

	private int col;
	private int row;
	private String path = "/frosch.png";
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Frosch(int col, int row) {
		super();
		this.col = col;
		this.row = row;
	}
	
	public void moveTo(Dimension d) {
		this.setCol(d.getWidth());
		this.setRow(d.getHeight());
	}
	
	public Dimension getPosition() {
		Dimension d = new Dimension(this.getCol(), this.getRow());
		return d;
	}

	
	
	
	
	
}
