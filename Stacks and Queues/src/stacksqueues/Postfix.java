package stacksqueues;

import java.io.StringReader;
import java.lang.Math;
import java.util.Scanner;

/**
 * code for computing arithmetic expressions in post-fix notation.
 *  @version TODO
 */
public class Postfix {

    /** Pattern that matches on words */
    public static final String WORD = "[a-zA-Z]*\\b";

    /** Pattern that matches on arithmetic operators */
    public static final String OPERATOR = "[^\\w]";
    
    

    /** Main method to evaluate expression */
    public static void main(String[] args) {

    	// tell the user how to run the program
    	if (args.length==0) {
    		System.err.println("Usage: java Postfix <expr>");

    	} else {
    		System.out.println("input: " + args[0]);
    		Scanner input = new Scanner(new StringReader(args[0]));

			// Below is a complicated regular expression that will split the
			// input string at various boundaries.
			input.useDelimiter("(\\s+"                  // whitespace
					+"|(?<=[a-zA-Z])(?=[^a-zA-Z])"      // word->non-word
					+"|(?<=[^a-zA-Z])(?=[a-zA-Z])"      // non-word->word
					+"|(?<=[^0-9\\056])(?=[0-9\\056])"  // non-number->number
					+"|(?<=[0-9\\056])(?=[^0-9\\056])"  // number->non-number
					+"|(?<=[^\\w])(?=[^\\w]))");        // symbol->symbol

			double finalResult = postfix(input);
			System.out.println("result: " + finalResult);
    	}
    }

    public static double postfix(Scanner input) {
		// loop to run through all the tokens of the input
    	double result = 0.0;
    	// create the stack needed for computing the arithmetic expressions in post-fix notation
    	LinkedStack<Object> postFixStack = new LinkedStack<Object>();
		while (input.hasNext()) {
			// checks if the input is a number
			if (input.hasNextDouble()) {
				double doubleNumber = input.nextDouble();
				// push the number to the stack
				postFixStack.push(doubleNumber);				
			}
			// checks if the input is an operator
			else if (input.hasNext(OPERATOR)) {
				String myOperator = input.next(OPERATOR);
				// pop two numbers from the stack
				Object secondNumber = postFixStack.pop();
				Object firstNumber = postFixStack.pop();
				// check the operator's type and apply the correct arithmetic calculation
				if (myOperator.equals("+")) {
					result = (double) firstNumber + (double) secondNumber;
				}
				else if (myOperator.equals("-")) {
					result = (double) firstNumber - (double) secondNumber;
				}
				else if (myOperator.equals("/")) {
					result = (double) firstNumber / (double) secondNumber;
				}
				else if (myOperator.equals("*")) {
					result = (double) firstNumber * (double) secondNumber;
				}
				else if (myOperator.equals("^")) {
					result = Math.pow((double) firstNumber, (double) secondNumber);
				}
				else {
					throw new IllegalArgumentException("input invalid" + myOperator);
				}
				// push the result to the stack
				postFixStack.push((Object) result);
			}			
			else {
				System.out.println("Unknown: " + input.next());
			}
				
		}
		return result;
	}
}
