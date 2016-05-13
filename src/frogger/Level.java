package frogger;

import java.util.ArrayList;

public class Level {

	private int id;
	private String name;
	private String path;
	
	public Level(int id, String name, String path) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public ArrayList<ArrayList<Integer>> getStructure() {
		int iRows = this.getDimension().getHeight();
		int iCols = this.getDimension().getWidth();
		int[] iaFields = getFields();
		ArrayList<ArrayList<Integer>> alReturn = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < iRows; i++) {
			ArrayList<Integer> alRowFields = new ArrayList<Integer>();
			for(int j = 0; j < iCols; j++) {
				alRowFields.add(iaFields[i*iCols+j]);
			}
			alReturn.add(alRowFields);
		}
		return alReturn;		
	}
	
	public Dimension getDimension() {
		String sLevelstructure = Utils.loadFileAsString(this.path);
		String[] saFields = sLevelstructure.split("\\s");		
		Dimension dimReturnDimention = new Dimension(Utils.toInt(saFields[0]), Utils.toInt(saFields[1]));
		return dimReturnDimention;
	}
	
	private int[] getFields() {
		String sLevelstructure = Utils.loadFileAsString(this.path);
		int[] iaReturnFields = new int[getDimension().getHeight()*getDimension().getWidth()];
		String[] saFields = sLevelstructure.split("\\s");
		for(int i = 2; i < saFields.length; i++) {
			iaReturnFields[i-2] = Utils.toInt(saFields[i]); 
		}
		return iaReturnFields;
	}
	
	
	public int getHeight() {
		return getDimension().getHeight();
	}
	
	public int getWidth() {
		return getDimension().getWidth();
	}
	
	
}
