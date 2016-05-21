package v2;

import java.awt.image.BufferedImage;

public class Auto extends Thread {

	private int x;
	private int y;
	private BufferedImage bBild;
	private int iGeschwindigkeit; // Pixel / Sekunde
	private int iRichtung; // 1 = links ; 2 = rechts
	Spielfeld s;
	
	public Auto(int reihe, int spalte, BufferedImage bBild, int iGeschwindigkeit, int iRichtung, Spielfeld s) {
		super();
		this.x = reihe * 32;
		this.y = spalte * 32;
		this.bBild = bBild;
		this.iGeschwindigkeit = iGeschwindigkeit;
		this.iRichtung = iRichtung;
		this.s = s;
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
	
	public BufferedImage getbBild() {
		return bBild;
	}
	
	public void setbBild(BufferedImage bBild) {
		this.bBild = bBild;
	}
	
	public int getIGeschwindigkeit() {
		return iGeschwindigkeit;
	}
	
	public void setIGeschwindigkeit(int geschwindigkeit) {
		this.iGeschwindigkeit = geschwindigkeit;
	}
	
	public void bewegeVor() {
		if(this.iRichtung == 1)
			x += iGeschwindigkeit;
		else 
			x-= iGeschwindigkeit;
	}
	
}
