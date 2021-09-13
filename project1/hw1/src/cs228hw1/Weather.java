package cs228hw1;

import cs228hw1.stats.StatisticsShell;
import cs228hw1.stats.Statistics.DATA;
import cs228hw1.stats.Tparse;
import java.util.Scanner;
import cs228hw1.stats.Average;
import cs228hw1.stats.Median;
import cs228hw1.stats.Maximum;
import cs228hw1.stats.Minimum;
import java.util.ArrayList;
import cs228hw1.stats.Histogram;

/**
 * Client that performs statistics calculations on a NOAA weather data file.
 * 
 * @author Kylus Pettit-Ehler
 *
 */
public class Weather {

	public static <T extends Number> void main(String[] args) {
		// ArrayList made to store all temperature data, takes a Tparse object to create
		// generic types
		StatisticsShell<T> temperatureData = new StatisticsShell<T>(new Tparse<T>());
		// ArrayList made to store all time data, takes a Tparse object to create
		// generic types
		StatisticsShell<T> timeData = new StatisticsShell<T>(new Tparse<T>());
		System.out.print("Please enter the directory of the file to read: \n");
		Scanner input = new Scanner(System.in);
		String directory = input.next();
		boolean readable = temperatureData.ReadFile(directory, DATA.TEMP);
		// Handle situation where file is not found / readable
		while (!readable) {
			System.out.print("Couldn't find that file. Be sure you are entering the directory correctly: \n");
			directory = input.next();
			readable = temperatureData.ReadFile(directory, DATA.TEMP);
		}
		timeData.ReadFile(directory, DATA.YR_MO_DA_HR_MN);
		// Initialize the variables we will use to add the appropriate StatObjects to
		// temperatureData
		int i = 0;
		// Turn the generic T type into a string for comparison
		String Comp = timeData.GetDataSet().get(0).toString();
		// Extract the month and day from the created string
		String month = Comp.toString().substring(5, 7), day = Comp.toString().substring(7, 9);
		// These numbers will be updated and put into the placeholder arrays so we know
		// what sections of data to add to the StatObjects
		int beginMonth = 0, endMonth = 0, beginDay = 0, endDay = 0;
		// This will be tracking our position in the placeholder arrays
		int position = 0;
		/*
		 * STAT OBJECTS WILL BE ADDED SYSTEMETICALLY (IN A SPECIFIC ORDER SO THEY CAN BE
		 * LOCATED LATER)
		 */
		ArrayList<Integer> monthPlaceholders = new ArrayList<>();
		ArrayList<Integer> dayPlaceholders = new ArrayList<>();
		// While we have not iterated through the whole array
		while (i < (temperatureData.GetDataSet().size())) {
			// Check to see if the month has changed
			Comp = timeData.GetDataSet().get(i).toString();
			if (month.compareTo(Comp.toString().substring(5, 7)) != 0) {
				// If it has, add the appropriate objects and set the new month
				Maximum<T> v = new Maximum<>();
				Minimum<T> w = new Minimum<>();
				temperatureData.AddStatObject(v, beginMonth, endMonth);
				temperatureData.AddStatObject(w, beginMonth, endMonth);
				// Be sure to add 1 so we don't put the last number of last month into the new
				// set of data
				beginMonth = endMonth + 1;
				endMonth++;
				month = Comp.toString().substring(5, 7);
				// THE LAST PLACEHOLDER (FROM LEFT TO RIGHT) ADDED WILL BE FOR A MINIMUM
				monthPlaceholders.add(position);
				// THE FIRST PLACEHOLDER ADDED WILL BE FOR A MAXIMUM
				monthPlaceholders.add(position + 1);
				position += 2;
			} else {
				endMonth++;
			}
			// Check to see if the day has changed
			if (day.compareTo(Comp.toString().substring(7, 9)) != 0) {
				// If it has, add the appropriate objects and set the new day
				Average<T> x = new Average<>();
				Median<T> y = new Median<>();
				Maximum<T> z = new Maximum<>();
				temperatureData.AddStatObject(x, beginDay, endDay);
				temperatureData.AddStatObject(y, beginDay, endDay);
				temperatureData.AddStatObject(z, beginDay, endDay);
				// Be sure to add 1 so that we don't put the last number of yesterday into our
				// new set of data
				beginDay = endDay + 1;
				endDay++;
				day = Comp.toString().substring(7, 9);
				for (int j = 0; j < 3; j++) {
					// THE FIRST PLACEHOLDER (FROM LEFT TO RIGHT) WILL BE FOR AN AVERAGE
					// THE NEXT WILL BE FOR A MEDIAN
					// THE LAST WILL BE FOR A MAXIMUM
					dayPlaceholders.add(position + j);
				}
				position += 3;
			} else {
				endDay++;
			}
			i++;
		}
		// Get the last month and day StatObject data
		Maximum<T> v = new Maximum<>();
		Minimum<T> w = new Minimum<>();
		temperatureData.AddStatObject(v, beginMonth, endMonth);
		temperatureData.AddStatObject(w, beginMonth, endMonth);
		monthPlaceholders.add(position);
		monthPlaceholders.add(position + 1);
		position += 2;
		Average<T> x = new Average<>();
		Median<T> y = new Median<>();
		Maximum<T> z = new Maximum<>();
		temperatureData.AddStatObject(x, beginDay, endDay);
		temperatureData.AddStatObject(y, beginDay, endDay);
		temperatureData.AddStatObject(z, beginDay, endDay);
		for (int j = 0; j < 3; j++) {
			dayPlaceholders.add(position + j);
		}
		// Get result for all the objects that have been added
		ArrayList<ArrayList<Number>> results = temperatureData.MapCar();
		System.out.println();
		int counter = 1;
		// Print out the maximum and minimum for every month
		for (int k = 0; k < monthPlaceholders.size(); k += 2) {
			System.out.println("      === MONTH " + counter + " ===");
			System.out.println("MAXIMUM TEMP FOR MONTH: "
					+ results.get(monthPlaceholders.get(k)).toString().replace("[", "").replace("]", "") + "°F");
			System.out.println("MINIMUM TEMP FOR MONTH: "
					+ results.get(monthPlaceholders.get(k + 1)).toString().replace("[", "").replace("]", "") + "°F");
			System.out.println("----------------------------");
			counter++;
		}
		counter = 1;
		// Print out the average and median for every day
		for (int m = 0; m < dayPlaceholders.size(); m += 3) {
			System.out.println("      === DAY " + counter + " ===");
			System.out.println("AVERAGE TEMP FOR DAY: "
					+ results.get(dayPlaceholders.get(m)).toString().replace("[", "").replace("]", "") + "°F");
			System.out.println("MEDIAN TEMP FOR DAY: "
					+ results.get(dayPlaceholders.get(m + 1)).toString().replace("[", "").replace("]", "") + "°F");
			System.out.println("----------------------------");
			counter++;
		}
		// Make a new histogram and assign it the appropriate values
		Histogram<T> dailyMax = new Histogram<T>();
		dailyMax.SetMaxRange(110);
		dailyMax.SetMinRange(-40);
		dailyMax.SetNumberBins(15);
		ArrayList<Number> ranges = dailyMax.GetResult();
		// Turn the ranges into a format we can use
		// Put them into an ArrayList that stores their "frequency"
		int[] counts = new int[ranges.size()];
		for (int n = 2; n < dayPlaceholders.size(); n += 3) {
			for (int c = 0; c < ranges.size(); c++) {
				if ((results.get(dayPlaceholders.get(n)).get(0).intValue()) > (ranges.get(ranges.size() - c - 1))
						.intValue()) {
					counts[counts.length - c - 1]++;
					break;
				}
			}
		}
		// Use that format to print out every window along with stars to signify
		// frequency
		// Start at index f = 1 so the data is not offset
		System.out.println();
		System.out.println("=== FREQUENCY OF MAX TEMPERATURES PER DAY ===");
		System.out.println();
		for (int f = 1; f < counts.length; f++) {
			System.out.print(ranges.get(f - 1) + " - " + ranges.get(f) + " °F: ");
			for (int p = 0; p < counts[f - 1]; p++) {
				System.out.print("*");
			}
			System.out.println();
		}
		input.close();
	}
}
