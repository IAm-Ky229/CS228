package cs228hw1.stats;

import java.util.ArrayList;

/**
 * Class for finding the maximum of a set of data
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type of the class that extends Number
 */
public class Maximum<T extends Number> extends BasicCalculations<T> {

	/**
	 * Default Maximum constructor with no parameters
	 */
	public Maximum() {
		super();
	}

	/**
	 * Constructor for a Maximum object
	 * 
	 * @param x The list of data to work with
	 */
	public Maximum(ArrayList<T> x) {
		super(x);
	}

	// Iterates through the list to find the maximum
	public ArrayList<Number> GetResult() throws RuntimeException {
		if ((GetData() == null) || GetData().isEmpty()) {
			throw new RuntimeException();
		}
		ArrayList<Number> retVal = new ArrayList<>();
		Integer maximum = GetData().get(0).intValue();
		for (int i = 0; i < GetData().size(); i++) {
			if (GetData().get(i).intValue() > maximum) {
				maximum = GetData().get(i).intValue();
			}
		}
		retVal.add(maximum);
		return retVal;
	}

}
