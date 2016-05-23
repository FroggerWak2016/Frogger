package v2;

import java.awt.image.BufferedImage;

public class Fluss extends AktionsReihe {

	@Override
	public void run() {
		
		while(this.getSpSpielfeld().bLebendig) {
			try {
				int i = (int)(Math.random()*2000+2000/iGeschwindigkeit+500);  	// Erstelle eine zufällige Zeit bis zum nächsten Hinzufügen eines Autos. Abhängig auch von der Geschwindigkeit.
				Thread.sleep(i);												// Unterbricht die Schleife für die oben berechnete Zeit
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.spSpielfeld.lock.lock();  								// Verhindere parallelen Zugriff
				int iSpalte = -2;
				if(iRichtung < 0)  iSpalte = 15;						// Definiere Startspalte
				
				Holzstamm h = new Holzstamm(this.iReihe, iSpalte, this);// Erstellt ein neues Auto
				this.alObjekte.add(h);									// Füge das Auto des Liste von Objekten hinzu
					
			this.spSpielfeld.lock.unlock();
		}
		
	}
	
	public Fluss(int iReihe, int iGeschwindigkeit, int iRichtung, int iWiederholung, Spielfeld spSpielfeld, BufferedImage biBild) {
		this.setiReihe(iReihe);
		this.setiGeschwindigkeit(iGeschwindigkeit);
		this.setiRichtung(iRichtung);
		this.setiWiederholung(iWiederholung);
		this.setSpSpielfeld(spSpielfeld);
		this.setBiBild(biBild);
	}
}
