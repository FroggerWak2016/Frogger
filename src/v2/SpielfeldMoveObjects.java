package v2;

public class SpielfeldMoveObjects implements Runnable {

	private Spielfeld s;
	
	public SpielfeldMoveObjects(Spielfeld sSpielfeld) {
		this.s = sSpielfeld;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(s.alive) {
			s.moveAutos();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
