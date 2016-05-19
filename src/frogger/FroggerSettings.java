package frogger;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class FroggerSettings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Level levelWahllevel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FroggerSettings frame = new FroggerSettings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Level l1 = new Level(1,"Level 1", "/Level1.txt");
				levelWahllevel = l1;
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FroggerSettings() {

		/*
		 * Main frame
		 */
		setTitle("Frogger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 600);


		/*
		 * Main panel
		 */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("1dlu"),
				RowSpec.decode("1dlu"),}));
		

		/*
		 * Panel "Level"
		 */
		JPanel pFroggerSettings = new JPanel();
		contentPane.add(pFroggerSettings, "2, 2, fill, fill");

		pFroggerSettings.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		//Level Panel
		JPanel pSettingsLevel = new JPanel();
		pFroggerSettings.add(pSettingsLevel, "2, 2, fill, fill");
		pSettingsLevel.setBorder(new TitledBorder(null, "Level", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//Tiere Panel
		JPanel pSettingsAnimal = new JPanel();
		pFroggerSettings.add(pSettingsAnimal, "2, 4, fill, fill");
		pSettingsAnimal.setBorder(new TitledBorder(null, "Tiere", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//Spielen Panel
		JPanel pSettingsStart = new JPanel();
		pFroggerSettings.add(pSettingsStart, "2,6,fill,fill");
		pSettingsStart.setBorder(new TitledBorder(null, "Starten", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FormLayout fl_pSettingsStart = new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,});
		pSettingsStart.setLayout(fl_pSettingsStart);
		
		JButton btnSpielStarten = new JButton("Spiel starten");
		pSettingsStart.add(btnSpielStarten, "2, 2, default, fill");
		
		btnSpielStarten.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FroggerGameWindow f = new FroggerGameWindow(levelWahllevel);
				f.setVisible(true);
				setVisible(false);				
			}
			
		});
		
		// Spielstände Panel
		JPanel pSettingsServerLoad = new JPanel();
		pFroggerSettings.add(pSettingsServerLoad, "2, 8, fill, fill");
		pSettingsServerLoad.setBorder(new TitledBorder(null, "Spielstände", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FormLayout fl_pSettingsServerLoad = new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,});
		pSettingsServerLoad.setLayout(fl_pSettingsServerLoad);
		
		JButton btnSpeichern = new JButton("Speichern");
		pSettingsServerLoad.add(btnSpeichern, "2, 2, default, fill");
		
		JButton btnLaden = new JButton("Laden");
		pSettingsServerLoad.add(btnLaden, "2, 4, default, fill");
		
		JButton btnServerstandLaden = new JButton("Serverstand Laden");
		pSettingsServerLoad.add(btnServerstandLaden, "2, 6, default, fill");
		
	}

}
