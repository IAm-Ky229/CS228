package cs228hw2.tests;

/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import cs228hw2.AmusingLinkedList;
*/
/**
 * The class created to test the integrity of the implemented AmusingLinkedList.
 * 
 * @author Kylus Pettit-Ehler
 *
 */
public class AmusingListTest {

	public static void main(String[] args) {
		// AmusingLinkedList<String> x = new AmusingLinkedList<>();
		/*
		 * 
		 */
		/*
		 * AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		 * 
		 * a.add(0); a.add(10); a.add(20); a.add(null); a.add(40);
		 * 
		 * System.out.println(a.size()); AmusingLinkedList<Integer>.Node n =
		 * a.getNodeAtIndex(1); //assertEquals(10, n.getData());
		 * assertTrue(a.remove(Integer.valueOf(10))); System.out.println(a.toString());
		 * System.out.println(a.contains(10)); //assertEquals(20,
		 * a.getNodeAtIndex(1).getData()); //assertEquals(4, a.size());
		 */
		/*
		 * String message = "Value at index %d should be %d"; AmusingLinkedList<Integer>
		 * a = new AmusingLinkedList<Integer>(); Integer[] data = {0, 10, 20, 30, 40};
		 * 
		 * assertTrue(a.addAll(0, Arrays.asList(data)));
		 * 
		 * // Sanity check all data for (int i = 0; i < 5; i++) { assertEquals(data[i],
		 * a.getNodeAtIndex(i).getData(), String.format(message, i, data[i])); }
		 */
		/*
		 * String message = "Value at index %d should be %d"; AmusingLinkedList<Integer>
		 * a = new AmusingLinkedList<Integer>(); int[] data = { 0, 10, 20, 30, 40 };
		 * 
		 * // ONE ELEMENT ONLY a.add(data[1]); assertEquals(data[1],
		 * a.getNodeAtIndex(0).getData(), String.format(message, 0, 10)); String
		 * message2 = "Next should point to self on 1 element list";
		 * assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getNext(), message2);
		 * message2 = "Prev should point to self on 1 element list";
		 * assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getPrev(), message2);
		 * 
		 * // SECOND ELEMENT a.add(data[2]); message2 =
		 * "Elem 0->next should point to elem 1"; assertSame(a.getNodeAtIndex(1),
		 * a.getNodeAtIndex(0).getNext(), message2); message2 =
		 * "Elem 0->prev should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(0).getPrev(), message2); message2 =
		 * "Elem 1->next should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(1).getNext(), message2); message2 =
		 * "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); assertEquals(data[2],
		 * a.getNodeAtIndex(1).getData(), String.format(message, 1, 20));
		 * 
		 * // THIRD ELEMENT a.add(data[4]); message2 =
		 * "Elem 0->next should point to elem 1"; assertSame(a.getNodeAtIndex(1),
		 * a.getNodeAtIndex(0).getNext(), message2); message2 =
		 * "Elem 0->prev should point to elem 2"; assertSame(a.getNodeAtIndex(2),
		 * a.getNodeAtIndex(0).getPrev(), message2); message2 =
		 * "Elem 1->next should point to elem 2"; assertSame(a.getNodeAtIndex(2),
		 * a.getNodeAtIndex(1).getNext(), message2); message2 =
		 * "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); message2 =
		 * "Elem 2->next should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getNext(), message2); message2 =
		 * "Elem 2->prev should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getPrev(), message2); assertEquals(data[4],
		 * a.getNodeAtIndex(2).getData(), String.format(message, 2, 40));
		 * 
		 * // Fourth Element a.add(0, data[0]); message2 =
		 * "Elem 0->next should point to elem 1"; assertSame(a.getNodeAtIndex(1),
		 * a.getNodeAtIndex(0).getNext(), message2); message2 =
		 * "Elem 0->prev should point to elem 2"; System.out.println("AMUSINGLISTTEST" +
		 * a.debugString()); System.out.println("AMUSINGLISTTEST" +
		 * a.getNodeAtIndex(0)); System.out.println("AMUSINGLISTTEST" +
		 * a.getNodeAtIndex(2)); assertSame(a.getNodeAtIndex(2),
		 * a.getNodeAtIndex(0).getPrev(), message2); message2 =
		 * "Elem 1->next should point to elem 2"; assertSame(a.getNodeAtIndex(2),
		 * a.getNodeAtIndex(1).getNext(), message2); message2 =
		 * "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); message2 =
		 * "Elem 2->next should point to elem 3"; assertSame(a.getNodeAtIndex(3),
		 * a.getNodeAtIndex(2).getNext(), message2);
		 * System.out.println(a.debugString()); message2 =
		 * "Elem 2->prev should point to elem 0";
		 * System.out.println(a.getNodeAtIndex(0));
		 * System.out.println(a.getNodeAtIndex(2)); assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getPrev(), message2); message2 =
		 * "Elem 3->next should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(3).getNext(), message2); message2 =
		 * "Elem 3->prev should point to null";
		 * assertNull(a.getNodeAtIndex(3).getPrev(), message2); assertEquals(data[0],
		 * a.getNodeAtIndex(0).getData(), String.format(message, 0, 0));
		 */
		/*
		 * String message = "Value at index %d should be %d"; AmusingLinkedList<Integer>
		 * a = new AmusingLinkedList<Integer>(); Integer[] data = {0, 10, 20, 30, 40};
		 * 
		 * assertTrue(a.addAll(0, Arrays.asList(data)));
		 * 
		 * // Sanity check all data for (int i = 0; i < 5; i++) { assertEquals(data[i],
		 * a.getNodeAtIndex(i).getData(), String.format(message, i, data[i])); }
		 * 
		 * System.out.println(a.debugString());
		 * 
		 * String message2; message2 = "Elem 0->next should point to elem 1";
		 * assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		 * message2 = "Elem 0->prev should point to elem 4";
		 * assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		 * message2 = "Elem 1->next should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		 * message2 = "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); message2 =
		 * "Elem 2->next should point to elem 3"; assertSame(a.getNodeAtIndex(3),
		 * a.getNodeAtIndex(2).getNext(), message2); message2 =
		 * "Elem 2->prev should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getPrev(), message2); message2 =
		 * "Elem 3->next should point to elem 4"; assertSame(a.getNodeAtIndex(4),
		 * a.getNodeAtIndex(3).getNext(), message2); message2 =
		 * "Elem 3->prev should point to null";
		 * assertNull(a.getNodeAtIndex(3).getPrev(), message2); message2 =
		 * "Elem 4->next should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(4).getNext(), message2); message2 =
		 * "Elem 4->prev should point to elem 2"; assertSame(a.getNodeAtIndex(2),
		 * a.getNodeAtIndex(4).getPrev(), message2);
		 * 
		 * Integer[] dataB = {50, 60, 70, 80, 90}; assertTrue(a.addAll(0,
		 * Arrays.asList(dataB)));
		 * 
		 * // Sanity check all data int i = 0; assertEquals(50,
		 * a.getNodeAtIndex(i).getData(), String.format(message, i++, 50));
		 * assertEquals(60, a.getNodeAtIndex(i).getData(), String.format(message, i++,
		 * 60)); assertEquals(70, a.getNodeAtIndex(i).getData(), String.format(message,
		 * i++, 70)); assertEquals(80, a.getNodeAtIndex(i).getData(),
		 * String.format(message, i++, 80)); assertEquals(90,
		 * a.getNodeAtIndex(i).getData(), String.format(message, i++, 90));
		 * assertEquals(00, a.getNodeAtIndex(i).getData(), String.format(message, i++,
		 * 00)); assertEquals(10, a.getNodeAtIndex(i).getData(), String.format(message,
		 * i++, 10)); assertEquals(20, a.getNodeAtIndex(i).getData(),
		 * String.format(message, i++, 20)); assertEquals(30,
		 * a.getNodeAtIndex(i).getData(), String.format(message, i++, 30));
		 * assertEquals(40, a.getNodeAtIndex(i).getData(), String.format(message, i++,
		 * 40));
		 * 
		 * 
		 * message2 = "Elem 0->next should point to elem 1";
		 * assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		 * message2 = "Elem 0->prev should point to elem 8";
		 * assertSame(a.getNodeAtIndex(8), a.getNodeAtIndex(0).getPrev(), message2);
		 * message2 = "Elem 1->next should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		 * message2 = "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); message2 =
		 * "Elem 2->next should point to elem 3"; assertSame(a.getNodeAtIndex(3),
		 * a.getNodeAtIndex(2).getNext(), message2); message2 =
		 * "Elem 2->prev should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getPrev(), message2); message2 =
		 * "Elem 3->next should point to elem 4"; assertSame(a.getNodeAtIndex(4),
		 * a.getNodeAtIndex(3).getNext(), message2); message2 =
		 * "Elem 3->prev should point to null";
		 * assertNull(a.getNodeAtIndex(3).getPrev(), message2); message2 =
		 * "Elem 4->next should point to elem 5"; assertSame(a.getNodeAtIndex(5),
		 * a.getNodeAtIndex(4).getNext(), message2); message2 =
		 * "Elem 4->prev should point to elem 2"; assertSame(a.getNodeAtIndex(2),
		 * a.getNodeAtIndex(4).getPrev(), message2); message2 =
		 * "Elem 5->next should point to elem 6"; assertSame(a.getNodeAtIndex(6),
		 * a.getNodeAtIndex(5).getNext(), message2); message2 =
		 * "Elem 5->prev should point to null";
		 * assertNull(a.getNodeAtIndex(5).getPrev(), message2); message2 =
		 * "Elem 6->next should point to elem 7"; assertSame(a.getNodeAtIndex(7),
		 * a.getNodeAtIndex(6).getNext(), message2); message2 =
		 * "Elem 6->prev should point to elem 4"; assertSame(a.getNodeAtIndex(4),
		 * a.getNodeAtIndex(6).getPrev(), message2); message2 =
		 * "Elem 8->prev should point to elem 6"; assertSame(a.getNodeAtIndex(6),
		 * a.getNodeAtIndex(8).getPrev(), message2); message2 =
		 * "Elem 9->prev should point to null";
		 * assertNull(a.getNodeAtIndex(9).getPrev(), message2);
		 * 
		 * Integer[] dataC = {}; assertFalse(a.addAll(1, Arrays.asList(dataC)));
		 * 
		 * // Exeption checks AmusingLinkedList<Integer> b = new
		 * AmusingLinkedList<Integer>(); assertThrows(IndexOutOfBoundsException.class,
		 * () -> {b.addAll(1, Arrays.asList(data));});
		 * assertThrows(IndexOutOfBoundsException.class, () -> {b.addAll(-1,
		 * Arrays.asList(data));}); b.add(1);
		 * assertThrows(IndexOutOfBoundsException.class, () -> {b.addAll(2,
		 * Arrays.asList(data));}); assertTrue(b.addAll(1, Arrays.asList(data)),
		 * "Adding at size should append new values");
		 */

		/*
		 * AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>(); Integer[]
		 * addAll = {20, 30, 40};
		 * 
		 * assertEquals(0, a.size()); a.add(null); assertEquals(1, a.size()); a.add(1);
		 * assertEquals(2, a.size()); a.remove(0); assertEquals(1, a.size());
		 * a.remove(Integer.valueOf(1)); assertEquals(0, a.size()); a.add(0, 10);
		 * assertEquals(1, a.size()); a.addAll(Arrays.asList(addAll)); assertEquals(4,
		 * a.size()); a.addAll(0, Arrays.asList(addAll)); assertEquals(7, a.size());
		 * a.retainAll(Arrays.asList(addAll)); assertEquals(6, a.size());
		 * a.removeAll(Arrays.asList(addAll)); System.out.println(a.debugString());
		 * assertEquals(0, a.size());
		 */

		/*
		 * AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>(); Integer[]
		 * retain = {20, 30};
		 * 
		 * a.add(10); a.add(10); a.add(10); a.add(20); a.add(30); a.add(40);
		 * a.add(null); a.add(40);
		 * 
		 * assertEquals(8, a.size()); System.out.println(a.debugString());
		 * assertTrue(a.retainAll(Arrays.asList(retain)));
		 * System.out.println(a.debugString()); assertEquals(20, a.get(0));
		 * assertEquals(30, a.get(1)); assertEquals(2, a.size());
		 */

		/*
		 * AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		 * 
		 * a.add(0); a.add(10); a.add(20); a.add(null); a.add(40);
		 * 
		 * assertEquals(5, a.size()); AmusingLinkedList<Integer>.Node n =
		 * a.getNodeAtIndex(1); assertEquals(10, n.getData());
		 * System.out.println(a.debugString());
		 * assertTrue(a.remove(Integer.valueOf(10)));
		 * System.out.println(a.debugString()); assertFalse(a.contains(10));
		 * assertEquals(20, a.getNodeAtIndex(1).getData()); assertEquals(4, a.size());
		 */

		/*
		 * AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		 * 
		 * a.add(0); a.add(10); a.add(20); a.add(null); a.add(40);
		 * 
		 * assertEquals(5, a.size()); AmusingLinkedList<Integer>.Node n =
		 * a.getNodeAtIndex(1); assertEquals(10, n.getData());
		 * assertTrue(a.remove(Integer.valueOf(10))); assertFalse(a.contains(10));
		 * assertEquals(20, a.getNodeAtIndex(1).getData()); assertEquals(4, a.size());
		 * 
		 * String message2; message2 = "Elem 0->next should point to elem 1";
		 * assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		 * message2 = "Elem 0->prev should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		 * message2 = "Elem 1->next should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		 * message2 = "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); message2 =
		 * "Elem 2->next should point to elem 3"; assertSame(a.getNodeAtIndex(3),
		 * a.getNodeAtIndex(2).getNext(), message2); message2 =
		 * "Elem 2->prev should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getPrev(), message2); message2 =
		 * "Elem 3->next should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(3).getNext(), message2); message2 =
		 * "Elem 3->prev should point to null";
		 * assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		 * 
		 * System.out.println(a.debugString()); assertTrue(a.remove(null));
		 * System.out.println(a.debugString()); assertEquals(3, a.size());
		 * 
		 * message2 = "Elem 0->next should point to elem 1";
		 * assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		 * message2 = "Elem 0->prev should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		 * message2 = "Elem 1->next should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		 * message2 = "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); message2 =
		 * "Elem 2->next should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getNext(), message2); message2 =
		 * "Elem 2->prev should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getPrev(), message2);
		 * 
		 * assertFalse(a.remove(Integer.valueOf(-1)));
		 * 
		 * message2 = "Elem 0->next should point to elem 1";
		 * assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		 * message2 = "Elem 0->prev should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		 * message2 = "Elem 1->next should point to elem 2";
		 * assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		 * message2 = "Elem 1->prev should point to null";
		 * assertNull(a.getNodeAtIndex(1).getPrev(), message2); message2 =
		 * "Elem 2->next should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getNext(), message2); message2 =
		 * "Elem 2->prev should point to elem 0"; assertSame(a.getNodeAtIndex(0),
		 * a.getNodeAtIndex(2).getPrev(), message2);
		 * 
		 * assertEquals(0, a.get(0)); assertEquals(20, a.get(1)); assertEquals(40,
		 * a.get(2));
		 */
	}
}
