package cs228hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import cs228hw2.AmusingPreciseNumber;
import cs228hw2.Deque228;

/**
 * Postfix calculator implemented with user-defined backing data structures.
 * 
 * @author Kylus Pettit-Ehler
 *
 */
public class calculator {
	/**
	 * Main method used to read a file.
	 * 
	 * @param args The file to be read.
	 */
	public static void main(String[] args) {
		String cmdline = args[0];
		File file = new File(cmdline);
		String input = "";
		boolean broke = false;
		Deque228<AmusingPreciseNumber> stack = new Deque228<>();
		Scanner s;
		try {
			s = new Scanner(file);
			while (s.hasNext()) {
				input += s.next() + " ";
			}
			s.close();
			if (input.isEmpty()) {
				System.out.println("Nothing was added to the stack.");
			} else {
				String inputCheck = "";
				Scanner traverse = new Scanner(input);
				System.out.println("Elements have been appended to the stack. Performing calculations...");
				while (traverse.hasNext()) {
					inputCheck = traverse.next();
					if (!validInput(inputCheck)) {
						System.out.println(
								"ERROR: AmusingPreciseNumbers can only contain a plus or minus sign at the beginning,\nand must only consist of digits. The AmusingPreciseNumber can also be a decimal.\nThe operators can be either plus, minus, abs, or neg.");
					}
					AmusingPreciseNumber temp = new AmusingPreciseNumber(inputCheck);
					if (temp.toString().equals("+")) {
						if (validState(stack, temp)) {
							addNumbers(stack);
						} else {
							System.out.println("Exited the stack due to error in postfix operations.");
							broke = true;
							break;
						}
					} else if (temp.toString().equals("-")) {
						if (validState(stack, temp)) {
							subtractNumbers(stack);
						} else {
							System.out.println("Exited the stack due to error in postfix operations.");
							broke = true;
							break;
						}
					} else if (temp.toString().equals("neg")) {
						if (validState(stack, temp)) {
							AmusingPreciseNumber numbOne = new AmusingPreciseNumber(stack.pollLast().toString());
							AmusingPreciseNumber result = new AmusingPreciseNumber();
							result = AmusingPreciseNumber.negate(numbOne);
							stack.offerLast(result);
						} else {
							System.out.println("Exited the stack due to error in postfix operations.");
							broke = true;
							break;
						}
					} else if (temp.toString().equals("abs")) {
						if (validState(stack, temp)) {
							AmusingPreciseNumber numbOne = new AmusingPreciseNumber(stack.pollLast().toString());
							AmusingPreciseNumber result = new AmusingPreciseNumber();
							result = AmusingPreciseNumber.abs(numbOne);
							stack.offerLast(result);
						} else {
							System.out.println("Exited the stack due to error in postfix operations.");
							broke = true;
							break;
						}
					} else {
						stack.offerLast(temp);
					}
				}
				traverse.close();
			}
			if (!broke) {
				System.out.println("Here is the result of postfix calculations: ");
				System.out.println(stack.peek().toString());
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Could not find the file specified.");
		}

	}

	/**
	 * Checks to see if the user has entered an invalid string into the postfix
	 * calculator.
	 * 
	 * @param toParse The string to check for validity.
	 * @return Whether the string is valid or not.
	 */
	private static boolean validInput(String toParse) {
		if (toParse.equals("abs") || toParse.equals("neg")) {
			return true;
		}
		for (int i = 0; i < toParse.length(); i++) {
			if (!(toParse.charAt(i) == '.') && !(toParse.charAt(i) == '+') && !(toParse.charAt(i) == '-')
					&& !(Character.isDigit(toParse.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Subtracts the two most recent numbers on the stack, given an exception was
	 * not thrown beforehand. using the first number (number lower) as the number to
	 * be operated on.
	 * 
	 * @param stack The stack to remove AmusingPreciseNumbers from and operate on.
	 */
	private static void subtractNumbers(Deque228<AmusingPreciseNumber> stack) {
		AmusingPreciseNumber numbOne = new AmusingPreciseNumber(stack.pollLast().toString());
		AmusingPreciseNumber numbTwo = new AmusingPreciseNumber(stack.pollLast().toString());
		AmusingPreciseNumber result = new AmusingPreciseNumber();
		if (!(numbOne == null) && !(numbTwo == null)) {
			result = AmusingPreciseNumber.subtract(numbTwo, numbOne);
		}
		stack.offerLast(result);
	}

	/**
	 * Adds the two most recent numbers on the stack, given than an exception was
	 * not thrown beforehand. The number below (immediately before) is the number
	 * that is operated on.
	 * 
	 * @param stack The stack to remove AmusingPreciseNumbers from and operate on.
	 */
	private static void addNumbers(Deque228<AmusingPreciseNumber> stack) {
		AmusingPreciseNumber numbOne = new AmusingPreciseNumber(stack.pollLast().toString());
		AmusingPreciseNumber numbTwo = new AmusingPreciseNumber(stack.pollLast().toString());
		AmusingPreciseNumber result = new AmusingPreciseNumber();
		if (!(numbOne == null) && !(numbTwo == null)) {
			result = AmusingPreciseNumber.add(numbTwo, numbOne);
		}
		stack.offerLast(result);
	}

	/**
	 * Checks to see if the desired operation can be performed without error.
	 * 
	 * @param stack The stack to observe for validity.
	 * @param temp  The desired operation on the next AmusingPreciseNumber(s).
	 * @return Whether the desired operation can be performed with the stack in its
	 *         current state.
	 */
	private static boolean validState(Deque228<AmusingPreciseNumber> stack, AmusingPreciseNumber temp) {
		if ((stack.size() < 2) && (temp.toString().equals("+") || temp.toString().equals("-"))) {
			System.out.println("ERROR: less than two numbers available to perform calculation.");
			return false;
		} else if ((stack.size() < 1) && (temp.toString().equals("abs") || temp.toString().equals("neg"))) {
			System.out.println("ERROR: less than one number available to perform calculation.");
			return false;
		}
		return true;
	}
}
