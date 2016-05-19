package v2;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Frosch {

	private int pixX;
	private int pixY;
	
	private int col;
	private int row;
	
	private String path = "/fosch_neu.png";
	BufferedImage bgImage;
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.pixX = 32*col;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.pixY = row*32;
		this.row = row;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public BufferedImage getBgImage() {
		return bgImage;
	}
	public void setBgImage(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}

	public int getPixX() {
		return pixX;
	}
	
	public void setPixX(int pixX) {
		this.pixX = pixX;
		this.col = (int)pixX/32;
	}
	
	public int getPixY() {
		return pixY;
	}
	
	public void setPixY(int pixY) {
		this.pixY = pixY;
		this.row = (int)pixY/32;
	}
	
	public Frosch(int col, int row){
		super();
		this.setCol(col);
		this.setRow(row);
		
		try {
			this.bgImage = ImageIO.read(this.getClass().getResource(this.getPath())).getSubimage(0, 0, 32, 32);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void moveTo(Koordinate d) {
		this.setCol(d.getX());
		this.setRow(d.getY());
	}
	
	public void moveTo(int x, int y) {
		this.setPixX(x);
		this.setPixY(y);
	}
	
	public Dimension getPosition() {
		Dimension d = new Dimension(this.getCol(), this.getRow());
		return d;
	}
	
	
	
	
	
	
}
