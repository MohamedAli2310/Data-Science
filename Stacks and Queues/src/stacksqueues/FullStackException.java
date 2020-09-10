package stacksqueues;

@SuppressWarnings("serial")
public class FullStackException extends RuntimeException {
	public FullStackException() {
		super("Stack is full.");
	}
}