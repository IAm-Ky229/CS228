package cs228hw1.stats;

import java.util.ArrayList;

/**
 * The methods that are common to all the classes of statistical measurement
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type for the class that extends Number
 */
public class BasicCalculations<T extends Number> implements StatObject<T> {
	// The copy of the ArrayList passed by the constructor
	private ArrayList<T> copy;
	// The description of this StatObject
	private String description;

	/**
	 * The default super constructor for a StatObject with no parameters
	 */
	public BasicCalculations() {
		copy = new ArrayList<>();
		description = "";
	}

	/**
	 * The super constructor that takes an ArrayList of generic type T
	 * 
	 * @param x The list of data to assign to this object
	 */
	public BasicCalculations(ArrayList<T> x) {
		SetData(x);
	}

	// Change the description of this StatObject
	public void SetDescription(String d) {
		description = d;
	}

	// Get the description of this StatObject
	public String GetDescription() {
		return description;
	}

	// Return the data set of this StatObject
	public ArrayList<T> GetData() {
		return copy;
	}

	// Actual calculations done in their respective classes
	public ArrayList<Number> GetResult() throws RuntimeException {
		return null;
	}

	// Change the data that is currently in the StatObject
	public void SetData(ArrayList<T> data) {
		// copy.clear();
		for (int i = 0; i < data.size(); i++) {
			T y = data.get(i);
			copy.add(y);
		}
	}

	public boolean checkNull(T object) {
		return object == null;
	}
}
