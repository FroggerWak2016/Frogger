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
	
	public void goInDirection(int direction) {
		switch(direction) {
		case 37: this.setCol(this.getCol()-1); break;
		case 38: this.setRow(this.getRow()-1); break;
		case 39: this.setCol(this.getCol()+1); break;
		case 40: this.setRow(this.getRow()+1); break;
		default: System.out.println("none");	
	}
	}
	
	
	
	
	
}
