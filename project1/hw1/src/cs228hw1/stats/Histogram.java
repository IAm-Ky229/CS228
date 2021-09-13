package cs228hw1.stats;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Obtains the "windows" for a histogram given the range of numbers to work with
 * and desired bins
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type for this class that extends Number
 */
public class Histogram<T extends Number> extends BasicCalculations<T> {
	// The number of bins the histogram will contain
	private int bins;
	// The maximum and minimum ranges for the histogram
	private double minRange, maxRange;
	// The resulting bin ranges from the desired bins and ranges
	private ArrayList<Number> binRanges = new ArrayList<Number>();

	/**
	 * Default constructor for Histogram with no arguments. minRange and maxRange
	 * are initially assigned negative and positive infinity, and bins is assigned
	 * 10
	 */
	public Histogram() {
		super();
		bins = 10;
		minRange = Double.NEGATIVE_INFINITY;
		maxRange = Double.POSITIVE_INFINITY;
	}

	/**
	 * Constructor for the histogram object that takes an ArrayList of generic type
	 * T.
	 * 
	 * @param x The ArrayList of data for the histogram
	 */
	public Histogram(ArrayList<T> x) {
		super(x);
	}

	// Performs the calculations required to find the bin ranges
	public ArrayList<Number> GetResult() throws RuntimeException {
		if (minRange > maxRange) {
			throw new RuntimeException();
		} else if (minRange == Double.NEGATIVE_INFINITY || maxRange == Double.POSITIVE_INFINITY) {
			throw new RuntimeException();
		}
		double range = maxRange - minRange;
		double binRange = range / bins;
		double position = minRange;
		for (int i = 0; i < bins; i++) {
			binRanges.add((int) position);
			position += binRange;
		}
		binRanges.add((int) position);
		return binRanges;
	}

	/**
	 * Sets the number of bins of the histogram
	 * 
	 * @param bins Desired number of bins
	 * @throws InputMismatchException catches an invalid character input
	 */
	public void SetNumberBins(Number bins) throws InputMismatchException {
		this.bins = bins.intValue();
	}

	/**
	 * Getter method for number of bins in the histogram
	 * 
	 * @return The number of bins for this histogram
	 */
	public Number GetNumberBins() {
		return bins;
	}

	/**
	 * Sets the minimum range of the histogram
	 * 
	 * @param min the desired minimum range
	 * @throws InputMismatchException Catches an invalid input
	 */
	public void SetMinRange(Number min) throws InputMismatchException {
		minRange = min.doubleValue();
	}

	/**
	 * Gets the minimum range of the histogram
	 * 
	 * @return the minimum range of the histogram
	 */
	public Number GetMinRange() {
		return minRange;
	}

	/**
	 * Sets The maximum range of the histogram
	 * 
	 * @param max The desired max of the histogram
	 * @throws InputMismatchException Catches an invalid input
	 */
	public void SetMaxRange(Number max) throws InputMismatchException {
		maxRange = max.doubleValue();
	}

	/**
	 * Gets the maximum range of the histogram
	 * 
	 * @return the maximum range of this histogram
	 */
	public Number GetMaxRange() {
		return maxRange;
	}

}
