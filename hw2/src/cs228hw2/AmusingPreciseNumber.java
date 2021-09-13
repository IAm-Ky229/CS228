package cs228hw2;

import java.io.IOException;
import java.io.Reader;
import java.util.ListIterator;
import java.util.Scanner;

import cs228hw2.AmusingLinkedList;

/**
 * Class used to store a precise number and perform simple calculations with it.
 * 
 * @author Kylus Pettit-Ehler
 *
 */
public class AmusingPreciseNumber {
	/**
	 * User-defined backing data structure to store the given numbers in the form of
	 * strings.
	 */
	private AmusingLinkedList<String> preciseNumb;
	/**
	 * Needed in the special case where the preciseNumbers are decimals.
	 */
	private int scale;

	/**
	 * The default constructor for an AmusingPreciseNumber.
	 */
	public AmusingPreciseNumber() {
		preciseNumb = new AmusingLinkedList<>();
		scale = 0;
	}

	/**
	 * Constructor for an AmusingPreciseNumber object taking an integer argument.
	 * 
	 * @param numb the number to be parsed as an AmusingPreciseNumber.
	 */
	public AmusingPreciseNumber(int numb) {
		boolean decimal = false;
		preciseNumb = new AmusingLinkedList<>();
		String toParse = Integer.toString(numb);
		if (!validInput(toParse)) {
			throw new RuntimeException();
		} else {
			for (int i = 0; i < toParse.length(); i++) {
				String sub = toParse.substring(i, i + 1);
				if (decimal) {
					scale++;
				}
				if (sub.equals(".")) {
					decimal = true;
				}
				preciseNumb.add(sub);
			}
		}
	}

	/**
	 * Checks to see if the user has requested the construction of a valid
	 * AmusingPreciseNumber, assuming they don't put a + or - in the middle of the
	 * number.
	 * 
	 * @param toParse The string to scan for validity.
	 * @return Whether the string is a valid AmusingPreciseNumber or not.
	 */
	private boolean validInput(String toParse) {
		if (toParse.equals("abs") || toParse.equals("neg")) {
			return true;
		}
		for (int i = 0; i < toParse.length(); i++) {
			if (!(toParse.charAt(i) == '.') && !(toParse.charAt(i) == '+') && !(toParse.charAt(i) == '-')
					&& !(Character.isDigit(toParse.charAt(i)))) {
				return false;
			}
			if (((toParse.charAt(i) == '+') || (toParse.charAt(i) == '-')) && (i != 0)) {
				return false;
			}
		}
		if (toParse.lastIndexOf('.') != toParse.indexOf(".")) {
			return false;
		}
		if (toParse.equals("") || toParse.equals("+.") || toParse.equals("-.")) {
			return false;
		}
		return true;
	}

	/**
	 * Constructor for an AmusingPreciseNumber taking a String argument.
	 * 
	 * @param numb The string to be parsed to an AmusingPreciseNumber.
	 */
	public AmusingPreciseNumber(String numb) {
		preciseNumb = new AmusingLinkedList<>();
		boolean decimal = false;
		if (!validInput(numb)) {
			throw new RuntimeException();
		} else {
			for (int i = 0; i < numb.length(); i++) {
				String sub = numb.substring(i, i + 1);
				if (decimal) {
					scale++;
				}
				if (sub.equals(".")) {
					decimal = true;
				}
				preciseNumb.add(sub);
			}
		}
	}

	/**
	 * Constructor for an AmusingPreciseNumber that uses a Reader argument. The
	 * number should be able to continue indefinitely.
	 * 
	 * @param r The reader object that provides the AmusingPreciseNumber with
	 *          arguments.
	 * @throws IOException Signals that the reader has encountered an error with the
	 *                     given input / output.
	 */
	public AmusingPreciseNumber(Reader r) throws IOException {
		StringBuilder builder = new StringBuilder();
		int charsRead = -1;
		char[] chars = new char[100];
		do {
			charsRead = r.read(chars, 0, chars.length);
			if (charsRead > 0)
				builder.append(chars, 0, charsRead);
		} while (charsRead > 0);
		String stringRead = builder.toString();
		preciseNumb = new AmusingLinkedList<>();
		boolean decimal = false;
		Scanner go = new Scanner(stringRead);
		String temp = go.next();
		if (!validInput(temp)) {
			go.close();
			throw new RuntimeException();
		} else {
			for (int i = 0; i < temp.length(); i++) {
				String sub = temp.substring(i, i + 1);
				if (decimal) {
					scale++;
				}
				if (sub.equals(".")) {
					decimal = true;
				}
				preciseNumb.add(sub);
			}
		}
		go.close();
	}

	/**
	 * Constructor for an AmusingPreciseNumber that creates a deep copy of the given
	 * AmusingPreciseNumber object.
	 * 
	 * @param numb The given AmusingPreciseNumber to deep copy.
	 */
	public AmusingPreciseNumber(AmusingPreciseNumber numb) {
		preciseNumb = new AmusingLinkedList<>();
		for (int i = 0; i < numb.preciseNumb.size(); i++) {
			preciseNumb.add(numb.preciseNumb.get(i));
		}
		scale = new Integer(numb.scale);
	}

	/**
	 * Add this number to the AmusingPreciseNumber in question.
	 * 
	 * @param numb The AmusingPreciseNumber to be added to the referenced
	 *             AmusingPreciseNumber.
	 */
	public void add(AmusingPreciseNumber numb) {
		int carry = 0;
		boolean executed = false;
		boolean equal = contentsAreEqual(numb);
		executed = checkSignsAdditive(numb, equal);
		addDecimals(numb);
		scaleNumbers(numb);
		ListIterator<String> iterOne = preciseNumb.listIterator(preciseNumb.size());
		ListIterator<String> iterTwo = numb.preciseNumb.listIterator(numb.preciseNumb.size());
		if (!executed) {
			for (int i = 0; i < Math.min(preciseNumb.size(), numb.preciseNumb.size()); i++) {
				String addOneStr = iterOne.previous();
				String addTwoStr = iterTwo.previous();
				if (!(addOneStr.equals(".") || addTwoStr.equals("."))) {
					int addOne = Integer.valueOf(addOneStr);
					int addTwo = Integer.valueOf(addTwoStr);
					int result = ((addOne + addTwo + carry) % 10);
					if (addOne + addTwo + carry >= 10) {
						carry = (addOne + addTwo + carry) / 10;
					} else {
						carry = 0;
					}
					iterOne.next();
					iterOne.set(Integer.toString(result));
					iterOne.previous();
				}
			}
			if (carry != 0) {
				preciseNumb.add(0, "1");
			}
		}
		removeLeading(equal);
	}

	/**
	 * Helper method to determine if padding zeroes need to be added to the end or
	 * beginning of either AmusingPreciseNumber. Also checks to see if a "+" was
	 * appended to the beginning of either AmusingPreciseNumber.
	 * 
	 * @param numb The other AmusingPreciseNumber.
	 */
	private void scaleNumbers(AmusingPreciseNumber numb) {
		if (preciseNumb.get(0).equals("+")) {
			preciseNumb.remove(0);
		}
		if (numb.preciseNumb.get(0).equals("+")) {
			numb.preciseNumb.remove(0);
		}
		if (scale > numb.scale) {
			for (int i = numb.scale; i < scale; i++) {
				numb.preciseNumb.add("0");
				numb.scale++;
			}
		} else {
			for (int i = scale; i < numb.scale; i++) {
				preciseNumb.add("0");
				scale++;
			}
		}
		if (preciseNumb.size() > numb.preciseNumb.size()) {
			if (numb.preciseNumb.get(0).equals("-")) {
				for (int i = numb.preciseNumb.size(); i < preciseNumb.size(); i++) {
					numb.preciseNumb.add(1, "0");
				}
			} else {
				for (int i = numb.preciseNumb.size(); i < preciseNumb.size(); i++) {
					numb.preciseNumb.add(0, "0");
				}
			}
		} else {
			if (preciseNumb.get(0).equals("-")) {
				for (int i = preciseNumb.size(); i < numb.preciseNumb.size(); i++) {
					preciseNumb.add(1, "0");
				}
			} else {
				for (int i = preciseNumb.size(); i < numb.preciseNumb.size(); i++) {
					preciseNumb.add(0, "0");
				}
			}
		}
	}

	/**
	 * Checks the signs of both AmusingPreicseNumbers to determine if there is a
	 * special case.
	 * 
	 * @param numb  The other AmusingPreciseNumber.
	 * @param equal Whether both numbers are completely equal.
	 * @return If a special case regarding signs was detected.
	 */
	private boolean checkSignsAdditive(AmusingPreciseNumber numb, boolean equal) {
		Boolean negate = false;
		if (preciseNumb.get(0).equals("+")) {
			preciseNumb.remove(0);
		}
		if (numb.preciseNumb.get(0).equals("+")) {
			numb.preciseNumb.remove(0);
		}
		if (preciseNumb.get(0).equals("-") && !(numb.preciseNumb.get(0).equals("-"))) {
			if (numbIsBigger(numb)) {
				negate = true;
			}
			negate();
			subtract(numb);
			if (negate) {
				negate();
			}
			return true;
		} else if (numb.preciseNumb.get(0).equals("-") && !(preciseNumb.get(0).equals("-"))) {
			numb.negate();
			subtract(numb);
			return true;
		} else if ((numb.preciseNumb.get(0).equals("-") && (preciseNumb.get(0).equals("-")))) {
			negate();
			numb.negate();
			add(numb);
			negate();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Subtract this number from the AmusingPreciseNumber in question.
	 * 
	 * @param numb The AmusingPreciseNumber to be subtracted from the referenced
	 *             AmusingPreciseNumber.
	 */
	public void subtract(AmusingPreciseNumber numb) {
		int carry = 0;
		int result = 0;
		addDecimals(numb);
		scaleNumbers(numb);
		boolean equal = contentsAreEqual(numb);
		boolean executed = checkSignsSubtractive(numb, equal);
		ListIterator<String> iterOne = preciseNumb.listIterator(preciseNumb.size());
		ListIterator<String> iterTwo = numb.preciseNumb.listIterator(numb.preciseNumb.size());
		if (!executed) {
			boolean complement = numbIsBigger(numb);
			for (int i = 0; i < preciseNumb.size(); i++) {
				String subtractOneStr = iterOne.previous();
				String subtractTwoStr = iterTwo.previous();
				if (!(subtractOneStr.equals(".") || subtractTwoStr.equals("."))) {
					int subtractOne = Integer.valueOf(subtractOneStr);
					int subtractTwo = Integer.valueOf(subtractTwoStr);
					if (subtractOne - subtractTwo + carry < 0) {
						result = subtractOne - subtractTwo + carry + 10;
						carry = -1;
					} else {
						result = subtractOne - subtractTwo + carry;
						carry = 0;
					}
					iterOne.next();
					iterOne.set(Integer.toString(result));
					iterOne.previous();
				}
			}
			if (complement) {
				takeComplement(numb, equal);
				negate();
			}
		}
		removeLeading(equal);
	}

	/**
	 * This method is used by add and subtract to make sure the final
	 * AmusingPreciseNumber answer is of the correct format.
	 * 
	 * @param equal Whether the two numbers are identical.
	 */
	private void removeLeading(boolean equal) {
		boolean zero = true;
		for (int i = 0; i < preciseNumb.size(); i++) {
			if (!(preciseNumb.get(i).equals("0")) && !(preciseNumb.get(i).equals("."))
					&& !(preciseNumb.get(i).equals("-"))) {
				zero = false;
			}
		}
		if (zero) {
			preciseNumb.clear();
			preciseNumb.add("0");
		} else {
			if (preciseNumb.get(0).equals("-") && (!equal)) {
				if (preciseNumb.get(1).equals("0")) {
					while (preciseNumb.get(1).equals("0")) {
						preciseNumb.remove(1);
					}
				}
			} else {
				if (preciseNumb.get(0).equals("0") && (!equal)) {
					while (preciseNumb.get(0).equals("0")) {
						preciseNumb.remove(0);
					}
				}
			}
		}
	}

	/**
	 * This method is utilized by toString() to format the output in a more
	 * "correct" fashion.
	 * 
	 * @param toReturn The unformatted string to process.
	 * @return The formatted string.
	 */
	private String removeLeadingString(String toReturn) {
		boolean zero = true;
		for (int i = 0; i < toReturn.length(); i++) {
			if (!(toReturn.charAt(i) == '0') && !(toReturn.charAt(i) == '.') && !(toReturn.charAt(i) == '-')) {
				zero = false;
			}
		}
		if (toReturn.charAt(0) == '-' && toReturn.length() != 1) {
			while (toReturn.charAt(1) == '0' && !toReturn.equals("-0")) {
				toReturn = toReturn.substring(0, 1) + toReturn.substring(2, toReturn.length());
			}
		} else if (toReturn.charAt(0) == '0') {
			if (zero) {
				toReturn = "0";
			} else {
				while (toReturn.charAt(0) == '0') {
					toReturn = toReturn.substring(1, toReturn.length());
				}
			}
		}
		while (toReturn.charAt(toReturn.length() - 1) == '0' && !zero && toReturn.indexOf(".") != -1) {
			toReturn = toReturn.substring(0, toReturn.length() - 1);
		}
		if (toReturn.charAt(toReturn.length() - 1) == '.') {
			toReturn = toReturn.substring(0, toReturn.length() - 1);
		}
		return toReturn;
	}

	/**
	 * Helper method for subtract to determine what actions should be taken for the
	 * signs of the AmusingPreciseNumbers.
	 * 
	 * @param numb  AmusingPreciseNumber given as an argument.
	 * @param equal Whether the 2 AmusingPreciseNumbers are completely equal.
	 * @return Whether there was a special case regarding the signs of the
	 *         AmusingPreciseNumbers.
	 */
	private boolean checkSignsSubtractive(AmusingPreciseNumber numb, boolean equal) {
		if (preciseNumb.get(0).equals("+")) {
			preciseNumb.remove(0);
		}
		if (numb.preciseNumb.get(0).equals("+")) {
			numb.preciseNumb.remove(0);
		}
		if (preciseNumb.get(0).equals("-") && !(numb.preciseNumb.get(0).equals("-"))) {
			negate();
			add(numb);
			negate();
			return true;
		} else if ((numb.preciseNumb.get(0).equals("-")) && !(preciseNumb.get(0).equals("-"))) {
			numb.negate();
			add(numb);
			return true;
		} else if ((numb.preciseNumb.get(0).equals("-") && (preciseNumb.get(0).equals("-")))) {
			negate();
			numb.negate();
			subtract(numb);
			negate();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if these two AmusingPreicseNumbers are completely equal.
	 * 
	 * @param numb The other AmusingPreciseNumber.
	 * @return Whether the 2 numbers are equal.
	 */
	private boolean contentsAreEqual(AmusingPreciseNumber numb) {
		for (int i = 0; i < preciseNumb.size(); i++) {
			if (!(preciseNumb.get(i).equals(numb.preciseNumb.get(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Helper method used by subtract if a special case is detected where the
	 * calculated answer is actually the complement to the correct answer.
	 * 
	 * @param numb  The other AmusingPreciseNumber.
	 * @param equal Whether the two numbers are equal.
	 */
	private void takeComplement(AmusingPreciseNumber numb, boolean equal) {
		int i;
		int lastNonZero = 0;
		// find last non zero element of the number from left to right
		for (i = 0; i < preciseNumb.size() - 1; i++) {
			if (preciseNumb.get(i) != "0") {
				lastNonZero = i;
			}
		}
		// if this number isn't a decimal
		if (!preciseNumb.contains(".")) {
			// if it has a zero at the end
			if (preciseNumb.get(preciseNumb.size() - 1).equals("0")) {
				for (i = 0; i < lastNonZero - 1; i++) {
					if (!(preciseNumb.get(i).equals("."))) {
						int temp = Integer.valueOf(preciseNumb.get(i));
						temp = (temp - 9) * -1;
						preciseNumb.set(i, Integer.toString(temp));
					}
				}
				// if it doesn't have a zero at the end and it isn't just a single digit
			} else {
				if (preciseNumb.size() != 1) {
					for (i = 0; i < lastNonZero + 1; i++) {
						if (!(preciseNumb.get(i).equals("."))) {
							int temp = Integer.valueOf(preciseNumb.get(i));
							temp = (temp - 9) * -1;
							preciseNumb.set(i, Integer.toString(temp));
						}
					}
				}
			}
			// if its a decimal
		} else {
			for (i = 0; i < lastNonZero + 1; i++) {
				if (!(preciseNumb.get(i).equals("."))) {
					int temp = Integer.valueOf(preciseNumb.get(i));
					temp = (temp - 9) * -1;
					preciseNumb.set(i, Integer.toString(temp));
				}
			}
		}
		// actually make the number a complement
		int temp = Integer.valueOf(preciseNumb.get(i));
		temp = (temp - 10) * -1;
		preciseNumb.set(i, Integer.toString(temp));
		if (preciseNumb.get(1).equals("0") && !equal) {
			while (preciseNumb.get(1).equals("0")) {
				preciseNumb.remove(1);
			}
		}
	}

	/**
	 * Helper method used to detect if only one number has a decimal.
	 * 
	 * @param numb The other AmusingPreciseNumber.
	 */
	private void addDecimals(AmusingPreciseNumber numb) {
		if (numb.preciseNumb.contains(".") & !preciseNumb.contains(".")) {
			preciseNumb.add(".");
		} else if (!numb.preciseNumb.contains(".") & preciseNumb.contains(".")) {
			numb.preciseNumb.add(".");
		}
	}

	/**
	 * Used to determine if the AmusingPreciseNumber given as a argument is bigger
	 * than the one that is being operated on.
	 * 
	 * @param numb The given AmusingPreciseNnumber.
	 * @return Whether numb is bigger than the number being operated on.
	 */
	private boolean numbIsBigger(AmusingPreciseNumber numb) {
		boolean isBigger = false;
		boolean invert = false;
		if (preciseNumb.get(0).equals("-") && !(numb.preciseNumb.get(0).equals("-"))) {
			return true;
		} else if (!preciseNumb.get(0).equals("-") && (numb.preciseNumb.get(0).equals("-"))) {
			return false;
		}
		if (preciseNumb.get(0).equals("-") && numb.preciseNumb.get(0).equals("-")) {
			preciseNumb.remove(0);
			numb.preciseNumb.remove(0);
			invert = true;
		}
		for (int i = 0; i < numb.preciseNumb.size(); i++) {
			if (!(preciseNumb.get(i).equals(".")) && !(numb.preciseNumb.get(i).equals("."))) {
				Integer compare1 = Integer.valueOf(numb.preciseNumb.get(i));
				Integer compare2 = Integer.valueOf(preciseNumb.get(i));
				if (compare1 < compare2) {
					break;
				} else if (compare1 == compare2) {
					// do nothing
				} else if ((compare1 > compare2)) {
					isBigger = true;
				}
			}
		}
		if (invert) {
			return !isBigger;
		} else {
			return isBigger;
		}
	}

	/**
	 * Negate this AmusingPreciseNumber.
	 */
	public void negate() {
		if (preciseNumb.get(0).equals("-")) {
			preciseNumb.remove(0);
		} else if (preciseNumb.get(0).equals("+")) {
			preciseNumb.set(0, "-");
		} else {
			preciseNumb.add(0, "-");
		}
	}

	/**
	 * Take the absolute value of this AmusingPreciseNumber, making it positive.
	 */
	public void abs() {
		if (preciseNumb.get(0).equals("-")) {
			preciseNumb.remove(0);
		}
	}

	/**
	 * Adds the two given AmusingPreciseNumbers, leaving the two
	 * AmusingPreciseNumbers given as arguments unchanged.
	 * 
	 * @param numb1 The first AmusingPrecise number to be added.
	 * @param numb2 The second AmusingPrecise number to be added.
	 * @return The sum of these two AmusingPreciseNumbers as another
	 *         AmusingPreciseNumber.
	 */
	public static AmusingPreciseNumber add(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
		AmusingPreciseNumber temp1 = new AmusingPreciseNumber(numb1);
		AmusingPreciseNumber temp2 = new AmusingPreciseNumber(numb2);
		temp1.add(temp2);
		return temp1;
	}

	/**
	 * Subtracts the two given AmusingPreciseNumbers, leaving the two
	 * AmusingPreciseNumbers given as arguments unchanged.
	 * 
	 * @param numb1 The first AmusingPrecise number to be subtracted from.
	 * @param numb2 The second AmusingPrecise number to be used to subtract.
	 * @return The difference of these two AmusingPreciseNumbers as another
	 *         AmusingPreciseNumber (numb1 - numb2).
	 */
	public static AmusingPreciseNumber subtract(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
		AmusingPreciseNumber temp1 = new AmusingPreciseNumber(numb1);
		AmusingPreciseNumber temp2 = new AmusingPreciseNumber(numb2);
		temp1.subtract(temp2);
		return temp1;
	}

	/**
	 * Negates the given AmusingPreciseNumber, leaving the original
	 * AmusingPreciseNumber given as an argument unchanged.
	 * 
	 * @param numb The AmusingPreciseNumber to negate.
	 * @return The negated number as another AmusingPreciseNumber.
	 */
	public static AmusingPreciseNumber negate(AmusingPreciseNumber numb) {
		AmusingPreciseNumber temp = new AmusingPreciseNumber(numb);
		temp.negate();
		return temp;
	}

	/**
	 * Takes the absolute value of the given AmusingPreciseNumber, leaving the
	 * original AmusingPreciseNumber given as an argument unchanged.
	 * 
	 * @param numb The AmusingPreciseNumber to take the absolute value of.
	 * @return The positive number as another AmusingPreciseNumber.
	 */
	public static AmusingPreciseNumber abs(AmusingPreciseNumber numb) {
		AmusingPreciseNumber temp = new AmusingPreciseNumber(numb);
		temp.abs();
		return temp;
	}

	@Override
	public String toString() {
		String toReturn = "";
		for (int i = 0; i < preciseNumb.size(); i++) {
			toReturn += preciseNumb.get(i);
		}
		if (toReturn.charAt(0) == '+' && toReturn.length() != 1) {
			toReturn = toReturn.substring(1, toReturn.length());
		}
		toReturn = removeLeadingString(toReturn);
		if (toReturn.equals("-0")) {
			toReturn = "0";
		}
		return toReturn;
	}
}