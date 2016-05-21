package v2;

import java.awt.image.BufferedImage;

public class Auto extends BewegendesObjekt {

	
	
	public Auto(int reihe, int spalte, AktionsReihe ar) {
		super();
		this.y = reihe * 32;
		this.x = spalte * 32;
		this.reihe = ar;
	}


	
}
