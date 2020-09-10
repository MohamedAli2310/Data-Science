package babynames.linkedlist;

/**
 * Class to holds the data (name, rank, number) for node 
 * @version March 1, 2020
 */

public class Name {

	private String name;
	private int rank;
	private int number;
	private int position;

	/**
	 * constructing the node that stores the name, rank, and number
	 * 
	 * @param inputName
	 * @param inputRank
	 * @param inputNumber
	 */
	public Name(String inputName, int inputRank, int inputNumber) {
		name = inputName;
		rank = inputRank;
		number = inputNumber;
	}

	/**
	 * constructing the node that takes the data from the csv file and read them
	 * 
	 * @param inputName
	 * @param inputRank
	 * @param inputNumber
	 */
	public Name(String inputName, String inputRank, String inputNumber) {
		name = inputName;
		rank = Integer.parseInt(inputRank); // convert from string to integers
		number = Integer.parseInt(inputNumber); // convert from string to integers
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return name + ": " + rank + ", " + number + ", ";
	}

	/**
	 * compare to for comparing strings to objects	 * 
	 * @param arg
	 * @return
	 */
	public int compareTo(Name arg) {
		return name.compareTo(arg.getName());
	}
}
