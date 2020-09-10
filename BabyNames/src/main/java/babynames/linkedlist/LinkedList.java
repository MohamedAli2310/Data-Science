package babynames.linkedlist;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class for creating the doubly linked list with sentinels (dummy nodes)
 * 
 * @version March 1, 2020
 */

public class LinkedList {

	private Node header; // header sentinel
	private Node trailer; // trailer sentinel
	private int size = 0; // no. of elements in the list

	/**
	 * constructs a new empty list
	 */
	public LinkedList() {
		header = new Node(null, null, null); // create header
		trailer = new Node(null, header, null); // trailer is preceded by header
		header.setNext(trailer); // header is followed by trailer
	}

	/**
	 * Returns the number of elements in the linked list
	 */
	public int size() {
		return size;
	}

	/**
	 * Tests whether the linked list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the first element of the list
	 */
	public Name first() {
		if (isEmpty()) {
			return null;
		}
		return header.next().getName(); // first element follows header
	}

	/**
	 * Returns the last element of the list
	 */
	public Name last() {
		if (isEmpty()) {
			return null;
		}
		return trailer.prev().getName(); // last element precedes trailer
	}

	/**
	 * Adds String initData to the linked list in between the given nodes
	 */
	public void addBetween(Name inputName, Node predecessor, Node successor) {
		// create and link a new node
		Node newest = new Node(inputName, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size += 1; // increase the size of linked list
	}

	/**
	 * Add data to the front of the list
	 */
	public void addFirst(Name inputName) {
		addBetween(inputName, header, header.next()); // place after header
	}

	/**
	 * Add data to the end of the list
	 */
	public void addLast(Name inputName) {
		addBetween(inputName, trailer.prev(), trailer); // place after trailer
	}

	/**
	 * Insert objects of type Name to the linked list according to the alphabetical
	 * order of the names
	 */
	public void insertAlpha(Name inputName) {
		// checks if linked list is empty
		if (size == 0) {
			addFirst(inputName);
		}
		// checks if the name is alphabetically before the name in the first node
		else if (inputName.compareTo(header.next().getName()) < 0) {
			addFirst(inputName); // place after header
		}
		// checks if the name is alphabetically after the name in the last node
		else if (inputName.compareTo(trailer.prev().getName()) > 0) {
			addLast(inputName); // place before trailer
		} else {
			Node currNode = header.next(); // create a new node that resembles the first node of the linked list
			// traverse through the nodes of the linked list and check the alphabetical
			// order
			while (inputName.compareTo(currNode.getName()) > 0) {
				currNode = currNode.next();
			}
			addBetween(inputName, currNode.prev(), currNode); // place the data in the appropriate place
		}
	}

	/**
	 * Search through the linked list for a node that has the name of the input name
	 * @param name
	 * @return node of type Name
	 */
	public Name search(String name) {
		int counter = 0; // to know the position
		Node currNode = header.next();
		// traverse through the linked list
		while (currNode != null && currNode.next() != null) {
			if (name.equals(currNode.getName().getName())) {
				currNode.getName().setPosition(counter); // set the position of the name in the linked list
				return currNode.getName();
			}
			currNode = currNode.next();
			counter++;
		}
		return null;
	}

	/**
	 * Add total number of babies of each name to an array list
	 * @return array list containing the total numbers of babies
	 */
	public ArrayList<Integer> getTotalNumbers() {
		Node currNode = header.next();
		ArrayList<Integer> totalNumbers = new ArrayList<Integer>(); // to hold the total number of babies of each name
		// traverse through the linked list
		while (currNode != null && currNode.next() != null) {
			totalNumbers.add(currNode.getName().getNumber()); // adding the numbers of babies of each name to the array
																// list
			currNode = currNode.next();
		}
		return totalNumbers;
	}

	/**
	 * To get the total rank of the input name
	 * @param name
	 * @return total rank
	 */
	public int getTotalRank(String name) {
		int totalNumber = search(name).getNumber(); // store the total number of babies of the input name
		ArrayList<Integer> sortedArrayList = getTotalNumbers(); // an array list that holds the total number of babies
																// of each name
		Collections.sort(sortedArrayList); // sort the array list (in ascending order)
		Collections.reverse(sortedArrayList); // sort the array list (in descending order)
		// traverse through the sorted array list
		for (int i = 0; i < sortedArrayList.size(); i++) {
			// check if the total number in the array list is equal to the total number of
			// the input name
			if (sortedArrayList.get(i) == totalNumber) {
				return i + 1; // return the total rank (i+1) since the index start at 0 while the rankings
								// start at 1
			}

		}
		return -1;

	}

	/**
	 * for string formatting
	 * @return sb.toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Node currNode = header.next();
		while (currNode != null && currNode.next() != null) {
			sb.append(currNode.toString());
			sb.append("\n");
			currNode = currNode.next();
		}
		return sb.toString();
	}

}
