package cs228hw2.tests;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import cs228hw2.AmusingPreciseNumber;

public class AmusingPreciseNumberTest {
	public static void main(String[] args) throws IOException {
		AmusingPreciseNumber numberOne = new AmusingPreciseNumber("-82");
		System.out.println(numberOne.toString());
		AmusingPreciseNumber numberTwo = new AmusingPreciseNumber("4943");
		System.out.println(numberTwo.toString());
		numberOne.subtract(numberTwo);
		System.out.println(numberOne.toString());
		Reader r = new StringReader("489230489");
		AmusingPreciseNumber numberThree = new AmusingPreciseNumber(r);
		System.out.println(numberThree.toString());
		//AmusingPreciseNumber numberFour = new AmusingPreciseNumber("48932084.4932048");
	}
}
