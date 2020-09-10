package polling.treesheaps;

import java.util.ArrayList;

/**
 * Implementing Max Priority Queues using array-based heaps
 * @param <E>
 * @version April 14, 2020
 */
public class ArrayHeap<E extends Comparable<E>> implements PriorityQueue<E> {
	
	ArrayList<E> heap;
	
	/**
	 * Constructor
	 */
	public ArrayHeap(){
		heap = new ArrayList<E>();
	}
	
	/**
	 * Constructor
	 * @param array
	 */
	public ArrayHeap(ArrayList<E> array) {
		heap = array;
		// phase 1 of in-place sorting: unsorted --> heap		
		for(int i = 0; i < heap.size(); i++) {
			bubbleUp(i);
		}
	}
	
	/**
	 * Insert an element into the priority queue.  
	 * Keep in heap order
	 * @param element the element to insert
	 */
	public void insert(E element) {
		heap.add(element); // put new element at the end
		bubbleUp(heap.size() - 1);	// re-heapify the heap to maintain heap order
	}

	/**
	 * Return the element with the maximum value, without removing it from the queue.
	 * @return the element with the maximum, or null if queue empty.
	 */
	public E max() {
		if(isEmpty()) {
			return null;
		}
		else {
			return heap.get(0);			
		}
	}
	
	/**
	 * Phase 2 of in-place heap sorting: heap --> sorted array
	 */
	public void sort() {
		for(int end = heap.size() - 1; end > 0; end--) {
			swap(0, end); // swap the first element in the heap with the last unsorted element
			bubbleDownHelper(end); // bubble-down to put the maximum element on the top
		}
	}
	
	/**
	 * Return the element with the maximum value, and remove it from the queue.
	 * @return the element with the maximum value or null if queue empty.
	 */
	public E removeMax() {
		if(heap.size() <= 0) {
			return null;
		}
		else {
			E max = heap.get(0); // store max element
			heap.set(0, heap.get(heap.size() - 1)); // move last element to index 0
			heap.remove(heap.size() - 1);
			bubbleDown(); // re-heapify to maintain the heap order
			return max;			
		}
	}
	
	/**
	 * Return the number of elements in the heap
	 * @return size of the heap
	 */
	public int size() {
		return heap.size();
	}
	
	
	/**
	 * Check if the queue is empty
	 * @return true if the queue is empty, false if not empty.
	 */
	public boolean isEmpty() {
		return heap.size() == 0;
	}
	
	/**
	 * Swap the two elements in the locations i and j
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		E temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	/**
	 * @param i
	 * @return the index of the parent of node i.
	 */
	private int parent(int i) {
		return (i-1)/2;
	}
	
	/**
	 * @param i
	 * @return the index of the left child of node i.
	 */
	private int leftChild(int i) {
		return 2*i+1;
	}
	
	/**
	 * @param i
	 * @return the index of the right child of node i.
	 */
	private int rightChild(int i) {
		return 2*i+2;
	}
	
	/**
	 * Moves the element at index i to restore the heap order
	 * @param i
	 */
	private void bubbleUp(int i) {
		// loop until the heap property is verified (parents have the maximum element)
		while(i > 0 && heap.get(i).compareTo(heap.get(parent(i))) > 0) {
			swap(i , parent(i));
			i = parent(i); // continue from the parent's location
		}
	}
	
	/**
	 * Moves the element at the root lower to the whole heap to restore the heap order
	 * @param i
	 */
	private void bubbleDown() {
		bubbleDownHelper(heap.size());
	}
	
	/**
	 * Moves the element at the root lower till reaching index end to restore the heap order
	 * @param end
	 */
	private void bubbleDownHelper(int end) {
		int i = 0; // root's index
		// continue as long as the root has children
		while(leftChild(i) < end) {
			int largestChildIndex = leftChild(i); // although right child may be larger
			if(rightChild(i) < end && heap.get(leftChild(i)).compareTo(heap.get(rightChild(i))) < 0) {
				largestChildIndex = rightChild(i); // right child is larger
			}
			// checks if the the largest child has a greater value than the parent
			if(heap.get(i).compareTo(heap.get(largestChildIndex)) < 0) {
				swap(i, largestChildIndex);
			}
			else {
				break; // heap order is reached
			}
			i = largestChildIndex; // continue from the position of the child
		}
	}
	
	/**
	 * print the heap properly in a good format
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		int start = 0;
		int levelSize = 1;
		while(start < heap.size()) {
			for(int i = start; i < start + levelSize && i < heap.size(); i++) {
				sb.append(heap.get(i) + " "); // add the elements of the heap to the string
			}
			sb.append("\n"); // add a new line after each level
			start += levelSize;
			levelSize *= 2; // because each level contains a power of 2 elements
		}	    
		int last = sb.lastIndexOf("\n");
		// remove the additional empty line
		if (last >= 0) {
			sb.delete(last, sb.length()); 
			}  
	    return sb.toString();
	}


	
}
