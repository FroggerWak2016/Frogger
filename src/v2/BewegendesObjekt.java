package v2;

public abstract class BewegendesObjekt {


	protected AktionsReihe arAktionsreihe; 	// Definiert in welche Reihe das Objekt liegt
	protected int x;						// Definiert x-Position des Objektes
	protected int y;						// Definiert y-Position des Objektes
	protected boolean hasObjekt;

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
	
	public AktionsReihe getArAktionsreihe() {
		return arAktionsreihe;
	}

	public void setArAktionsreihe(AktionsReihe arAktionsreihe) {
		this.arAktionsreihe = arAktionsreihe;
	}

	public int getiHoehe() {
		return this.getArAktionsreihe().getBiBild().getHeight();
	}

	public int getiBreite() {
		return this.getArAktionsreihe().getBiBild().getWidth();
	}


	// Diese Methode bewegt das Objekt vor
	public void bewegeVor() {
		this.arAktionsreihe.spSpielfeld.lock.lock();		// Unterbindet Parallelzugriff
		
			if(this.getClass() == Holzstamm.class) System.out.println("holz");
			x += this.arAktionsreihe.iGeschwindigkeit * this.arAktionsreihe.iRichtung;
		this.arAktionsreihe.spSpielfeld.lock.unlock();
	}
	
	// Gibt einen boolischen Wert zurück, abhängig, ob die Figur im Spielfeld ist, oder nicht
	public boolean inSpielfeld(Spielfeld s) {
		if(x >= (-1*this.getiBreite()) && x <= Settings.SPALTEN * Settings.FELDPIXEL) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "BewegendesObjekt [x=" + x + ", y=" + y + "]";
	}
	
	

	
}
