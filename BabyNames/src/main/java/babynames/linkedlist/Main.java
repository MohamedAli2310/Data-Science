package babynames.linkedlist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

/**
 * Reading the files, calculating the percentage, testing
 * @version March 1, 2020
 */
public class Main {
	public static void main(String[] args) {

		ArrayList<String> inputMaleNames = new ArrayList<String>(); // hold male names input from command-line arguments
		ArrayList<String> inputFemaleNames = new ArrayList<String>(); // hold female names input from command-line arguments
		ArrayList<String> inputFiles = new ArrayList<String>(); // hold the name of csv files input from command-line arguments
		LinkedList allYearsMaleData = new LinkedList(); // store all the male data from all the input csv files 
		LinkedList allYearsFemaleData = new LinkedList(); // store all the female data from all the input csv files

		for (int i = 0; i < args.length; i++) {
			// check if the input is a male name
			if (args[i].equals("-m")) {
				inputMaleNames.add(args[i + 1]);
				i++;
			// check if the input is a female name
			} else if (args[i].equals("-f")) {
				inputFemaleNames.add(args[i + 1]);
				i++;
			// check if the input is a csv file
			} else {
				inputFiles.add(args[i]);
			}
		}

		int allFilesTotalMales = 0; // hold total number of males from all files
		int allFilesTotalFemales = 0; // hold total number of females from all files
		
		// traverse through each input csv file
		for (String file : inputFiles) {

			int totalMales = 0;  // store the number of males  of each file
			int totalFemales = 0; // store the number of females of each file
			int totalNumberMale = 0; // store the total number of males 
			int totalNumberFemale = 0; // store the total number of females

			try {
				// read data from CSV file
				CSVReader reader = new CSVReader(new FileReader(file));
				ArrayList<String[]> myEntries = new ArrayList<String[]>(reader.readAll());

				LinkedList femaleNames = new LinkedList(); // to store the female names 
				LinkedList maleNames = new LinkedList(); // to store the male names

				for (String[] row : myEntries) {
					totalMales += Integer.parseInt(row[2]);
					totalFemales += Integer.parseInt(row[4]);
					Name maleName = new Name(row[1], row[0], row[2]);
					Name femaleName = new Name(row[3], row[0], row[4]);
					maleNames.insertAlpha(maleName);
					femaleNames.insertAlpha(femaleName);
					
					// checks if the object is already in the allYearsMaleData linked list
					if (allYearsMaleData.search(maleName.getName()) == null) {
						allYearsMaleData.insertAlpha(maleName);
					} else {
						// if the object is already in the list, add to the total number
						allYearsMaleData.search(maleName.getName()).setNumber(
								allYearsMaleData.search(maleName.getName()).getNumber() + maleName.getNumber());
					}
					// checks if the object is already in the allYearsFemaleData linked list
					if (allYearsFemaleData.search(femaleName.getName()) == null) {
						allYearsFemaleData.insertAlpha(femaleName);
					} else {
						// if the object is already in the list, add to the total number
						allYearsFemaleData.search(femaleName.getName()).setNumber(
								allYearsFemaleData.search(femaleName.getName()).getNumber() + femaleName.getNumber());
					}
				}
				
				// traverse through the input male names
				for (String maleName : inputMaleNames) {
					Name male = maleNames.search(maleName); // search for the node with the same input name (and return the node's data)
					System.out.println(file.substring(5, 9)); // to get the year from the csv format
					System.out.println(male.toString() + " "
							+ String.format("%.6f", (double) male.getNumber() / (double) totalMales) + "\n"); // to get the percentage
					totalNumberMale += male.getNumber();
					allFilesTotalMales += totalMales;

				}
				
				// traverse through the input female names
				for (String femaleName : inputFemaleNames) {
					Name female = femaleNames.search(femaleName); // search for the node with the same input name (and return the node's data)
					System.out.println(file.substring(5, 9)); // to get the year from the csv format
					System.out.println(female.toString() + " "
							+ String.format("%.6f", (double) female.getNumber() / (double) totalFemales) + "\n"); // to get the percentage
					totalNumberFemale += female.getNumber();
					allFilesTotalFemales += totalFemales;

				}

				reader.close();

			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// these for loops are for dealing with total data
		// traverse through the input female names
		for (String femaleName : inputFemaleNames) {
			Name female = allYearsFemaleData.search(femaleName); // search for the node with the same input name (and return the node's data)
			System.out.println("Total");
			System.out.println(femaleName + ": " + allYearsFemaleData.getTotalRank(femaleName) + ", "
					+ female.getNumber() + ", "
					+ String.format("%.6f", (double) female.getNumber() / (double) allFilesTotalFemales) + "\n"); // to get the total percentage

			System.out.println("Position of " + female.getName() + " in the Linked List: " + female.getPosition()); // print the position in the linked list
		}
		
		// traverse through the input male names
		for (String maleName : inputMaleNames) {
			Name male = allYearsMaleData.search(maleName); // search for the node with the same input name (and return the node's data)
			System.out.println("Total");
			System.out.println(maleName + ": " + allYearsMaleData.getTotalRank(maleName) + ", " + male.getNumber()
					+ ", " + String.format("%.6f", (double) male.getNumber() / (double) allFilesTotalMales) + "\n"); // to get the total percentage
			 
			System.out.println("Position of " + male.getName() + " in the Linked List: " + male.getPosition()); // to print the position in the linked list
		}

	}
}
