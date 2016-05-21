package v2;

public class SpielfeldAddObject implements Runnable {
	
	Spielfeld s;
	
	public SpielfeldAddObject(Spielfeld sSpielfeld) {
		this.s = sSpielfeld;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(s.alive) {
			s.addAuto();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
