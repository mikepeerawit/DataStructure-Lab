
public class TestSpeed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataManager dm = new DataManager("/Users/sukree/Downloads/covid19.csv"); // Load everything from the file. 
		// All data should be ready at this point.
		long startTime = System.nanoTime();
		for(int i=0;i<100;i++) { // i will be changed to 100000 later.
			dm.getData("", "Afghanistan", "3/20/20"); // Countries and States are just an example.
			dm.getData("","Algeria","3/21/20");       // They will be randomly choose while the program is being evaluated.
		}
		System.out.println((System.nanoTime()-startTime)/(1));
	}

}
