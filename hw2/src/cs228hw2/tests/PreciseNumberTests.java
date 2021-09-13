package cs228hw2.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs228hw2.AmusingPreciseNumber;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * An important note, you may need to change every number, depending on how you implemented toString
 * 		in my case, toString only places a decimal if it is absolutely necessary, and does not have 
 * 			a leading 0, i.e. 0.2391 would be .2391.
 * 		A way I might resolve this is to type assertEquals( into find and modify numbers as needed
 * Also, check the ReaderConst test to test if your reader constructor allows for 100,000 digits of precision
 * 		Warnings: fairly trivial test and takes a looooooooong time
 * 		Must uncomment to test
 * @author John King
 *
 */
@SuppressWarnings("unused")
class PreciseNumberTests 
{

	@Test
	void testToString() 
	{
		AmusingPreciseNumber x = new AmusingPreciseNumber(512);
		assertEquals("512", x.toString());
		AmusingPreciseNumber y = new AmusingPreciseNumber(10000);
		assertEquals("10000", y.toString());
		x = new AmusingPreciseNumber(000001);
		assertEquals("1", x.toString());
		x = new AmusingPreciseNumber(-50000);
		assertEquals("-50000", x.toString());
		x = new AmusingPreciseNumber(-500319300);
		assertEquals("-500319300", x.toString());
		x = new AmusingPreciseNumber(0);
		assertEquals("0", x.toString());
	}
	/*
	 * Note: before uncommenting, ensure the backing list is public (obviously change this back once done)
	 */
	@Test
	void testDeepCopy()
	{
		/*AmusingPreciseNumber x = new AmusingPreciseNumber(573);
		AmusingPreciseNumber y = new AmusingPreciseNumber(x);
		//this is the list... I know I could have tested this in a better way, but I'm lazy
			//and this is the first thing I thought of
		y.numList.set(0, 5);
		//since the 0th index is the Least Significant Bit in my case
		assertEquals(3, (int) x.numList.get(0));
		assertEquals(5, (int) y.numList.get(0));*/
		
	}
	
	@Test
	void testAddSubtract()
	{
		AmusingPreciseNumber x = new AmusingPreciseNumber(512);
		AmusingPreciseNumber y = new AmusingPreciseNumber(12);
		x.add(y);
		assertEquals("524", x.toString());
		
		x = new AmusingPreciseNumber(500);
		y = new AmusingPreciseNumber(390);
		x.add(y);
		assertEquals("890", x.toString());
		
		//Basic adding tests (may also test subtract, depending on how you did it)
		x = new AmusingPreciseNumber(99847);
		y = new AmusingPreciseNumber(99910083);
		y.add(x);
		assertEquals("100009930", y.toString());
		x = new AmusingPreciseNumber(-99847);
		y = new AmusingPreciseNumber(99910083);
		y.add(x);
		assertEquals("99810236", y.toString());
		x = new AmusingPreciseNumber(99847);
		y = new AmusingPreciseNumber(-99910083);
		y.add(x);
		assertEquals("-99810236", y.toString());
		x = new AmusingPreciseNumber(-99847);
		y = new AmusingPreciseNumber(-99910083);
		y.add(x);
		assertEquals("-100009930", y.toString());
		
		//Subtraction tests
		x = new AmusingPreciseNumber(99847);
		y = new AmusingPreciseNumber(99910083);
		y.subtract(x);
		assertEquals("99810236", y.toString());
		x = new AmusingPreciseNumber(-99847);
		y = new AmusingPreciseNumber(99910083);
		y.subtract(x);
		assertEquals("100009930", y.toString());
		x = new AmusingPreciseNumber(99847);
		y = new AmusingPreciseNumber(-99910083);
		y.subtract(x);
		assertEquals("-100009930", y.toString());
		x = new AmusingPreciseNumber(-99847);
		y = new AmusingPreciseNumber(-99910083);
		y.subtract(x);
		assertEquals("-99810236", y.toString());
		
		//A few zero cases, if you think you need more due to your implementation, you might want to
		x = new AmusingPreciseNumber(0);
		y = new AmusingPreciseNumber(0);
		x.add(y);
		assertEquals("0", x.toString());
		x.subtract(y);
		assertEquals("0", x.toString());
		//sorry about this
		x.negate();
		assertEquals("0", x.toString());
		x = new AmusingPreciseNumber(800);
		x.add(y);
		assertEquals("800", x.toString());
		x.subtract(y);
		assertEquals("800", x.toString());
		y = AmusingPreciseNumber.negate(x);
		x.add(y);
		assertEquals("0", x.toString());
	}

	//also, I was lazy, so this also tests the non-static negate method and non-static abs method
	@Test
	void staticTests()
	{
		//same as non-static add method tests, but if you want to see some other tests, let me know
		AmusingPreciseNumber x = new AmusingPreciseNumber(99847);
		AmusingPreciseNumber y = new AmusingPreciseNumber(99910083);
		AmusingPreciseNumber z = AmusingPreciseNumber.add(x, y);
		assertEquals("100009930", z.toString());
		assertEquals("99847", x.toString());
		assertEquals("99910083", y.toString());
		x.negate();
		z = AmusingPreciseNumber.add(x, y);
		assertEquals("99810236", z.toString());
		y.negate();
		z = AmusingPreciseNumber.add(x, y);
		assertEquals("-100009930", z.toString());
		x.negate();
		z = AmusingPreciseNumber.add(x, y);
		assertEquals("-99810236", z.toString());
		
		x = new AmusingPreciseNumber(600);
		y = new AmusingPreciseNumber(8000);
		z = AmusingPreciseNumber.add(x, y);
		assertEquals("8600", z.toString());
		assertEquals("600", x.toString());
		assertEquals("8000", y.toString());
		//here we take a small break to test abs and negate
		z.negate();
		assertEquals("-8600", z.toString());
		z.abs();
		assertEquals("8600", z.toString());
		z.abs();
		assertEquals("8600", z.toString());
		//the static versions
		z = AmusingPreciseNumber.negate(x);
		assertEquals("-600", z.toString());
		z = AmusingPreciseNumber.abs(AmusingPreciseNumber.negate(x));
		assertEquals("600", z.toString());
		z = AmusingPreciseNumber.abs(z);
		assertEquals("600", z.toString());
		z = AmusingPreciseNumber.negate(AmusingPreciseNumber.negate(x));
		assertEquals(x.toString(), z.toString());
		
		//back to static subtraction
		z = AmusingPreciseNumber.subtract(x, y);
		assertEquals("-7400", z.toString());
		x.negate();
		z = AmusingPreciseNumber.subtract(x, y);
		assertEquals("-8600", z.toString());
		y.negate();
		z = AmusingPreciseNumber.subtract(x, y);
		assertEquals("7400", z.toString());
		x.negate();
		z = AmusingPreciseNumber.subtract(x, y);
		assertEquals("8600", z.toString());
	}
	
	@Test
	void testStringConstructor()
	{
		AmusingPreciseNumber x = new AmusingPreciseNumber("+987");
		assertEquals("987", x.toString());
		x = new AmusingPreciseNumber("-987");
		assertEquals("-987", x.toString());
		x = new AmusingPreciseNumber("-943.56");
		assertEquals("-943.56", x.toString());
		x = new AmusingPreciseNumber("+0009.003000");
		assertEquals("9.003", x.toString());
		x = new AmusingPreciseNumber("0.000000");
		assertEquals("0", x.toString());
		x = new AmusingPreciseNumber("-00.0000059");
		assertEquals("-.0000059", x.toString());
		x = new AmusingPreciseNumber("9837.");
		assertEquals("9837", x.toString());
		
		//test whether the constructor rejects invalid strings
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber("+-482"));
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber(""));
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber("9.921.0"));
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber("92100$00"));
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber("00000.000+"));
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber("+."));
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber("-."));
	}
	
	//probably not strictly necessary, but whatever
	//could probably be included in adding tests, but this way allows you to not need the
		//string constructor for that test.
	@Test
	void testDecimalAdding()
	{
		//I'm just going to assume that if this passes, you can add decimals
		AmusingPreciseNumber x = new AmusingPreciseNumber("483.83");
		AmusingPreciseNumber y = new AmusingPreciseNumber("9.2347907");
		x.add(y);
		assertEquals("493.0647907", x.toString());
	}
	
	@Test
	void testReaderConst() throws IOException
	{
		String numb = "+29358";
		Reader r = new StringReader(numb);
		AmusingPreciseNumber x = new AmusingPreciseNumber(r);
		assertEquals("29358", x.toString());
		numb = " -000397236.0023760000 31298374";
		r = new StringReader(numb);
		x = new AmusingPreciseNumber(r);
		assertEquals("-397236.002376", x.toString());
		numb = " 0  \n ";
		r = new StringReader(numb);
		x = new AmusingPreciseNumber(r);
		assertEquals("0", x.toString());
		numb = "";
		final Reader b = new StringReader(numb); 
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber(b));
		numb = " 234897a ";
		final Reader c = new StringReader(numb);
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber(c));
		numb = "+.";
		final Reader p = new StringReader(numb);
		assertThrows(RuntimeException.class, () -> new AmusingPreciseNumber(p));
		
		
		
		
		
		/*
		 * WARNING, DO NOT UNCOMMENT UNTIL EVERTHING ELSE IS TESTED
		 * this final test took my implementation 297.741s to perform on boosted PC performance
		 * (it's kinda bad though so yours may do better)
		 * It does handle exactly 100,000 digits of precision though (in the lamest way possible)
		numb = "";
		numb += "1";
		for(int i = 0; i < 99999; i++)
		{
			numb += 0;
		}
		r = new StringReader(numb);
		x = new AmusingPreciseNumber(r);
		numb = "";
		for(int i = 0; i < 99999; i++)
		{
			numb += "9";
		}
		r = new StringReader(numb);
		AmusingPreciseNumber y = new AmusingPreciseNumber(r);
		x.subtract(y);
		assertEquals("1", x.toString());
		 */
	}
}
