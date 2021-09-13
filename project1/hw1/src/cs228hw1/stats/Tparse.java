package cs228hw1.stats;

/**
 * Class made to parse strings and return the correct type of T object.
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <T> The generic type of the class that extends Number.
 */
public class Tparse<T extends Number> implements IParser<T> {
	private Number temp;

	// Parses a string and returns a T type, will not do anything if the string
	// isn't a number
	@SuppressWarnings("unchecked")
	public T parse(String str) {
		try {
			temp = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			// Because we can't do statistical measurements on non-numbers
		}
		// Return temp casted to the generic T type
		return (T) temp;
	}

}
