package v2;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Frosch implements Runnable {

	private int pixX;
	private int pixY;
	
	private int col;
	private int row;
	
	private boolean bAufHolz = false;
	private BewegendesObjekt hHolz = null;
	
	private String path = "/frosch2.png";
	BufferedImage bgImage;
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.pixX = Settings.FELDPIXEL*col;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.pixY = row*Settings.FELDPIXEL;
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
		this.col = (int)pixX/Settings.FELDPIXEL;
	}
	
	public int getPixY() {
		return pixY;
	}
	
	public void setPixY(int pixY) {
		this.pixY = pixY;
		this.row = (int)pixY/Settings.FELDPIXEL;
	}
	
	public Frosch(int col, int row){
		super();
		this.setCol(col);
		this.setRow(row);
		
		try {
			this.bgImage = ImageIO.read(this.getClass().getResource(this.getPath())).getSubimage(0, 0, 35, 35);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void moveTo(KoordinateFein d) {
		this.setPixX(d.getX());
		this.setPixY(d.getY());
	}
	
	public void moveTo(int x, int y) {
		this.setPixX(x);
		this.setPixY(y);
	}
	
	public Dimension getPosition() {
		Dimension d = new Dimension(this.getCol(), this.getRow());
		return d;
	}
	
	public boolean isbAufHolz() {
		return bAufHolz;
	}
	public void setbAufHolz(boolean bAufHolz) {
		this.bAufHolz = bAufHolz;
	}
	public BewegendesObjekt gethHolz() {
		return hHolz;
	}
	public void sethHolz(BewegendesObjekt hHolz) {
		this.hHolz = hHolz;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public KoordinateFein getCenter() {
		return new KoordinateFein(this.getPixX()+17,this.getPixY()+17);
	}
	
	/*
	@Override
	public void run() {
	
		while(true) {
			if(hHolz != null) {
				System.out.println(Thread.currentThread().getName()+" Bewege Frosch: ");
				this.setPixX(this.getPixX()+(hHolz.arAktionsreihe.iGeschwindigkeit * hHolz.arAktionsreihe.iRichtung));
				//this.setPixX(this.getPixX());
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}*/
	
	
	
	
	
	
}
