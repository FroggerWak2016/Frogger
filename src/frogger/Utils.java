package frogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Utils {

	public static Dimension getDimension(String path) {
		String sLevelstructure = loadFileAsString(path);
		String[] fields = sLevelstructure.split("\\s");		
		Dimension dimReturnDimention = new Dimension(toInt(fields[0]), toInt(fields[1]));
		return dimReturnDimention;		
	}
	
	public static String loadFileAsString(String path){
		  StringBuilder builder = new StringBuilder();

		  //Get file from resources folder
		  FileReader file = null;
		  try {
		    file = new FileReader(Utils.class.getClass().getResource(path).getFile());
		  } catch (FileNotFoundException e1) {
		    e1.printStackTrace();
		  }
		  if(file != null) {
		    try {
		      BufferedReader br = new BufferedReader(file);
		      String line;
		      while((line = br.readLine()) != null) {
		        builder.append(line + "\n");
		      }
		      br.close();
		    } catch(IOException e) {
		      e.printStackTrace();
		    }
		  }
		  return builder.toString();
		}
	
	public static void main(String[] args) {
		
		System.out.println(getDimension("/Level1.txt").getWidth()+"");
		
	}
	
	public static int toInt(String s) {
		
		int iReturn = 0;
		try {
			iReturn = Integer.parseInt(s);
		} catch(Exception e) {
			System.out.println("Parsing fehler");
		}
		return iReturn;
	}
	
	
}
