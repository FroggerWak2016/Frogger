package v2;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SpielfeldMoveObjects implements Runnable {
	final Lock lock = new ReentrantLock();
	private Spielfeld s;
	
	public SpielfeldMoveObjects(Spielfeld sSpielfeld) {
		this.s = sSpielfeld;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(s.alive) {
			s.lock.lock();
//			System.out.println("Zugriff auf Reihen: SMO: "+s.aktionsreihen);
			for(AktionsReihe tmp : s.aktionsreihen) {

				ArrayList<BewegendesObjekt> toDelete = new ArrayList<BewegendesObjekt>();
//				System.out.println("Zugriff auf Objekte: SMO: "+tmp.objekte);
				for(BewegendesObjekt b : tmp.objekte) {

					
					b.bewegeVor(tmp.iGeschwindigkeit, tmp.iRichtung);
					if(!b.inSpielfeld(s)) toDelete.add(b);
				}
				tmp.objekte.removeAll(toDelete);
//				System.out.println("Zugriff auf Objekte ENDE: SMO: "+tmp.objekte);
				
			}
//			System.out.println("Zugriff auf Reihen ENDE: SMO: "+s.aktionsreihen);
			
			s.lock.unlock();
			
			s.repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
