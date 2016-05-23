package v2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	/* Ließt den Inhalt aus der TXT-Level Datei und gibt diese als String zurück */
	public static String loadFileAsString(String path){
	  StringBuilder builder = new StringBuilder();

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
	
	
	/* Wandelt einen String in eine Integer-Zahl */
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
