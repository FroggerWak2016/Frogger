package builder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Feld extends JPanel {

	/**
	 * Create the panel.
	 */
	 
	private ArrayList<ArrayList<Tile>> structure = new ArrayList<ArrayList<Tile>>();
	RichtigerBuilder b;
	
	public Feld(int rows, int cols, RichtigerBuilder b) {
		this.b = b;
		this.setBounds(0, 0, cols*32, rows*32);	
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(b.aktTile != null)
					if(e.getX() < cols*32 && e.getY() <  rows*32 )
				paintFeld((int)e.getX()/32, (int)e.getY()/32, b.aktTile);
				
			}

		});
		
		Tile nullTile = new Tile("/backgrounds/null.png", "00", "null");
		for(int y = 0; y < rows; y++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for(int x = 0; x < cols; x++) {
				row.add(nullTile);
			}			
			structure.add(row);
		}
	}
	
	public void paintFeld(int x, int y, Tile t) {
		structure.get(y).set(x, t);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		for(int y = 0; y < structure.size(); y++) {
			for(int x = 0; x < structure.get(y).size(); x++) {
				BufferedImage bu = structure.get(y).get(x).getB();
					g2.drawImage(structure.get(y).get(x).b, x*32, y*32, 32, 32, null);
			}			
		}
		
		reExport();
		
		
		
	}
	
	public void reExport() {
		String s = "";
		for(int y = 0; y < structure.size(); y++) {
			String sRow ="";
			for(int x = 0; x < structure.get(y).size(); x++) {
				sRow += structure.get(y).get(x).getBezeichnung();
				if(x < structure.get(y).size()-1) sRow += " ";
			}
			s += sRow;
			if(y < structure.size()-1) s+="\n";
		}
			
		this.b.ex.textPane.setText(s);
	}
}
