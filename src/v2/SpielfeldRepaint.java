package v2;

public class SpielfeldRepaint implements Runnable {

	Spielfeld s;
	
	public SpielfeldRepaint(Spielfeld sSpielfeld) {
		this.s = sSpielfeld;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(s.bLebendig) {
			s.repaint();
		
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
