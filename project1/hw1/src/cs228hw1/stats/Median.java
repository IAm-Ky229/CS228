package cs228hw1.stats;

import java.util.ArrayList;
import java.util.Collections;

/**
 * StatObject that gets the median of a set of data
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type for this class that extends Number
 */
public class Median<T extends Number> extends BasicCalculations<T> {

	/**
	 * Default constructor for Median with no arguments
	 */
	public Median() {
		super();
	}

	/**
	 * Constructor for a Median object that takes an ArrayList of generic type T
	 * 
	 * @param x The list of data to use
	 */
	public Median(ArrayList<T> x) {
		super(x);
	}

	// The calculations for finding the median of the data. It first must be sorted
	public ArrayList<Number> GetResult() throws RuntimeException {
		if ((GetData() == null) || GetData().isEmpty()) {
			throw new RuntimeException();
		}
		ArrayList<Number> retVal = new ArrayList<>();
		ArrayList<Double> sorted = new ArrayList<>();

		for (int i = 0; i < GetData().size(); i++) {
			double temp = GetData().get(i).doubleValue();
			sorted.add(temp);
		}
		Collections.sort(sorted);
		double median = 0;
		if (sorted.size() % 2 == 0) {
			int leftValue = sorted.size() / 2 - 1;
			int rightValue = (sorted.size() / 2);
			median = ((sorted.get(leftValue).doubleValue()) + (sorted.get(rightValue).doubleValue())) / 2;
		} else {
			int middle = sorted.size() / 2;
			median = sorted.get(middle).doubleValue();
		}
		retVal.add(median);
		return retVal;
	}
}
