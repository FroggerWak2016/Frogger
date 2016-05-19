package v2;

import java.awt.image.BufferedImage;

public class Feld {

	private boolean bZuganglich = true;
	private BufferedImage biHintergrund;
	private Koordinate koKoordinate;
	private String sBezeichnung;
	
	public boolean isbZuganglich() {
		return bZuganglich;
	}
	
	public void setbZuganglich(boolean bZuganglich) {
		this.bZuganglich = bZuganglich;
	}
	
	public BufferedImage getBiHintergrund() {
		return biHintergrund;
	}
	
	public void setBiHintergrund(BufferedImage biHintergrund) {
		this.biHintergrund = biHintergrund;
	}

	public Koordinate getKoKoordinate() {
		return koKoordinate;
	}

	public void setKoKoordinate(Koordinate koKoordinate) {
		this.koKoordinate = koKoordinate;
	}

	public String getsBezeichnung() {
		return sBezeichnung;
	}

	public void setsBezeichnung(String sBezeichnung) {
		this.sBezeichnung = sBezeichnung;
	}

	public Feld(boolean bZuganglich, BufferedImage biHintergrund, Koordinate koKoordinate, String sBezeichnung) {
		super();
		this.bZuganglich = bZuganglich;
		this.biHintergrund = biHintergrund;
		this.koKoordinate = koKoordinate;
		this.sBezeichnung = sBezeichnung;
	}
	
	public Feld() {
		super();
	}
		
	
}
