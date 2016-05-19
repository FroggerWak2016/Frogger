package v2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import builder.Builder;

public class Spielfenster extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Spielfenster window = new Spielfenster();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Spielfenster() {
		
		Spielfenster SPIELFENSTER = this;
		
		this.setResizable(false);
		this.setBounds(100, 100, 693, 630);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("480px"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("590px"),
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		
		// Linke Spalte -> Einstellungen
		JPanel pEinstellungen = new JPanel(); 
		this.getContentPane().add(pEinstellungen, "2, 2, fill, fill");
		pEinstellungen.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("40px"),
				RowSpec.decode("40px"),}));
		
		JPanel pTier = new JPanel();
		pTier.setBorder(new TitledBorder(null, "Tier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pEinstellungen.add(pTier, "1, 1, fill, fill");
		
		JPanel pLevel = new JPanel();
		pLevel.setBorder(new TitledBorder(null, "Level", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pEinstellungen.add(pLevel, "1, 3, fill, fill");
		
		JPanel pServer = new JPanel();
		pServer.setBorder(new TitledBorder(null, "Server", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pEinstellungen.add(pServer, "1, 5, fill, fill");	
		
		// Rechte Spalte -> Spiel
		JPanel pSpiel = new JPanel();
		this.getContentPane().add(pSpiel, "4, 2, fill, fill");
		pSpiel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("7px"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("540px"),}));
		
		JPanel pSpielbar = new JPanel();
		pSpielbar.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pSpiel.add(pSpielbar, "1, 2, fill, fill");
		pSpielbar.setLayout(new BorderLayout(0, 0));
		
		
		Spielfeld pSpielplan = new Spielfeld();
		pSpiel.add(pSpielplan, "1, 3, fill, fill");
		
		JButton btnSpielstart = new JButton("Spielen");
		btnSpielstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pSpielplan.baueLevel(1);
				SPIELFENSTER.setFocus();
			}
		});
		pEinstellungen.add(btnSpielstart, "1, 7, fill, fill");
		
		JButton btnBuilder = new JButton("Builder");
		btnBuilder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Builder b = new Builder();
				b.setVisible(true);
			}
		});
		pEinstellungen.add(btnBuilder, "1, 8, fill, fill");
		
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() >= 37 && e.getKeyCode() <= 40) // Nur bei Pfeiltasten
					pSpielplan.move(e.getKeyCode());				
			}
			
		});
	}

	private void setFocus() {
		this.setFocusable(true);
		this.requestFocus();
		this.requestFocusInWindow();
	}
}
