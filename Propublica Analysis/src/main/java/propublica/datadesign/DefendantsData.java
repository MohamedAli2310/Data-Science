package propublica.datadesign;

import java.util.ArrayList;

/**
 * @version February 19, 2020
 */

public class DefendantsData {
	private ArrayList<Defendants> defendantsData = new ArrayList<Defendants>(); // to hold all data from csv file
	private ArrayList<Defendants> defendantsDataCopy = new ArrayList<Defendants>(); // another copy to hold all data from csv file
	private int whiteHighNoSum = 0; // sum of white defendants of high risk who didn't re-offend
	private int whiteLowYesSum = 0; // sum of white defendants of low risk who re-offended
	private int blackHighNoSum = 0; // sum of black defendants of high risk who didn't re-offend
	private int blackLowYesSum = 0; // sum of black defendants of low risk who re-offended
	private int whiteNoSum = 0; // sum of all white defendants who didn't re-offend
	private int whiteYesSum = 0; // sum of all white defendants who re-offended
	private int blackNoSum = 0; // sum of all black defendants who didn't re-offend
	private int blackYesSum = 0; // sum of all black defendants who re-offended

	// DefendantsData constructor
	public DefendantsData(ArrayList<String[]> inputDefendantsData) {
		// loop through the ArrayList of all the defendants data
		for (String[] rowData : inputDefendantsData) {
			try {
				Defendants defendantData = new Defendants(rowData);
				defendantsData.add(defendantData); // add all defendants data to a new ArrayList of type Defendants
				defendantsDataCopy.add(defendantData); // add all defendants data to another copy that will be used for the further analysis conducted on r_charge descriptions.
			} catch (IllegalArgumentException e) {
				e.printStackTrace();

			}
		}

	}

	/**
	 * @return the whiteHighNoSum
	 */
	public int getWhiteHighNoSum() {
		return whiteHighNoSum;
	}

	/**
	 * @return the whiteLowYesSum
	 */
	public int getWhiteLowYesSum() {
		return whiteLowYesSum;
	}

	/**
	 * @return the blackHighNoSum
	 */
	public int getBlackHighNoSum() {
		return blackHighNoSum;
	}

	/**
	 * @return the blackLowYesSum
	 */
	public int getBlackLowYesSum() {
		return blackLowYesSum;
	}

	/**
	 * @return the whiteNoSum
	 */
	public int getWhiteNoSum() {
		return whiteNoSum;
	}

	/**
	 * @return the whiteYesSum
	 */
	public int getWhiteYesSum() {
		return whiteYesSum;
	}

	/**
	 * @return the blackNoSum
	 */
	public int getBlackNoSum() {
		return blackNoSum;
	}

	/**
	 * @return the blackYesSum
	 */
	public int getBlackYesSum() {
		return blackYesSum;
	}

	/**
	 * calculate the numbers of defendants needed to construct the table
	 */
	public void dataAnalysis() {
		// loop through each defendant's data (each row)
		for (Defendants defendant : defendantsData) {
			defendant.analysis();
			whiteHighNoSum += defendant.getWhiteHighNo(); // calculate number of white defendants of high risk who
															// didn't re-offend
			whiteLowYesSum += defendant.getWhiteLowYes(); // calculate the number of white defendants of low risk who
															// re-offended
			blackHighNoSum += defendant.getBlackHighNo(); // calculate the number of black defendants of high risk who
															// didn't re-offend
			blackLowYesSum += defendant.getBlackLowYes(); // calculate the number of black defendants of low risk who
															// re-offended
			whiteNoSum += defendant.getWhiteNo(); // calculate the number of all white defendants who didn't re-offend
			whiteYesSum += defendant.getWhiteYes(); // calculate the number of all white defendants who re-offended
			blackNoSum += defendant.getBlackNo(); // calculate the number of all black defendants who didn't re-offend
			blackYesSum += defendant.getBlackYes(); // calculate the number of all black defendants who re-offended
		}
		
		
	}
	/**
	 * calculate the number of defendants after applying changes to the recidivism value
	 */
	public void dataFurtherAnalysis() {
		// loop through each defendant's data copy (each row)
		for (Defendants defendant1 : defendantsDataCopy) {
			defendant1.furtherAnalysis();
			defendant1.analysis();
			whiteHighNoSum += defendant1.getWhiteHighNo(); // calculate number of white defendants of high risk who
															// didn't re-offend
			whiteLowYesSum += defendant1.getWhiteLowYes(); // calculate the number of white defendants of low risk who
															// re-offended
			blackHighNoSum += defendant1.getBlackHighNo(); // calculate the number of black defendants of high risk who
															// didn't re-offend
			blackLowYesSum += defendant1.getBlackLowYes(); // calculate the number of black defendants of low risk who
															// re-offended
			whiteNoSum += defendant1.getWhiteNo(); // calculate the number of all white defendants who didn't re-offend
			whiteYesSum += defendant1.getWhiteYes(); // calculate the number of all white defendants who re-offended
			blackNoSum += defendant1.getBlackNo(); // calculate the number of all black defendants who didn't re-offend
			blackYesSum += defendant1.getBlackYes(); // calculate the number of all black defendants who re-offended
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (Defendants rowData : defendantsData) {
			sb.append(rowData.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * print the data analyzed
	 */
	public void printAnalysisData() {

		System.out.println(whiteHighNoSum);
		System.out.println(whiteLowYesSum);
		System.out.println(blackHighNoSum);
		System.out.println(blackLowYesSum);
		System.out.println(whiteNoSum);
		System.out.println(whiteYesSum);
		System.out.println(blackNoSum);
		System.out.println(blackYesSum);

	}

	/**
	 * calculate the four ratios needed to construct the table
	 */

	public void finalAnalysis() {
		double HighNoReoffendWhite = (double) (whiteHighNoSum) / (double) (whiteNoSum);
		double HighNoReoffendBlack = (double) (blackHighNoSum) / (double) (blackNoSum);
		double LowRecidivateWhite = (double) (whiteLowYesSum) / (double) (whiteYesSum);
		double LowRecidivateBlack = (double) (blackLowYesSum) / (double) (blackYesSum);
		// create an object of PropublicaDataTable that takes the four calculated ratios
		// as its attributes
		PropublicaDataTable dataTable = new PropublicaDataTable(HighNoReoffendWhite, HighNoReoffendBlack,
				LowRecidivateWhite, LowRecidivateBlack);
		System.out.println(dataTable.toString());
	}
	


}
