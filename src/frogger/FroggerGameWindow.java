package frogger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FroggerGameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JFrame frame;
	public static int BAR_HEIGHT = 40;
	public static int TILE_SIZE = 25;
	public static int ROW_HEIGHT = TILE_SIZE;
	public static int COLUMN_WIDTH = TILE_SIZE;
	
	public static int CELL_HEIGHT = ROW_HEIGHT;
	public static int CELL_WIDTH = COLUMN_WIDTH;
	public static int DEFAULT_TOP_VALUE = 22;
	
	public static int playgroundHeight;
	public static int playgroundWidth;
	public static int windowHeight;
	
	private Level levelWahllevel;


	/**
	 * Create the application.
	 */
	public FroggerGameWindow(Level levelWahllevel) {
		this.levelWahllevel = levelWahllevel;
		
		playgroundHeight = TILE_SIZE * levelWahllevel.getHeight();
		playgroundWidth = TILE_SIZE * levelWahllevel.getWidth();
		windowHeight = playgroundHeight + BAR_HEIGHT + DEFAULT_TOP_VALUE;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setResizable(true);
		this.setSize(playgroundWidth, windowHeight);

	    this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("450px:grow"),},
			new RowSpec[] {
				RowSpec.decode("40px:grow"),
				RowSpec.decode("675px"),}));
		
		
		
		
		Spielfeld s = new Spielfeld(levelWahllevel, playgroundWidth, playgroundHeight);
		this.getContentPane().add(s, "1, 2, fill, fill");
		this.setFocusable(true);
		this.requestFocus();
		this.requestFocusInWindow();
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				s.move(e.getKeyCode());				
			}
			
		});
		
		
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel, "1, 1, fill, fill");
		
		JButton btnTes = new JButton("Tes");
		btnTes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.paintRectangle(1, 1, 100, 100, Color.BLUE);
			}
		});
		
		JButton btnOben = new JButton("Oben");
		btnOben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.geheHoch();
			}
		});
		
		panel.add(btnOben);
		panel.add(btnTes);
		
	}
	
	
	

}
