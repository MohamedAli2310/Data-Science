package propublica.datadesign;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReaderHeaderAware;

import prelab.StudentsData;

/**
 * testing the analysis * 
 * @version February 12, 2020
 */

public class Main {
	public static void main(String[] args) {
		try {
			// read data from CSV file
			CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader("compas-scores.csv"));
			ArrayList<String[]> myEntries = new ArrayList<String[]>(reader.readAll());
			
			//---------------------------- Lab 2 Test----------------------------------------
			DefendantsData test = new DefendantsData(myEntries);
			test.dataAnalysis(); 
			// comment the line above & uncomment the line below to get the data after applying changes to recidivism values
			// test.dataFurtherAnalysis();
			test.finalAnalysis();

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
//		// ------------------------------- Lab 1 Test -------------------------------------------------------------------------------
//
//		// testing defendant1's data
//		// an example of a defendant's data as an array of strings
//		String[] defendantData1 = { "Male", "African-American", "M", "Unlaw Use False Name/Identity", "9", "High", "0",
//				"", "" };
//		Defendants defendant1 = new Defendants(defendantData1);
//		System.out.println("----------------- Defendant 1 Test ---------------------");
//		System.out.println("Race: " + defendant1.getRace()); // testing getRace() method
//		System.out.println("Score: " + defendant1.getScore()); // testing getScore() method
//		System.out.println("Decile Score: " + defendant1.getDecileScore()); // testing getDecileScore() method
//		System.out.println("Sex: " + defendant1.getSex()); // testing getSex() method
//		System.out.println("Did he re-offend?: " + defendant1.isTwoYearRecid()); // testing isTwoYearRecid() method
//		System.out.println("Initial Crime: " + defendant1.getcChargeDesc()); // testing getcChargeDesc() method
//		System.out.println("Initial Crime Degree: " + defendant1.getCdegree()); // testing getCdegree() method
//		System.out.println("Crime after re-offending: " + defendant1.getrChargeDesc()); // testing getrChargeDesc()
//																						// method
//		System.out.println("Degree of Crime after re-offending: " + defendant1.getrChargeDegree()); // testing
//																									// getrChargeDegree()
//																									// method
//		defendant1.analysis(); // calling analysis method to classify the defendant
//		System.out.println("He is black of high risk and didn't re-offend: " + defendant1.getBlackHighNo());
//		System.out.println("He is black of low risk and re-offended: " + defendant1.getBlackLowYes());
//		System.out.println("He is white of high risk and didn't re-offend: " + defendant1.getWhiteHighNo());
//		System.out.println("He is white of low risk and re-offended: " + defendant1.getWhiteLowYes());
//
//		// testing defendant2's data
//		// an example of a defendant's data as an array of strings
//		String[] defendantData2 = { "Male", "Caucasian", "M", "Possession Of Methamphetamine", "2", "Low", "1",
//				"Operating W/O Valid License", "(M2)" };
//		Defendants defendant2 = new Defendants(defendantData2);
//		System.out.println("----------------- Defendant 2 Test ---------------------");
//		System.out.println("Race: " + defendant2.getRace()); // testing getRace() method
//		System.out.println("Score: " + defendant2.getScore()); // testing getScore() method
//		System.out.println("Decile Score: " + defendant2.getDecileScore()); // testing getDecileScore() method
//		System.out.println("Sex: " + defendant2.getSex()); // testing getSex() method
//		System.out.println("Did he re-offend?: " + defendant2.isTwoYearRecid()); // testing isTwoYearRecid() method
//		System.out.println("Initial Crime: " + defendant2.getcChargeDesc()); // testing getcChargeDesc() method
//		System.out.println("Initial Crime Degree: " + defendant2.getCdegree()); // testing getCdegree() method
//		System.out.println("Crime after re-offending: " + defendant2.getrChargeDesc()); // testing getrChargeDesc()
//																						// method
//		System.out.println("Degree of Crime after re-offending: " + defendant2.getrChargeDegree()); // testing
//																									// getrChargeDegree()
//																									// method
//		defendant2.analysis(); // calling analysis method to classify the defendant
//		System.out.println("He is black of high risk and didn't re-offend: " + defendant2.getBlackHighNo());
//		System.out.println("He is black of low risk and re-offended: " + defendant2.getBlackLowYes());
//		System.out.println("He is white of high risk and didn't re-offend: " + defendant2.getWhiteHighNo());
//		System.out.println("He is white of low risk and re-offended: " + defendant2.getWhiteLowYes());
//
//		// testing defendant3's data (sex is invalid)
//		// an example of a defendant's data as an array of strings
//		System.out.println("----------------- Defendant 3 (invalid) Test ---------------------");
//		String[] defendantData3 = { "Mane", "Caucasian", "M", "Possession Of Methamphetamine", "1", "Low", "1",
//				"Operating W/O Valid License", "(M2)" };
//		Defendants defendant3 = new Defendants(defendantData3);

	}
}
