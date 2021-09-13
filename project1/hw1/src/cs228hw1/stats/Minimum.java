package cs228hw1.stats;

import java.util.ArrayList;

/**
 * StatObject that finds the minimum of a set of data
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type of the class that extends Number
 */
public class Minimum<T extends Number> extends BasicCalculations<T> {

	/**
	 * default constructor for Minimum with no arguments
	 */
	public Minimum() {
		super();
	}

	/**
	 * The constructor for a Minimum object
	 * 
	 * @param x The list that is to be worked with
	 */
	public Minimum(ArrayList<T> x) {
		super(x);
	}

	// Iterates through and finds the minimum, and returns it
	public ArrayList<Number> GetResult() throws RuntimeException {
		if ((GetData() == null) || GetData().isEmpty()) {
			throw new RuntimeException();
		}
		ArrayList<Number> retVal = new ArrayList<>();
		Integer minimum = GetData().get(0).intValue();
		for (int i = 0; i < GetData().size(); i++) {
			if (GetData().get(i).intValue() < minimum) {
				minimum = GetData().get(i).intValue();
			}
		}
		retVal.add(minimum);
		return retVal;
	}

}
