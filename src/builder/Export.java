package builder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Export extends JFrame {

	/**
	 * Create the application.
	 */
	public JTextPane textPane;
	private JTextField textField;
	
	public Export() {

		JFrame thisFrame = this;
		this.setTitle("Export");
		this.setBounds(50, 50, 407, 403);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		
		//Schließen
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				thisFrame.setVisible(false);
			}
	
		});

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblLevelnummer = new JLabel("Levelnummer:");
		panel.add(lblLevelnummer, "2, 2, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		this.getContentPane().add(textPane, BorderLayout.CENTER);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().isEmpty()) {
					try {
					 int numLevel = Integer.parseInt(textField.getText());
		    		 File file = new File("res/level/level_"+numLevel+".txt"); 
				      if (file.createNewFile()){
				    	  
				    	  FileWriter fw = new FileWriter(file);
				    	  fw.write(textPane.getText());
				    	  fw.close();
					     
				      } else {
				    	  FileWriter fw = new FileWriter(file);
				    	  fw.write(textPane.getText());
				    	  fw.close();
				      }
				      
				      
			    	} catch (IOException e2) {
				      e2.printStackTrace();
			    	}
				}
			}
		});
		getContentPane().add(btnSpeichern, BorderLayout.SOUTH);
		
	}

}
