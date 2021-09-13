package cs228hw1;

import java.util.ArrayList;
import java.util.Scanner;

import cs228hw1.stats.StatisticsShell;
import cs228hw1.stats.Tparse;
import cs228hw1.stats.Statistics.DATA;
import cs228hw1.stats.StdDeviation;

public class Test {

	public static <T extends Number> void main(String[] args) {
		// ArrayList made to store all temperature data
		StatisticsShell<T> temperatureData = new StatisticsShell<T>(new Tparse<T>());
		// ArrayList made to store all time data
		// StatisticsShell<T> timeData = new StatisticsShell<T>(new Tparse<T>());
		System.out.print("Please enter the directory of the file to read: \n");
		Scanner input = new Scanner(System.in);
		String directory = input.next();
		boolean readable = temperatureData.ReadFile(directory, DATA.DIR);
		// Handle situation where file is not found
		while (!readable) {
			System.out.print("Couldn't find that file. Be sure you are entering the directory correctly: \n");
			directory = input.next();
			readable = temperatureData.ReadFile(directory, DATA.TEMP);
		}
		StdDeviation<T> x = new StdDeviation<>();
		temperatureData.AddStatObject(x, 0, 35);
		ArrayList<ArrayList<Number>> results = temperatureData.MapCar();
		System.out.println(results.get(0).get(0));
		input.close();
	}
}
