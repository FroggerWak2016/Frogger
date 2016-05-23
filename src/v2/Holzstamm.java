package v2;

public class Holzstamm extends BewegendesObjekt {

	public Holzstamm(int reihe, int spalte, AktionsReihe arAktionsreihe) {
		super();
		this.y = reihe * Settings.FELDPIXEL;
		this.x = spalte * Settings.FELDPIXEL;
		this.arAktionsreihe = arAktionsreihe;
	}
}
