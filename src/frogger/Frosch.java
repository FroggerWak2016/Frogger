package frogger;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Frosch {

	private int col;
	private int row;
	private String path = "/frosch.png";
	BufferedImage bgImage;
	
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
	public Frosch(int col, int row) throws IOException {
		super();
		this.col = col;
		this.row = row;
		this.bgImage = ImageIO.read(this.getClass().getResource(this.getPath())).getSubimage(0, 0, 32, 32);
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
