package builder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Vorschau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	BufferedImage b;
	
	public Vorschau() {
		this.setBounds(0, 0, 32, 32);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(b, 0, 0, 32, 32, null);
	}
	
	public void setVorschau(BufferedImage b) {
		this.b = b;
		repaint();
	}

}
