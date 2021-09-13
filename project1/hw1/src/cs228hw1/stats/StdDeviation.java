package cs228hw1.stats;

import java.util.ArrayList;

/**
 * Finds the sample standard deviation of a set of data
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type of the class that extends Number
 */
public class StdDeviation<T extends Number> extends BasicCalculations<T> {

	/**
	 * default constructor for StdDeviation with no arguments
	 */
	public StdDeviation() {
		super();
	}

	/**
	 * Constructor for a StdDeviation object that takes an ArrayList of generic type
	 * T
	 * 
	 * @param x The list of data to be worked with
	 */
	public StdDeviation(ArrayList<T> x) {
		super(x);
	}

	// Performs the calculations necessary to return the standard deviation of the
	// given data
	public ArrayList<Number> GetResult() throws RuntimeException {
		if ((GetData() == null) || GetData().isEmpty()) {
			throw new RuntimeException();
		}
		ArrayList<Number> retVal = new ArrayList<Number>();
		ArrayList<Number> deviations = new ArrayList<Number>();
		double total = 0;
		int terms = 0;
		while (terms < GetData().size()) {
			total += GetData().get(terms).doubleValue();
			terms++;
		}
		double mean = total / terms;
		for (int i = 0; i < GetData().size(); i++) {
			deviations.add(Math.pow(GetData().get(i).doubleValue() - mean, 2));
		}
		total = 0;
		for (int i = 0; i < deviations.size(); i++) {
			total += deviations.get(i).doubleValue();
		}
		retVal.add(Math.sqrt(total / (terms - 1)));
		return retVal;
	}

}
