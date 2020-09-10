package stacksqueues;

import java.util.EmptyStackException;

/**
 * Implementing a linkedstack
 * @version March 28, 2020
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {

	private Node<E> top; // top node
	private int size = 0;
	
	/**
	 * constructs the linkedstack
	 */
	public LinkedStack() {
		top = null;
	}
	
	/**
	 * push an element to the top of the stack
	 */
	public void push(E element) {			
		// create a new node to carry the element
		Node<E> newNode = new Node<E>(element);
		// let the new node point to the top node
		newNode.setNext(top);
		// set the new node to be the top node
		top = newNode;
		// increment the size by 1
		size += 1;
	}

	/**
	 * remove and return the top element in the stack
	 */
	public E pop() throws EmptyStackException {
		// checks if the stack is empty
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			// store the data of the top node in a new variable
			E target = top.getData();
			// change the top node
			top = top.next();
			// decrease the size
			size -= 1;
			// return the data of the top node which is stored in target
			return target;
		}
	}

	/**
	 * return the top element in the stack
	 */
	public E peek() throws EmptyStackException {
		// checks if the stack is empty
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			return top.getData();
		}
	}

	/**
	 * return the size of the stack
	 */
	public int size() {
		return size;	
	}

	/**
	 * checks if the stack is empty
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}		
		return false;
	}
	
	/**
	 * arrange the stack in an understandable format
	 */
	public String toString() {
		// create a new linkedstack
		LinkedStack<E> temp = new LinkedStack<E>();
		StringBuilder sb = new StringBuilder("(");
		Node<E> currNode = top;
		
		while(currNode != null) {
			// store the elements into the new stack in a reverse order
			temp.push(currNode.getData());
			currNode = currNode.next();
		}
		while(temp.size() > 0) {
			// check for the last element
			if (temp.size() == 1) {
				E elem = temp.peek();
				// add the last element and the closing bracket to the string 
				sb.append(elem.toString() + (")"));
				temp.pop();	
			}
			else {
				// add each element followed by a comma and a space to the string 
				E elem = temp.peek();
				sb.append(elem.toString() + (", "));
				temp.pop();	
			}
		}	
		return sb.toString();
	}
	
	
}
