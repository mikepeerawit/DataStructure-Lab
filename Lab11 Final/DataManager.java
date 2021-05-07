import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataManager {
	private String fileLocation="";
	String[] dates;
	String[] states;
	String[] countries;
	int[][] data;
	
	public DataManager(String fileLocation) {
		this.setFileLocation(fileLocation);
		try {
			int n=0;
			Scanner in = new Scanner(new File(fileLocation));
			dates=in.nextLine().strip().substring(39).split(",");
			while(in.hasNext()){
				// System.out.println(in.nextLine());
				in.nextLine();
				n++;
			}
			in.close();
			states=new String[n];
			countries=new String[n];
			data=new int[n][dates.length];
			in = new Scanner(new File(fileLocation));
			in.nextLine().strip();
			int line=0;
			while(in.hasNext()){
				// System.out.println(in.nextLine());
				String[] tokens=in.nextLine().strip().split(",");
				states[line]=tokens[0];
				countries[line]=tokens[1];
				for(int i=4;i<tokens.length;i++) {
					data[line][i-4]=Integer.parseInt(tokens[i]);
				}
				line++;
			}
			in.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("File not found.");
			fileLocation="";
		}
		
	}
	private int getIndex(String[] arrayIn,String key) {
		for(int i=0;i<arrayIn.length;i++) {
			if(arrayIn[i].equalsIgnoreCase(key)) {
				return i;
			}
		}
		return -1;
	}
	public int getData(String state,String country,String date) {
		if(state.equals("")) {
			int sum=0;
			for(int i=0;i<this.states.length;i++) {
				if(countries[i].equalsIgnoreCase(country)) {
					int ind=getIndex(dates,date);
					if(ind != -1) {
						sum += data[i][ind];
					}
				}
			}
			return sum;
		}
		int stateIndex=getIndex(states,state);
		int dateIndex=getIndex(dates,date);
		return data[stateIndex][dateIndex];
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}
