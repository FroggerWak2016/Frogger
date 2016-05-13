package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.TileSet;

public class Spielfeld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BufferedImage canvas;
	
	
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	BufferedImage frosch;
	private Level levelWahllevel;
	Frosch f;
	
	/**
	 * Create the panel.
	 */
	public Spielfeld(Level levelWahllevel, int width, int height) {
		try {
			images.add(ImageIO.read(TileSet.class.getResource("/gras.png")).getSubimage(0, 0, 25, 25));
			images.add(ImageIO.read(TileSet.class.getResource("/wasser.png")).getSubimage(0, 0, 25, 25));
			frosch = ImageIO.read(TileSet.class.getResource("/frosch.png")).getSubimage(0,0,25,25);
		} catch (IOException e) {
			e.printStackTrace();
		    return;
		}			
		this.levelWahllevel = levelWahllevel;
		
		System.out.println(levelWahllevel.getHeight()-1);
		f = new Frosch(10, levelWahllevel.getHeight()-1);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
	
		//super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        ArrayList<ArrayList<Integer>> levelStructure = levelWahllevel.getStructure();
        
        for(int iRow = 0; iRow < levelWahllevel.getDimension().getHeight(); iRow++) {
        	for(int iCol = 0; iCol < levelWahllevel.getDimension().getWidth(); iCol++) {
        		g2.drawImage(images.get(levelStructure.get(iRow).get(iCol)), iCol*25, iRow*25, 25, 25, null);
        	}
        }
        
        g2.drawImage(frosch, f.getCol()*FroggerGameWindow.TILE_SIZE, f.getRow()*FroggerGameWindow.TILE_SIZE, 25, 25, null);
 
		
    }
	
	public void paintRectangle(int x, int y, int width, int height, Color c) {
		int color = c.getRGB();
		for(int i = x; i< x+width; i++ ) {
			for(int j = y; j< y+height; j++ ) {
				canvas.setRGB(i, j, color);
			}
		}
		repaint();
	}
	
	public void geheHoch() {
		f.setRow(f.getRow()-1);
		repaint();
	}
	
	public void move(int direction) {
		f.goInDirection(direction);
		repaint();
	}




	
	
	

}
