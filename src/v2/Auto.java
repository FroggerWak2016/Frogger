package v2;

public class Auto extends BewegendesObjekt {
	
	public Auto(int reihe, int spalte, AktionsReihe arAktionsreihe) {
		super();
		this.y = reihe * Settings.FELDPIXEL;
		this.x = spalte * Settings.FELDPIXEL;
		this.arAktionsreihe = arAktionsreihe;
	}
	
}
