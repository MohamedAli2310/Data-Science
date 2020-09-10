package stacksqueues;

/**
 * A class for nodes to help create the linked list class
 * @version March 28, 2020
 */
public class Node<E> {
	
	private E data; // data stored in the node
	private Node<E> next; // reference to the next node
	
	/**
	 * constructs the node
	 * @param initData
	 */
	public Node(E initData) {
		data = initData;		
	}
	
	/**
	 * @return the data stored in the node
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * @return the following node
	 */
	public Node<E> next() {
		return next;
	}
	
	/**
	 * setter for the next node
	 * @param next
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
