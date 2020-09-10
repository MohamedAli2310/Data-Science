package polling.treesheaps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReaderHeaderAware;

/**
 * Testing methods
 * Reading and Sorting candidates data
 * @version April 14, 2020
 */
public class Main {

	// for testing
	public static void testing() {
		// 1st: testing insert integers
		Integer[] arr1 = { -2, 3, 9, -7, 1, 2, 6, -3 };
		ArrayHeap<Integer> numberHeap = new ArrayHeap<Integer>();
		for (int number : arr1) {
			numberHeap.insert(number);
		}
		System.out.println("Testing Integer Insertion: ");
		System.out.println(numberHeap + "\n");
		
		// 2nd: testing removeMax() method on Integers
		numberHeap.removeMax();
		System.out.println("Testing removeMax() on Integers: ");
		System.out.println(numberHeap + "\n");

		// 3rd: testing insert characters
		ArrayHeap<Character> letterHeap = new ArrayHeap<Character>();
		letterHeap.insert('A');
		letterHeap.insert('C');
		letterHeap.insert('G');
		letterHeap.insert('B');
		letterHeap.insert('D');
		letterHeap.insert('G'); // inserting again, will still both copies
		letterHeap.insert('F');
		letterHeap.insert('E');
		letterHeap.insert('H');
		letterHeap.insert('I');
		System.out.println("Testing Characters Insertion: ");
		System.out.println("size:" + letterHeap.size());
		System.out.println(letterHeap + "\n");
		
		// 4th: testing removeMax() method on Characters
		letterHeap.removeMax();
		System.out.println("Testing removeMax() on Characters: ");
		System.out.println(letterHeap + "\n");

		// 5th: testing 1st phase of in-place sorting
		// create an ArrayList using static method "asList"
		Integer[] arr = { 3, 5, 1, 4, 7, 2, 6};
		ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));
		// make a new heap out of the array
		ArrayHeap<Integer> heap = new ArrayHeap<Integer>(array);
		System.out.println("Testing 1st Phase of In-place Sorting: ");
		System.out.println(heap + "\n");

		// 6th: testing full in-place sorting
		heap.sort();
		System.out.println(heap + "\n");
		System.out.println("Testing In-place Sorting");
		System.out.println(array + "\n");
	}

	public static void main(String[] args) {
		testing();

		ArrayHeap<Candidate> candidatesData = new ArrayHeap<Candidate>();
		try {
			// read data from CSV file
			CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(args[0]));
			ArrayList<String[]> myEntries = new ArrayList<String[]>(reader.readAll());

			for (String[] row : myEntries) {
				// insert each candidate's data
				candidatesData.insert(new Candidate(row));
			}

			reader.close();

			int candidatesNumber = candidatesData.size(); // store number of candidates
			// traverse through candidates
			for (int i = 0; i < candidatesNumber; i++) {
				System.out.println(candidatesData.removeMax());
			}

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
