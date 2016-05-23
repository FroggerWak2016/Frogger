package v2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.JPanel;




public class Spielfeld extends JPanel {

	final Lock lock = new ReentrantLock();
	private static final long serialVersionUID = 1L;

	public Spielfenster SPIELFENSTER;
	
	public boolean bObjekteBewegen = true;
	public boolean bInLevel = false;
	public boolean bLebendig = true;
	private boolean bLevelOk;
	
	private ArrayList<ArrayList<Feld>> alStruktur = new ArrayList<ArrayList<Feld>>();
	private ArrayList<Integer> alReihenMitWasser = new ArrayList<Integer>();
	private ArrayList<Integer> alReihenMitStrasse = new ArrayList<Integer>();
	public ArrayList<AktionsReihe> alAktionsreihen = new ArrayList<AktionsReihe>();
	
	private BufferedImage biGrass;
	private BufferedImage biWasser;
	private BufferedImage biBaum;
	private BufferedImage biStrasse;
	private BufferedImage biAuto;
	private BufferedImage biAutoRueck;
	private BufferedImage biHolz;
	
	public Frosch frFrosch;
	
	private Thread t;
	public Spielfeld(Spielfenster spielfenster) {		
		
		this.SPIELFENSTER = spielfenster;
		// Hintergrundbilder laden
		try {
			biGrass = ImageIO.read(this.getClass().getResource("/backgrounds/grass.gif")).getSubimage(0, 0, 32, 32);
			biWasser = ImageIO.read(this.getClass().getResource("/backgrounds/wasser.gif")).getSubimage(0, 0, 32, 32);
			biBaum = ImageIO.read(this.getClass().getResource("/backgrounds/tree.png")).getSubimage(0, 0, 32, 32);
			biStrasse = ImageIO.read(this.getClass().getResource("/backgrounds/strasse.png")).getSubimage(0, 0, 32, 32);
			biAuto = ImageIO.read(this.getClass().getResource("/auto.png")).getSubimage(0, 0, 64, 32);
			biAutoRueck = ImageIO.read(this.getClass().getResource("/auto_rueck.png")).getSubimage(0, 0, 64, 32);
			biHolz = ImageIO.read(this.getClass().getResource("/auto2.png")).getSubimage(0, 0, 64, 32);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		if(bInLevel && bLevelOk) {
			
			//Hintergrund formen
			for(ArrayList<Feld> alRow : alStruktur) {
				for(Feld fFeld : alRow) {
					g2.drawImage(fFeld.getBiHintergrund(), fFeld.getKoKoordinate().getX()*Settings.FELDPIXEL,  fFeld.getKoKoordinate().getY()*Settings.FELDPIXEL, fFeld.getBiHintergrund().getWidth(), fFeld.getBiHintergrund().getHeight(), null);
				}
			}
			
			
			lock.lock();
				for(AktionsReihe arAktionsreihe : alAktionsreihen) {
					for(BewegendesObjekt boObjekt : arAktionsreihe.alObjekte) {
						g2.drawImage(arAktionsreihe.getBiBild(), boObjekt.getX(), boObjekt.getY(), boObjekt.getiBreite(), boObjekt.getiHoehe(), null);
					}
				}
			lock.unlock();
			//Frosch formen
			g2.drawImage(frFrosch.bgImage, frFrosch.getPixX(), frFrosch.getPixY(), frFrosch.bgImage.getWidth(), frFrosch.bgImage.getHeight(), null);
			
			this.checkObAlive();
			
		} else {
			
			// Hintergrund im Einstellungsfenster
			for(int i = 0; i < Settings.REIHEN; i++) {
				for(int j = 0; j < Settings.SPALTEN; j++) {
					g2.drawImage(biGrass, j*Settings.FELDPIXEL, i*Settings.FELDPIXEL, Settings.FELDPIXEL, Settings.FELDPIXEL, null);
				}
			}
		}
	}
	
	// Liest txt Datei ein und füllt ein mehrdimensionales Array mit Feldern
	public void baueLevel(int iLevel) {
		alAktionsreihen.clear();
		
		String[] sStrukturAlsArray = Utils.loadFileAsString("/level/level_"+iLevel+".txt").split("\\s");
		
		for(int y = 0; y < Settings.REIHEN; y++) {
			ArrayList<Feld> alReihenstruktur = new ArrayList<Feld>();
			for(int x = 0; x < Settings.SPALTEN; x++) {
				
				Feld f = new Feld();
				switch(Utils.toInt(sStrukturAlsArray[y*Settings.SPALTEN+x])) {
					case 1: f = new Feld(true, biGrass, new Koordinate(x,y), "Grass" ); break;
					case 2: f = new Feld(true, biWasser, new Koordinate(x,y), "Wasser" ); break;
					case 3: f = new Feld(false, biBaum, new Koordinate(x,y), "Baum" ); break;
					case 4: f = new Feld(true, biStrasse, new Koordinate(x,y), "Straße" ); break;
					default: System.out.println("Problem!"); break;
				}
				
				alReihenstruktur.add(f);
			}
			alStruktur.add(alReihenstruktur);
		}
		 
		bLevelOk = checkObOk();
		
		if(bLevelOk) {

			
			frFrosch = new Frosch(Settings.SPALTEN/2,Settings.REIHEN-1);
			new Thread(frFrosch).start();
			bLebendig = true;

			new Thread(new SpielfeldMoveObjects(this)).start();;

			Strasse s1 = new Strasse(14, 3, 1, 3, this, biAuto);
			Strasse s2 = new Strasse(13, 4, -1, 4, this, biAutoRueck);
			Strasse s3 = new Strasse(12, 2, 1, 2, this, biAuto);
			
			Fluss f1 = new Fluss(1, 3, 1, 3, this, biHolz);
			Fluss f2 = new Fluss(2, 3, -1, 3, this, biHolz);
			Fluss f3 = new Fluss(5, 3, 1, 3, this, biHolz);
			Fluss f4 = new Fluss(7, 1, -1, 3, this, biHolz);
			
			alAktionsreihen.add(s1);
			alAktionsreihen.add(s2);
			alAktionsreihen.add(s3);
			alAktionsreihen.add(f1);
			alAktionsreihen.add(f2);
			alAktionsreihen.add(f3);
			alAktionsreihen.add(f4);
			
			for(AktionsReihe arAktionsreihe : alAktionsreihen)
				new Thread(arAktionsreihe).start();
			
			bInLevel = true;
			repaint();
		} else {
			System.out.println("In der Levelstruktur ist ein Fehler!");
		}
	}
	
	/*
	 * Diese Methode dient der Bewegung des Frosches
	 */
	public void move(int iDirection) {
		lock.lock();
		if(bLevelOk && bLebendig) {
			Koordinate k = new Koordinate(frFrosch.getCol(), frFrosch.getRow());
			switch(iDirection) {
				case 37: k = moveLeft(); break;
				case 38: k = moveUp(); break;
				case 39: k = moveRight(); break;
				case 40: k = moveDown(); break;
				default: break;
			}
			
			if(inSpielfeld(k)) { // ist in Spielfeld
				Feld feldGehezu = alStruktur.get(k.getY()).get(k.getX());
				if(feldGehezu.isbZuganglich()) {					
					
					
					frFrosch.moveTo(k);
					frFrosch.sethHolz(null);
					//Überprüfe Flüsse
					BewegendesObjekt boAufObjekt = null;
					for(AktionsReihe arAktionsreihe : alAktionsreihen) {
						if(arAktionsreihe.getiReihe() == k.getY() && arAktionsreihe.getClass() == Fluss.class) {
							boolean bLebendigTmp = false;
							for(BewegendesObjekt hHolz : arAktionsreihe.getObjekte()) {
								if(frFrosch.getPixX()+17 >= hHolz.getX() && frFrosch.getPixX() +16 <= hHolz.getX() + hHolz.getiBreite() ) {
									bLebendigTmp = true;
									boAufObjekt = hHolz;
									int iDiff = frFrosch.getPixX()+17 - hHolz.getX();
									frFrosch.setPixX(hHolz.getX()+(int)(iDiff/32)*32);
									break;
								}
							}
							bLebendig = bLebendigTmp;
						}
					}
					if(boAufObjekt != null) {
						frFrosch.sethHolz(boAufObjekt);
					}
					
					repaint();
				}
			}
		}
		lock.unlock();
		
	}
	
	// Funktionen zum Bewegen
	private Koordinate moveUp() {
		return new Koordinate(frFrosch.getCol(), frFrosch.getRow()-1);
	}
	private Koordinate moveDown() {
		return new Koordinate(frFrosch.getCol(), frFrosch.getRow()+1);	
	}
	private Koordinate moveLeft() {
		return new Koordinate(frFrosch.getCol()-1, frFrosch.getRow());	
	}
	private Koordinate moveRight() {
		return new Koordinate(frFrosch.getCol()+1, frFrosch.getRow());	
	}
	
	/*
	 * Überbrüft ob eine Koordinate im Spielfeld liegt
	 */
	private boolean inSpielfeld(Koordinate kKoordinate) {
		if(kKoordinate.getX() >= 0 && kKoordinate.getY() >= 0) {
			if(kKoordinate.getX() < Settings.SPALTEN && kKoordinate.getY() < Settings.REIHEN) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * Diese Methode überprüft die Hintergrundstruktur des Spielfeldes
	 * Dabei wird überprüft, ob eine Straßen, oder Flussreihe auch vollständig ist
	 */
	private boolean checkObOk() {
		
		for(int y = 0; y < Settings.REIHEN; y++) {
			for(int x = 0; x < Settings.SPALTEN; x++) {
				if(alStruktur.get(y).get(x).getsBezeichnung().equals("Wasser")) {
					alReihenMitWasser.add(y);
					break;
				}
				if(alStruktur.get(y).get(x).getsBezeichnung().equals("Strasse")) {
					alReihenMitStrasse.add(y);
					break;
				}
			}
		}
		
		boolean fehler = false;
		
		//Check on Wasser
		for(int y : alReihenMitWasser) {
			for(int x = 0; x < Settings.SPALTEN; x++) {
				if(!alStruktur.get(y).get(x).getsBezeichnung().equals("Wasser")) {
					fehler = true;
					break;
				}
			}
		}
		
		//Check ob Straße
		if(!fehler) {
			for(int y : alReihenMitStrasse) {
				for(int x = 0; x < Settings.SPALTEN; x++) {
					if(!alStruktur.get(y).get(x).getsBezeichnung().equals("Strasse")) {
						fehler = true;
						break;
					}
				}
			}
		}
		
		return !fehler;
	}
	
	/*
	 * Diese Methode prüft, ob der Frosch mit einem Bewegenden Objekt aus allen 
	 * Aktionsreihen kollidiert ist
	 */
	public void checkObAlive() {
		lock.lock();
		for(AktionsReihe arAktionsreihe : this.alAktionsreihen) {
			if(frFrosch.getRow() == arAktionsreihe.iReihe) {
				if(arAktionsreihe.getClass() == Strasse.class) {
					for(BewegendesObjekt b : arAktionsreihe.alObjekte) {
		
						if(!(frFrosch.getPixX() > b.getX()+64 || frFrosch.getPixX()+32 < b.getX())) {
							System.out.println("HA!");
							bLebendig = false;
						}
					}
				} else
				if(arAktionsreihe.getClass() == Fluss.class) {
					//System.out.println("Fluss!");
				}
			}
		}
			
		if(!bLebendig) wennTod();
		lock.unlock();
	}
	
	public void wennTod() {
		SPIELFENSTER.btnSpielstart.setEnabled(true);
	}
}
