package builder;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Builder extends JFrame {

	private JFrame frame;
	private JTextField reihen;
	private JTextField spalten;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Builder window = new Builder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Builder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 203);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20dlu"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("20dlu"),},
			new RowSpec[] {
				RowSpec.decode("13dlu"),
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("10dlu"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("10dlu"),
				RowSpec.decode("default:grow"),}));
		
		JLabel lblNewLabel = new JLabel("Levelbuilder");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		frame.getContentPane().add(lblNewLabel, "2, 2, 3, 1, center, center");
		
		JLabel lblReihen = new JLabel("Reihen:");
		frame.getContentPane().add(lblReihen, "2, 4, left, default");
		
		reihen = new JTextField();
		frame.getContentPane().add(reihen, "4, 4, 2, 1, fill, default");
		reihen.setColumns(10);
		
		JLabel lblSpalten = new JLabel("Spalten:");
		frame.getContentPane().add(lblSpalten, "2, 6, left, default");
		
		spalten = new JTextField();
		frame.getContentPane().add(spalten, "4, 6, 2, 1, fill, default");
		spalten.setColumns(10);
		
		JButton btnNewButton = new JButton("Bauen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int iReihen = 1;
				int iSpalten = 1;
				try {
					iReihen = Integer.parseInt(reihen.getText());
					iSpalten = Integer.parseInt(spalten.getText());

				} catch(Exception e1) {
					System.out.println("Fehler beim parsen!");
				}
				
				
				try {
					RichtigerBuilder r = new RichtigerBuilder(iReihen, iSpalten, frame);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				
			}
		});
		frame.getContentPane().add(btnNewButton, "4, 8, fill, default");
	}
	
	public void setVisible(boolean visible) {
		this.frame.setVisible(visible);
	}

}
