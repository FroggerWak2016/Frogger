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

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;




public class Spielfeld extends JPanel {

	final Lock lock = new ReentrantLock();
	private static final long serialVersionUID = 1L;

	ArrayList<Thread> treads = new ArrayList<Thread>();
	public static int FELDSEITE = 32;
	public static int FELDER_X = 15;
	public static int FELDER_Y = 17;
	
	public Spielfenster SPIELFENSTER;
	
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private ArrayList<Auto> autos = new ArrayList<Auto>();
	
	public boolean bInLevel = false;

	public boolean alive = true;
	
	private ArrayList<ArrayList<Feld>> alStructure = new ArrayList<ArrayList<Feld>>();
	private ArrayList<Integer> alReihenMitWasser = new ArrayList<Integer>();
	private ArrayList<Integer> alReihenMitStrasse = new ArrayList<Integer>();
	
	private BufferedImage grass;
	private BufferedImage wasser;
	private BufferedImage baum;
	private BufferedImage strasse;
	private BufferedImage auto;
	private Frosch fFrosch;
	private boolean bLebendig = true;
	
	private boolean bLevelOk;
	
	public Spielfeld(Spielfenster spielfenster) {
		
		this.SPIELFENSTER = spielfenster;
		// Hintergrundbilder laden
		try {
			grass = ImageIO.read(this.getClass().getResource("/backgrounds/grass.gif")).getSubimage(0, 0, 32, 32);
			wasser = ImageIO.read(this.getClass().getResource("/backgrounds/wasser.gif")).getSubimage(0, 0, 32, 32);
			baum = ImageIO.read(this.getClass().getResource("/backgrounds/tree.png")).getSubimage(0, 0, 32, 32);
			strasse = ImageIO.read(this.getClass().getResource("/backgrounds/strasse.png")).getSubimage(0, 0, 32, 32);
			auto = ImageIO.read(this.getClass().getResource("/auto.png")).getSubimage(0, 0, 64, 32);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		if(bInLevel && bLevelOk) {
			
			//Hintergrund formen
			for(ArrayList<Feld> alRow : alStructure) {
				for(Feld fFeld : alRow) {
					g2.drawImage(fFeld.getBiHintergrund(), fFeld.getKoKoordinate().getX()*32,  fFeld.getKoKoordinate().getY()*32,  32, 32, null);
				}
			}
			
			//Frosch formen
			g2.drawImage(fFrosch.bgImage, fFrosch.getPixX(), fFrosch.getPixY(), 32, 32, null);
			
			
			//Autos
			for(Auto a : autos) {

				g2.drawImage(auto, a.getX(), a.getY(), 64, 32, null);
			}
			
		} else {
			
			// Hintergrund im Einstellungsfenster
			for(int i = 0; i < FELDER_Y; i++) {
				for(int j = 0; j < FELDER_X; j++) {
					g2.drawImage(grass, j*32, i*32, 32, 32, null);
				}
			}
		}
	}
	
	// Liest txt Datei ein und füllt ein mehrdimensionales Array mit Feldern
	public void baueLevel(int iLevel) {
		
		lock.lock();
		autos.clear();
		lock.unlock();
		
		String[] sStructureAsArray = Utils.loadFileAsString("/level/level_"+iLevel+".txt").split("\\s");
		
		for(int y = 0; y < Spielfeld.FELDER_Y; y++) {
			ArrayList<Feld> alRowstructure = new ArrayList<Feld>();
			for(int x = 0; x < Spielfeld.FELDER_X; x++) {
				
				Feld f = new Feld();
				switch(Utils.toInt(sStructureAsArray[y*Spielfeld.FELDER_X+x])) {
					case 1: f = new Feld(true, grass, new Koordinate(x,y), "Grass" ); break;
					case 2: f = new Feld(true, wasser, new Koordinate(x,y), "Wasser" ); break;
					case 3: f = new Feld(false, baum, new Koordinate(x,y), "Baum" ); break;
					case 4: f = new Feld(true, strasse, new Koordinate(x,y), "Straße" ); break;
					default: System.out.println("Problem!"); break;
				}
				
				alRowstructure.add(f);
			}
			alStructure.add(alRowstructure);
		}
		 
		bLevelOk = checkObOk();
		System.out.println(bLevelOk);
		if(bLevelOk) {
			fFrosch = new Frosch(Spielfeld.FELDER_X/2,Spielfeld.FELDER_Y-1);
			alive = true;
			
			new Thread(new SpielfeldMoveObjects(this)).start();;
			new Thread(new SpielfeldAddObject(this)).start();;
			
			System.out.println(Thread.activeCount());
			bInLevel = true;
			
			addAuto();
			
			repaint();
		}
	}
	
	// Bewege Frosch
	public void move(int iDirection) {
		if(bLevelOk && alive) {
			Koordinate k = new Koordinate(fFrosch.getCol(),fFrosch.getRow());
			switch(iDirection) {
				case 37: k = moveLeft(); break;
				case 38: k = moveUp(); break;
				case 39: k = moveRight(); break;
				case 40: k = moveDown(); break;
				default: break;
			}
			
			if(inSpielfeld(k)) { // ist in Spielfeld
				Feld feldGehezu = alStructure.get(k.getY()).get(k.getX());
				if(feldGehezu.isbZuganglich()) {
									
					// Wenn auf ein Wasserfeld
					if(feldGehezu.getsBezeichnung().equals("Wasser")) {
						System.out.println("check wasser");
					}
					
					// Wenn auf eine Straße
					if(feldGehezu.getsBezeichnung().equals("Straße")) {
						System.out.println("check Straße");
					}
					
					if(bLebendig)
						fFrosch.moveTo(k);
					repaint();
				}
			}
		}
		
	}
	
	// Funktionen zum Bewegen
	private Koordinate moveUp() {
		return new Koordinate(fFrosch.getCol(), fFrosch.getRow()-1);
	}
	private Koordinate moveDown() {
		return new Koordinate(fFrosch.getCol(), fFrosch.getRow()+1);		
	}
	private Koordinate moveLeft() {
		return new Koordinate(fFrosch.getCol()-1, fFrosch.getRow());		
	}
	private Koordinate moveRight() {
		return new Koordinate(fFrosch.getCol()+1, fFrosch.getRow());		
	}
	
	// Funktion die Prüft, ob in Spielfeld
	private boolean inSpielfeld(Koordinate kKoordinate) {
		if(kKoordinate.getX() >= 0 && kKoordinate.getY() >= 0) {
			if(kKoordinate.getX() < Spielfeld.FELDER_X && kKoordinate.getY() < Spielfeld.FELDER_Y) {
				return true;
			}
		}
		return false;
	}
	
	
	private boolean checkObOk() {
		
		
		for(int y = 0; y < FELDER_Y; y++) {
			boolean found = false;
			for(int x = 0; x < FELDER_X; x++) {
				if(alStructure.get(y).get(x).getsBezeichnung().equals("Wasser")) {
					alReihenMitWasser.add(y);
					found = true;
					break;
				}
				if(alStructure.get(y).get(x).getsBezeichnung().equals("Strasse")) {
					alReihenMitStrasse.add(y);
					found = true;
					break;
				}
			}
		}
		
		boolean fehler = false;
		
		//Check on Wasser
		for(int y : alReihenMitWasser) {
			for(int x = 0; x < FELDER_X; x++) {
				if(!alStructure.get(y).get(x).getsBezeichnung().equals("Wasser")) {
					fehler = true;
					break;
				}
			}
		}
		
		//Check ob Straße
		if(!fehler) {
			for(int y : alReihenMitStrasse) {
				for(int x = 0; x < FELDER_X; x++) {
					if(!alStructure.get(y).get(x).getsBezeichnung().equals("Strasse")) {
						fehler = true;
						break;
					}
				}
			}
		}
		
		return !fehler;
	}
	
	public void checkAutos() {
		lock.lock();
		for(Auto a  : autos) {
			if(fFrosch.getPixY() == a.getY()) {
				if(!(fFrosch.getPixX() > a.getX()+64 || fFrosch.getPixX()+32 < a.getX())) {
					alive = false;
				}
			}
		}
		if(!alive) wennTod();
		lock.unlock();
	}
	
	
	public void addAuto() {
		Auto newAuto = new Auto(-2, 14, auto, 4, 1, this);
		Auto newAuto2 = new Auto(this.FELDER_X+2, 13, auto, 4, -1, this);
		Auto newAuto3 = new Auto(this.FELDER_X+2, 12, auto, 3, -1, this);
		lock.lock();
		autos.add(newAuto);
		autos.add(newAuto2);
		autos.add(newAuto3);
		
		lock.unlock();
	}
	
	public void moveAutos() {
		lock.lock();
		for(Auto a : autos) {
			a.bewegeVor();
		}
		this.repaint();
		lock.unlock();
		this.checkAutos();
	}
	
	public void wennTod() {
		SPIELFENSTER.btnSpielstart.setEnabled(true);
	}
}
