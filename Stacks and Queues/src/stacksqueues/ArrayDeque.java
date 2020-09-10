package stacksqueues;

import java.util.EmptyStackException;

/**
 * Implementing double-ended queue
 * @version March 28, 2020
 * @param <E>
 */

public class ArrayDeque<E> implements Deque<E> {
	
	public static final int CAPACITY = 1000;
	
	private int f; // front element
	private int size; // size of queue
	private E[] data; // array of data stored in the queue
	
	/**
	 * constructs the queue
	 */
	public ArrayDeque() {
		this(CAPACITY);
	}
	
	/**
	 * constructs the queue
	 * @param capacity of array
	 */
	@SuppressWarnings("unchecked")
	public ArrayDeque(int capacity) {
		f = 0;
		size = 0;
		data = (E[]) new Object[capacity];
	}
	
	/**
	 * add elements to the start of the queue 
	 */
	public void addFirst(E element) throws FullQueueException {
		// checks if the array is full
		if(size == data.length) {
			throw new FullQueueException();
		}
		// changes the front pointer
		f = (f - 1 + data.length) % data.length;
		// add the element to the array
		data[f] = element;	
		// increment the size by 1
		size += 1;
	}
	
	/**
	 * add elements to the end of the queue
	 */
	public void addLast(E element) throws FullQueueException {
		// checks if the array is full
		if(size == data.length) {
			throw new FullQueueException();
		}
		// create end variable to point the the last element
		int end = (f + size) % data.length;
		// add the element to the array
		data[end] = element;
		// increment the size
		size += 1;
	}
	
	/**
	 * remove and return the first element from the queue
	 */
	public E removeFirst() throws EmptyQueueException {
		// checks if the array is empty
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		// store the first element in the variable result
		E result = data[f];
		// remove the element by setting it to null
		data[f] = null;
		// change the front pointer
		f = (f + 1) % data.length;
		// decrease the size
		size -= 1;
		// return the first element which is stored in result
		return result;
	}
	
	/**
	 * remove and return the last element from the queue
	 */
	public E removeLast() throws EmptyQueueException {
		// checks if the array is empty
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		// create end variable to store the index of the last element
		int end = (f - 1 + size) % data.length;
		// store the last element in the variable result
		E result = data[end];
		// remove last element
		data[end] = null;
		// decrease the size
		size -= 1;
		// return the last element which is stored in result
		return result;
	}
	
	/**
	 * return the first element in the queue
	 */
	public E first() throws EmptyQueueException {
		// checks if the array is empty
		if(isEmpty()) {
			throw new EmptyStackException(); 
		}
		return data[f];
	}
	
	/**
	 * return the last element in the queue
	 */
	public E last() throws EmptyQueueException {
		// checks if the array is empty
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		// create end variable to point to the last element
		int end = (f - 1 + size) % data.length;
		return data[end];
	}
	
	/**
	 * return the size of the queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * checks if the array is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * print the queue in an understandable format
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		// counter used to stop the loop when it's equal to the no. of elements in the array
		int counter = 0;
		for(int i = f; counter < data.length && data[i] != null; i = (i + 1) % data.length) {
				sb.append(data[i].toString() + ", ");
				counter += 1;
			}
		// using substring to remove the comma and space at the end and then adding the bracket to close the queue 
		String print = sb.substring(0,sb.length()-2) + ")";
		return print.toString();
}	
}
