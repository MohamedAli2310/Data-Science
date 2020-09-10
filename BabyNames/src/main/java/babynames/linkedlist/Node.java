package babynames.linkedlist;

/**
 * A class for nodes to help create the linked list class
 * 
 * @version March 1, 2020
 */

public class Node {

	private Name name; // Node's data
	private Node next; // reference to the next node
	private Node prev; // reference to the previous node

	/**
	 * Constructor creates the Node and sets its next to null.
	 * 
	 * @param initData: the data this node will contain
	 */
	public Node(Name name, Node prev, Node next) {
		this.name = name;
		this.prev = prev;
		this.next = next;

	}

	/**
	 * returns the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Setter for the next Node
	 * 
	 * @param next: the Node that comes after this one
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * Getter for the next Node
	 * 
	 * @return the Node after this one
	 */
	public Node next() {
		return this.next;
	}

	/**
	 * Setter for the previous Node
	 * 
	 * @param prev: the Node that comes before this one
	 */
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	/**
	 * Getter for the previous Node
	 * 
	 * @return the Node before this one
	 */
	public Node prev() {
		return this.prev;
	}

	@Override
	public String toString() {
		return name.toString();
	}

}
