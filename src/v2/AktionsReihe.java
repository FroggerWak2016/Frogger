package v2;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class AktionsReihe extends Thread {

	protected int iGeschwindigkeit;	
	protected int iRichtung; 			// 1 = Rechts; -1 = Links
	protected int iWiederholung; 		// Sekunden, nachdem ein neues Auto auftaucht
	protected BufferedImage biBild;
	protected int iReihe;
	protected Spielfeld spSpielfeld;
	protected ArrayList<BewegendesObjekt> objekte = new ArrayList<BewegendesObjekt>();
	
	@Override
	public String toString() {
		return "AktionsReihe []";
	}
	public ArrayList<BewegendesObjekt> getObjekte() {
		return objekte;
	}
	public void setObjekte(ArrayList<BewegendesObjekt> objekte) {
		this.objekte = objekte;
	}
	public int getiReihe() {
		return iReihe;
	}
	public void setiReihe(int reihe) {
		this.iReihe = reihe;
	}
	public BufferedImage getBiBild() {
		return biBild;
	}
	public void setBiBild(BufferedImage biBild) {
		this.biBild = biBild;
	}
	public int getiGeschwindigkeit() {
		return iGeschwindigkeit;
	}
	public void setiGeschwindigkeit(int iGeschwindigkeit) {
		this.iGeschwindigkeit = iGeschwindigkeit;
	}
	public int getiRichtung() {
		return iRichtung;
	}
	public void setiRichtung(int iRichtung) {
		this.iRichtung = iRichtung;
	}
	public int getiWiederholung() {
		return iWiederholung;
	}
	public void setiWiederholung(int iWiederholung) {
		this.iWiederholung = iWiederholung;
	}
	public Spielfeld getSpSpielfeld() {
		return spSpielfeld;
	}
	public void setSpSpielfeld(Spielfeld spSpielfeld) {
		this.spSpielfeld = spSpielfeld;
	}
	
	
	
}
