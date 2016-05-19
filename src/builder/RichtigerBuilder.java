package builder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class RichtigerBuilder extends JFrame {

	private static int FELDRAND = 32;

	BufferedImage GRASS;
	BufferedImage WASSER;
	BufferedImage BAUM;
	BufferedImage STRASSE;
	Vorschau vorschau;
	public Tile aktTile;
	Export ex;
	
	public RichtigerBuilder(int reihen, int spalten, JFrame tochter) throws IOException {

		GRASS = ImageIO.read(this.getClass().getResource("/backgrounds/grass.gif")).getSubimage(0, 0, FELDRAND, FELDRAND);
		WASSER = ImageIO.read(this.getClass().getResource("/backgrounds/wasser.gif")).getSubimage(0, 0, FELDRAND, FELDRAND);
		BAUM = ImageIO.read(this.getClass().getResource("/backgrounds/tree.png")).getSubimage(0, 0, FELDRAND, FELDRAND);
		STRASSE = ImageIO.read(this.getClass().getResource("/backgrounds/strasse.png")).getSubimage(0, 0, FELDRAND, FELDRAND);
		
		RichtigerBuilder thisFrame = this;
		int width = spalten*32+10;
		if(width < 475) width = 475;
		int height = reihen*32+140;
		this.setBounds(100, 100, width, height);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("5px"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("5px"),},
			new RowSpec[] {
				RowSpec.decode("4px"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("4px"),}));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "2, 2, fill, fill");
		panel.setBorder(new TitledBorder(null, "Einstellungen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("35px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		vorschau = new Vorschau();
		panel.add(vorschau, "2, 1, fill, fill");
		
		
		
		Tile grass = new Tile("/backgrounds/grass.gif", "01", "Grass");
		Tile wasser = new Tile("/backgrounds/wasser.gif", "02", "Wasser");
		Tile baum = new Tile("/backgrounds/tree.png", "03", "Baum");
		Tile strasse = new Tile("/backgrounds/strasse.png", "04", "Straße");
		
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(grass);
		tiles.add(wasser);
		tiles.add(baum);
		tiles.add(strasse);
		
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "4, 1, fill, fill");
		
		
		for(int i = 0; i < tiles.size(); i++) {
			final int f = i;
			JButton btnNewButton = new JButton(tiles.get(i).getName());
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					thisFrame.setFeld(tiles.get(f));
				}
			});
			panel_3.add(btnNewButton);
		}


		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, "2, 4, fill, fill");
		panel_1.setBorder(new TitledBorder(null, "Planung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		Feld feld = new Feld(reihen, spalten, this);
		panel_1.add(feld, "1, 1, fill, fill");
		
		//Schließen
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				thisFrame.setVisible(false);
				tochter.setVisible(true);
				ex.setVisible(false);
			}

		});

		this.setVisible(true);
		
		ex = new Export();
		this.requestFocus();
	}
	
	public void setFeld(Tile tile) {
		aktTile = tile;
		vorschau.setVorschau(tile.getB());
	}

}
