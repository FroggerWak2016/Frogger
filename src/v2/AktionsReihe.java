package v2;

import java.awt.image.BufferedImage;

public abstract class AktionsReihe {

	private int iGeschwindigkeit;	
	private int iRichtung; 			// 1 = Rechts; -1 = Links
	private int iWiederholung; 		// Sekunden, nachdem ein neues Auto auftaucht
	private BufferedImage biBild;
	private int iReihe;
	
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
	
	
	
}
