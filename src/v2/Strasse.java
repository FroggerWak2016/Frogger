package v2;

import java.awt.image.BufferedImage;

public class Strasse extends AktionsReihe implements Runnable {
	
	
	@Override
	public void run() {
		
		while(this.getSpSpielfeld().alive) {
			try {
				int i = (int)(Math.random()*2000+1000);
				Thread.sleep(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.spSpielfeld.lock.lock();
//			System.out.println("Zugriff neues Auto");
			Auto a = new Auto(this.iReihe, -2, this);
			this.objekte.add(a);
			
//			System.out.println("Zugriff neues Auto Ende");
			this.spSpielfeld.lock.unlock();
		}
		
	}
	
	
	public Strasse(int iReihe, int iGeschwindigkeit, int iRichtung, int iWiederholung, Spielfeld spSpielfeld, BufferedImage biBild) {
		this.setiReihe(iReihe);
		this.setiGeschwindigkeit(iGeschwindigkeit);
		this.setiRichtung(iRichtung);
		this.setiWiederholung(iWiederholung);
		this.setSpSpielfeld(spSpielfeld);
		this.setBiBild(biBild);
	}

}
