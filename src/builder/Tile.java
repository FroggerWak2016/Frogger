package builder;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	BufferedImage b;
	String bezeichnung;
	String name;
	
	
	
	public Tile(String pfad, String bezeichnung, String name) {
		super();
		if(pfad != null) {
			try {
				this.b = ImageIO.read(this.getClass().getResource(pfad)).getSubimage(0, 0, 32, 32);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else this.b = null;
		this.name = name;
		this.bezeichnung = bezeichnung;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BufferedImage getB() {
		return b;
	}
	public void setB(BufferedImage b) {
		this.b = b;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	
}
