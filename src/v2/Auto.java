package v2;

import java.awt.image.BufferedImage;

public class Auto extends Thread {

	private int x;
	private int y;
	private BufferedImage bBild;
	private int iGeschwindigkeit;
	private int iRichtung; // 1 = links ; 2 = rechts
	Spielfeld s;
	
	@Override
	public void run() {
		if(iRichtung == 1) {
			while(x < Spielfeld.FELDER_X*32 && s.alive) {
				x++;
				s.checkAutos();
				s.repaint();
				
				if(x == 100) s.addAuto();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(x >= Spielfeld.FELDER_X*32)
				s.autos.remove(this);
		}
	}
	
	public Auto(int x, int y, BufferedImage bBild, int iGeschwindigkeit, int iRichtung, Spielfeld s) {
		super();
		this.x = x;
		this.y = y;
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
	
	
}
