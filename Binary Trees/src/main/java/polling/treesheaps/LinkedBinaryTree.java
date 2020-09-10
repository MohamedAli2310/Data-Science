package polling.treesheaps;

/**
 * LinkedBinaryTree Recursive Implementation
 * @version April 5, 2020
 * @param <E>
 */
public class LinkedBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {

	private E data; // root's data
	private LinkedBinaryTree<E> leftSubTree;
	private LinkedBinaryTree<E> rightSubTree;
	private int count; // to track tree's size

	/**
	 * Constructs LinkedBinaryTree
	 */
	public LinkedBinaryTree() {
		data = null;
		count++;
	}
	
	/**
	 * Constructs LinkedBinaryTree
	 * @param data
	 */
	public LinkedBinaryTree(E data) {
		this.data = data;
		leftSubTree = new LinkedBinaryTree<E>();
		rightSubTree = new LinkedBinaryTree<E>();
	}
	
	/**
	 * Insert an element to the tree
	 */
	public void insert(E element) {
		// base case for recursion
		if (this.data == null) {
			leftSubTree = new LinkedBinaryTree<E>();
			rightSubTree = new LinkedBinaryTree<E>();
			data = element;
		} else {
			LinkedBinaryTree<E> insertedTree = new LinkedBinaryTree<E>(element);
			// call helper function
			insertHelper(insertedTree, this);
		}
	}
	
	/**
	 * Helper method for elements' insertion
	 * @param insertedTree
	 * @param oldTree
	 */
	public void insertHelper(LinkedBinaryTree<E> insertedTree, LinkedBinaryTree<E> oldTree) {
		// checks if the two compared data is identical
		if (insertedTree.data.compareTo(oldTree.data) == 0) {
			oldTree.data = insertedTree.data;
		} 
		// checks if one element is larger than the other 
		else if (insertedTree.data.compareTo(oldTree.data) > 0) {
			if (oldTree.rightSubTree.data == null) {
				oldTree.rightSubTree = insertedTree;
				count++;
			} else {
				insertHelper(insertedTree, oldTree.rightSubTree);
			}
		}
		// checks if one element is smaller than the other
		else if (insertedTree.data.compareTo(oldTree.data) < 0) {
			if (oldTree.leftSubTree.data == null) {
				oldTree.leftSubTree = insertedTree;
				count++;
			} else {
				insertHelper(insertedTree, oldTree.leftSubTree);
			}

		}
	}
	
	/**
	 * Getter for root's data
	 */
	public E getRootElement() {
		return data;
	}
	
	/**
	 * Getter for tree's size
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Checks if the tree is empty
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	public int height() {
		if(isLeaf() || this.data == null) {
			return -1;
		}
		int leftHeight = leftSubTree.height();
		int rightHeight = rightSubTree.height();
		if(leftHeight > rightHeight) {
			return leftHeight + 1;
		}
		else {
			return rightHeight + 1;
		}
	}
	
	public boolean isLeaf() {
		return leftSubTree == null && rightSubTree == null;
	}
	/**
	 * In order tree traversal and printing
	 */
	public String toStringInOrder() {
		StringBuilder sb = new StringBuilder("In:\t");
		if (data != null) {
			inOrderHelper(this, sb);
		} else {
			return "";
		}
		return sb.toString().substring(0, sb.length() - 1);

	}
	
	/**
	 * Helper method for in order tree traversal
	 * @param inputTree
	 * @param sb
	 */
	public void inOrderHelper(LinkedBinaryTree<E> inputTree, StringBuilder sb) {

		if (inputTree.data != null) {
			inOrderHelper(inputTree.leftSubTree, sb); // left
			sb.append(inputTree.data.toString() + " "); // root
			inOrderHelper(inputTree.rightSubTree, sb); // right
		}

	}
	
	/**
	 * Pre-order tree traversal and printing
	 */
	public String toStringPreOrder() {
		StringBuilder sb = new StringBuilder("Pre:\t");
		if (data != null) {
			preOrderHelper(this, sb);
		} else {
			return "";
		}
		return sb.toString().substring(0, sb.length() - 1);

	}
	
	/**
	 * Helper method for pre-order tree traversal
	 * @param inputTree
	 * @param sb
	 */
	public void preOrderHelper(LinkedBinaryTree<E> inputTree, StringBuilder sb) {

		if (inputTree.data != null) {
			sb.append(inputTree.data.toString() + " "); // root
			preOrderHelper(inputTree.leftSubTree, sb); // left
			preOrderHelper(inputTree.rightSubTree, sb); // right
		}

	}
	
	/**
	 * Post-order tree traversal and printing
	 */
	public String toStringPostOrder() {
		StringBuilder sb = new StringBuilder("Post:\t");
		if (data != null) {
			postOrderHelper(this, sb);
		} else {
			return "";
		}
		return sb.toString().substring(0, sb.length() - 1);

	}
	
	/**
	 * Helper method for post-order tree traversal
	 * @param inputTree
	 * @param sb
	 */
	public void postOrderHelper(LinkedBinaryTree<E> inputTree, StringBuilder sb) {

		if (inputTree.data != null) {
			postOrderHelper(inputTree.leftSubTree, sb);
			postOrderHelper(inputTree.rightSubTree, sb);
			sb.append(inputTree.data.toString() + " ");
		}

	}
	
	@Override
	public String toString() {
		return "Tree: \n" + toStringPreOrder() + "\n" + toStringInOrder() + "\n" + toStringPostOrder();
	}
}
