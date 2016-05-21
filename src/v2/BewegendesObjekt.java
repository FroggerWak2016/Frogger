package v2;

import java.awt.image.BufferedImage;

public abstract class BewegendesObjekt {


	protected AktionsReihe reihe;
	protected int x;
	protected int y;
	

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
	
	public void bewegeVor(int iGeschwindigkeit, int iRichtung) {
		this.reihe.spSpielfeld.lock.lock();
			x += iGeschwindigkeit*iRichtung;
		this.reihe.spSpielfeld.lock.unlock();
	}
	
	@Override
	public String toString() {
		return "BewegendesObjekt [x=" + x + ", y=" + y + "]";
	}
	
	public boolean inSpielfeld(Spielfeld s) {
		if(x > -64 && x < Spielfeld.FELDER_X*32) {
			return true;
		}
		return false;
	}

	
}
