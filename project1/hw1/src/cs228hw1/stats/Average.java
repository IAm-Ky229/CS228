package cs228hw1.stats;

import java.util.ArrayList;

/**
 * Finds the average of the given data set
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> Generic type of this class that extends Number
 */
public class Average<T extends Number> extends BasicCalculations<T> {

	/**
	 * Default constructor for Average with no arguments
	 */
	public Average() {
		super();
	}

	/**
	 * Constructor for an Average StatObject object
	 * 
	 * @param x An ArrayList containing numbers of generic type T
	 */
	public Average(ArrayList<T> x) {
		super(x);
	}

	// Find the average of the given set of numbers
	public ArrayList<Number> GetResult() throws RuntimeException {
		if ((GetData() == null) || GetData().isEmpty()) {
			throw new RuntimeException();
		}
		ArrayList<Number> retVal = new ArrayList<>();
		double total = 0;
		int terms = 0;
		while (terms < GetData().size()) {
			total += GetData().get(terms).doubleValue();
			terms++;
		}
		retVal.add(total / terms);
		return retVal;
	}

}