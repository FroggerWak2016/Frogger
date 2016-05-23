package v2;

import java.util.ArrayList;

/* 
 * Diese Klasse bewegt alle n ms Alle Objekte auf dem Spielfeld um die in den jeweiligen Aktionsreihen definierten Felder (iGeschwindikkeit) vor.
 * Diese Klasse wird wiederholend ausgeführt, solange die Spielfigur am Leben ist.
 */

public class SpielfeldMoveObjects implements Runnable {

	private Spielfeld spSpielfeld;
	
	public SpielfeldMoveObjects(Spielfeld sSpielfeld) {
		this.spSpielfeld = sSpielfeld;
	}

	@Override
	public void run() {
		
		while(spSpielfeld.bLebendig) { 																				// Wiederhole solange die Spielfigur am Leben ist
			spSpielfeld.lock.lock();																			// Verhindert parallelen Zugriff
				
				for(AktionsReihe arAktionsreihe : spSpielfeld.alAktionsreihen) {									// Iteriere über alle Aktionsreihen
					ArrayList<BewegendesObjekt> toDelete = new ArrayList<BewegendesObjekt>();					// Array zum späteren Löschen von bewegenden Elementen, die aus dem Spielfeld sind
					for(BewegendesObjekt boBewegendesObjekt : arAktionsreihe.alObjekte) {							// Itereiere über alle bewegenden Objekte dieser Aktionsreihe
						boBewegendesObjekt.bewegeVor();															// Bewege das Objekt vor
						if(!boBewegendesObjekt.inSpielfeld(spSpielfeld)) 										// Wenn außerhalb Spielfeld: 
							toDelete.add(boBewegendesObjekt);													// Füge das Objekt der Liste zu löschender Objekte hinzu
					}
					arAktionsreihe.alObjekte.removeAll(toDelete);													// Löscht alle außerhalb liegenden Objekte
					
				}
				
				if(spSpielfeld.frFrosch.gethHolz() != null)
					spSpielfeld.frFrosch.setPixX(spSpielfeld.frFrosch.getPixX()+(spSpielfeld.frFrosch.gethHolz().arAktionsreihe.iGeschwindigkeit *spSpielfeld.frFrosch.gethHolz().arAktionsreihe.iRichtung));
				
			spSpielfeld.lock.unlock();
			
			spSpielfeld.repaint();				// Repainte Spielfeld
			
			try {
				Thread.sleep(30);				// Unterbreche für 30 ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
