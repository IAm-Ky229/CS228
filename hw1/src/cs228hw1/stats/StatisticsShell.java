package cs228hw1.stats;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that handles the storage and execution of all statistics objects and
 * their data.
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type of the class that extends Number.
 */
public class StatisticsShell<T extends Number> implements Statistics<T> {
	// ArrayList for storing StatObjects
	private ArrayList<StatObject<T>> StatObjects;
	// ArrayList for storing the data read from the file
	private ArrayList<T> ActiveDataSet;
	// The "converter" that is passed to the shell object upon construction
	private Tparse<T> cnvrt;

	/**
	 * Constructor for StatisticsShell object
	 * 
	 * @param p A parser that returns T types from strings
	 */
	public StatisticsShell(Tparse<T> p) {
		cnvrt = p;
		StatObjects = new ArrayList<StatObject<T>>();
		ActiveDataSet = new ArrayList<T>();
	}

	// Read the given file and search for a particular type of data
	public boolean ReadFile(String path, DATA d) {
		File file = new File(path);
		try {
			Scanner read = new Scanner(file);
			read.nextLine();
			// Check to see which column of data should be read and add it to the active
			// data set
			do {
				for (int i = 0; i < d.ordinal(); i++) {
					read.next();
				}
				ActiveDataSet.add(cnvrt.parse(read.next()));
				read.nextLine();
			} while (read.hasNext());
			read.close();
			// Return true if the file was successfully found
			return true;
			// Otherwise catch the exception and return false
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	// Getter method for stat objects
	public StatObject<T> GetStatObject(int i) {
		return StatObjects.get(i);
	}

	// Remover method for stat objects
	public StatObject<T> RemoveStatObject(int i) {
		StatObject<T> removed = StatObjects.get(i);
		StatObjects.remove(i);
		return removed;
	}

	// get the size of the stat object arraylist
	public int Count() {
		return StatObjects.size();
	}

	// Getter method for the active data set of the shell object
	public ArrayList<T> GetDataSet() {
		return ActiveDataSet;
	}

	// execute the GetResult() method on all stat objects in the statobjects
	// arraylist
	public ArrayList<ArrayList<Number>> MapCar() {
		ArrayList<ArrayList<Number>> results = new ArrayList<ArrayList<Number>>();
		for (int i = 0; i < Count(); i++) {
			results.add(StatObjects.get(i).GetResult());
		}
		return results;
	}

	// Add a stat object to the statobjects arraylist
	public void AddStatObject(StatObject<T> so) {
		StatObjects.add(so);
	}

	// Add a stat object to the statobjects arraylist with a certain "window" of
	// data
	public void AddStatObject(StatObject<T> so, int first, int last) {
		ArrayList<T> subset = new ArrayList<>();
		for (int i = first; i < last; i++) {
			if (ActiveDataSet.get(i) == null) {
				// Don't do anything, AddStatObject has found a null placeholder
			} else {
				subset.add(ActiveDataSet.get(i));
			}
		}
		StatObject<T> temp = so;
		temp.SetData(subset);
		StatObjects.add(temp);
	}

}
