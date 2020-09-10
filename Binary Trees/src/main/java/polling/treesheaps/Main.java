package polling.treesheaps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReaderHeaderAware;

/**
 * Reading the polling data and testing the tree
 * @version April 5, 2020
 */
public class Main {
	
    public static void main(String[] args) {
    	ArrayList<String> inputFiles = new ArrayList<String>(); // hold the name of csv files input from command-line arguments
    	LinkedBinaryTree<Candidate> candidatesData = new LinkedBinaryTree<Candidate>(); // hold the candidates' data  

		for(int i = 0; i < args.length; i++) {
			inputFiles.add(args[i]);
		}
		
		// traverse through each csv file
		for (String file : inputFiles) {

			try {
				// read data from CSV file
				CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(file));
				ArrayList<String[]> myEntries = new ArrayList<String[]>(reader.readAll());

				for (String[] row : myEntries) {	
					// insert each candidate's data
					candidatesData.insert(new Candidate(row));					
				}
				reader.close();

			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// print the tree in the correct format 	
		System.out.print(candidatesData.toString());
		
    }
} 
