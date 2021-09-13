package cs228hw2.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cs228hw2.AmusingLinkedList;

/**Tests of AmusingLinkedList. Test cases run on just about every method in List and
 * ListIterator, so if I happen to run tests on on a method you didn't implement, you
 * can disable the test using the "@Disabled" decorator, or delete it. I tried to
 * localize tests to each individual method as best as possible, however the most basic
 * methods like add(), remove(), size(), getNodeAtIndex(), etc. show up frequently. For
 * essentially all of the add/remove methods, I manually all of the pointers on your list
 * to ensure they are correct as well. Before bothering with this test suite, make certain
 * your getNodeAtIndex works properly, as that is pretty key for successfully testing things.
 * 
 * @author Braedon Giblin (bgiblin@iastate.edu)
 *
 */
@SuppressWarnings("unused")			// Suppressing warning on junit.*.Disabled
class AmusingLinkedListTest {

	@Test
	void testAmusingLinkedList() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		assertNotNull(a);
		assertTrue(a instanceof List<?>);
	}

	@Test
	void testAddE() {
		String message = "Value at index %d should be %d";
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		int[] data = {0, 10, 20, 30, 40};
		
		// ONE ELEMENT ONLY
		a.add(data[0]);
		assertEquals(data[0], a.getNodeAtIndex(0).getData(), String.format(message, 0, 0));
		String message2 = "Next should point to self on 1 element list";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Prev should point to self on 1 element list";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getPrev(), message2);
		
		// SECOND ELEMENT
		a.add(data[1]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		assertEquals(data[1], a.getNodeAtIndex(1).getData(), String.format(message, 1, 10));
		
		// THIRD ELEMENT
		a.add(data[2]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		assertEquals(data[2], a.getNodeAtIndex(2).getData(), String.format(message, 2, 20));
		
		// Fourth Element
		a.add(data[3]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		assertEquals(data[3], a.getNodeAtIndex(3).getData(), String.format(message, 3, 30));
		
		// Fifth Element
		a.add(data[4]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		assertEquals(data[4], a.getNodeAtIndex(4).getData(), String.format(message, 4, 40));
		
		// Sanity check all data
		for (int i = 0; i < 5; i++) {
			assertEquals(data[i], a.getNodeAtIndex(i).getData(), String.format(message, i, data[i]));
		}
		
		// NULL CHECKS
		
		a = new AmusingLinkedList<Integer>();
		a.add(0);
		a.add(null);
		assertEquals(0, a.getNodeAtIndex(0).getData(), String.format(message, 0, 0));
		assertNull(a.getNodeAtIndex(1).getData(), String.format(message, 1, 0));
	}

	@Test
	void testAddIntE() {
		String message = "Value at index %d should be %d";
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		int[] data = {0, 10, 20, 30, 40};
		
		// ONE ELEMENT ONLY
		a.add(data[1]);
		assertEquals(data[1], a.getNodeAtIndex(0).getData(), String.format(message, 0, 10));
		String message2 = "Next should point to self on 1 element list";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Prev should point to self on 1 element list";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getPrev(), message2);
		
		// SECOND ELEMENT
		a.add(data[2]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		assertEquals(data[2], a.getNodeAtIndex(1).getData(), String.format(message, 1, 20));
		
		// THIRD ELEMENT
		a.add(data[4]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		assertEquals(data[4], a.getNodeAtIndex(2).getData(), String.format(message, 2, 40));
		
		// Fourth Element
		a.add(0, data[0]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		assertEquals(data[0], a.getNodeAtIndex(0).getData(), String.format(message, 0, 0));
		
		// Fifth Element
		a.add(3, data[3]);
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		assertEquals(data[3], a.getNodeAtIndex(3).getData(), String.format(message, 3, 30));
		
		// Sanity check all data
		for (int i = 0; i < 5; i++) {
			assertEquals(data[i], a.getNodeAtIndex(i).getData(), String.format(message, i, data[i]));
		}
		
		// NULL CHECKS
		
		a = new AmusingLinkedList<Integer>();
		a.add(0);
		a.add(0, null);
		assertNull(a.getNodeAtIndex(0).getData(), "Element 0 should be null");
		assertEquals(0, a.getNodeAtIndex(1).getData(), "Element 1 should be 0");
		
		// Exception check
		AmusingLinkedList<Integer> b = new AmusingLinkedList<Integer>();
		b.add(10);
		assertThrows(IndexOutOfBoundsException.class, () -> {b.add(-1, 10);});
		assertThrows(IndexOutOfBoundsException.class, () -> {b.add(2, 10);});
	}

	@Test
	void testGetNodeAtIndex() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		String message;
		
		a.add(0);
		a.add(10);
		
		AmusingLinkedList<Integer>.Node head = a.getNodeAtIndex(0);
		
		message = "Node 0 should be 0";
		assertEquals(0, head.getData(), message);
		message = "head.next should be node at index 1";
		assertSame(head.getNext(), a.getNodeAtIndex(1), message);
	}

	@Test
	void testAddAllCollectionOfQextendsE() {
		String message = "Value at index %d should be %d";
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		Integer[] data = {0, 10, 20, 30, 40};
		
		assertTrue(a.addAll(Arrays.asList(data)));
		
		// Sanity check all data
		for (int i = 0; i < 5; i++) {
			assertEquals(data[i], a.getNodeAtIndex(i).getData(), String.format(message, i, data[i]));
		}
		
		String message2;
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		
		Integer[] dataB = {};
		assertFalse(a.addAll(Arrays.asList(dataB)));
	}

	@Test
	void testAddAllIntCollectionOfQextendsE() {
		String message = "Value at index %d should be %d";
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		Integer[] data = {0, 10, 20, 30, 40};
		
		assertTrue(a.addAll(0, Arrays.asList(data)));
		
		// Sanity check all data
		for (int i = 0; i < 5; i++) {
			assertEquals(data[i], a.getNodeAtIndex(i).getData(), String.format(message, i, data[i]));
		}
		
		String message2;
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		
		Integer[] dataB = {50, 60, 70, 80, 90};
		assertTrue(a.addAll(0, Arrays.asList(dataB)));
		
		// Sanity check all data
		int i = 0;
		assertEquals(50, a.getNodeAtIndex(i).getData(), String.format(message, i++, 50));
		assertEquals(60, a.getNodeAtIndex(i).getData(), String.format(message, i++, 60));
		assertEquals(70, a.getNodeAtIndex(i).getData(), String.format(message, i++, 70));
		assertEquals(80, a.getNodeAtIndex(i).getData(), String.format(message, i++, 80));
		assertEquals(90, a.getNodeAtIndex(i).getData(), String.format(message, i++, 90));
		assertEquals(00, a.getNodeAtIndex(i).getData(), String.format(message, i++, 00));
		assertEquals(10, a.getNodeAtIndex(i).getData(), String.format(message, i++, 10));
		assertEquals(20, a.getNodeAtIndex(i).getData(), String.format(message, i++, 20));
		assertEquals(30, a.getNodeAtIndex(i).getData(), String.format(message, i++, 30));
		assertEquals(40, a.getNodeAtIndex(i).getData(), String.format(message, i++, 40));
		
		
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 8";
		assertSame(a.getNodeAtIndex(8), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 5";
		assertSame(a.getNodeAtIndex(5), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		message2 = "Elem 5->next should point to elem 6";
		assertSame(a.getNodeAtIndex(6), a.getNodeAtIndex(5).getNext(), message2);
		message2 = "Elem 5->prev should point to null";
		assertNull(a.getNodeAtIndex(5).getPrev(), message2);
		message2 = "Elem 6->next should point to elem 7";
		assertSame(a.getNodeAtIndex(7), a.getNodeAtIndex(6).getNext(), message2);
		message2 = "Elem 6->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(6).getPrev(), message2);
		message2 = "Elem 8->prev should point to elem 6";
		assertSame(a.getNodeAtIndex(6), a.getNodeAtIndex(8).getPrev(), message2);
		message2 = "Elem 9->prev should point to null";
		assertNull(a.getNodeAtIndex(9).getPrev(), message2);
		
		Integer[] dataC = {};
		assertFalse(a.addAll(1, Arrays.asList(dataC)));
		
		// Exeption checks
		AmusingLinkedList<Integer> b = new AmusingLinkedList<Integer>();
	    assertThrows(IndexOutOfBoundsException.class, () -> {b.addAll(1, Arrays.asList(data));});
	    assertThrows(IndexOutOfBoundsException.class, () -> {b.addAll(-1, Arrays.asList(data));});
	    b.add(1);
	    assertThrows(IndexOutOfBoundsException.class, () -> {b.addAll(2, Arrays.asList(data));});
	    assertTrue(b.addAll(1, Arrays.asList(data)), "Adding at size should append new values");
	}

	@Test
	void testClear() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		String message;
		
		a.add(10);
		a.add(20);
		a.clear();
		
		message = "Size after clear should be 0";
		assertEquals(0, a.size(), message);
	}

	@Test
	void testContains() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		assertFalse(a.contains(50), "Should work with empty list");
		a.add(50);
		assertTrue(a.contains(50), "Should work with a matching element");
		assertFalse(a.contains(null), "Should work with nulls when null not present");
		a.add(null);
		assertTrue(a.contains(null), "Should work with null when null is present");
		
	}

	@Test
	void testContainsAll() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		Integer[] dataA = {0, 10, null};		// Should pass
		Integer[] dataB = {0, 10, null, 50};	// Should fail
		
		a.add(0);
		a.add(10);
		a.add(20);
		a.add(30);
		a.add(40);
		a.add(null);
		
		assertTrue(a.containsAll(Arrays.asList(dataA)));
		assertFalse(a.containsAll(Arrays.asList(dataB)));
		
	}

	@Test
	void testGet() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		String message = "Value at index %d = %d";
		a.add(0);
		a.add(10);
		a.add(20);
		
		assertEquals(0, a.get(0), String.format(message, 0, 0));
		assertEquals(10, a.get(1), String.format(message, 1, 10));
		assertEquals(20, a.get(2), String.format(message, 2, 20));
	}

	@Test
	void testIndexOf() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		a.add(1);
		a.add(2);
		a.add(1);
		
		assertEquals(0, a.indexOf(1));
		assertEquals(1, a.indexOf(2));
		a.add(null);
		a.add(null);
		assertEquals(3, a.indexOf(null));
		assertEquals(-1, a.indexOf(0));
		
		
	}

	@Test
	void testIsEmpty() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		assertTrue(a.isEmpty());
		a.add(10);
		assertFalse(a.isEmpty());
		a.clear();
		assertTrue(a.isEmpty());
		a.add(null);
		assertFalse(a.isEmpty());
	}

	@Test
	void testIterator() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		a.add(10);
		a.add(20);
		a.add(30);
		
		Iterator<Integer> i = a.iterator();
		
		assertTrue(i.hasNext());
		assertEquals(10, i.next());
		assertTrue(i.hasNext());
		assertEquals(20, i.next());
		assertTrue(i.hasNext());
		assertEquals(30, i.next());
		assertFalse(i.hasNext());
		assertThrows(NoSuchElementException.class, () -> {i.next();});
	}

	@Test
	void testLastIndexOf() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		a.add(1);
		a.add(2);
		a.add(1);
		
		assertEquals(2, a.lastIndexOf(1));
		assertEquals(1, a.lastIndexOf(2));
		a.add(null);
		a.add(null);
		assertEquals(4, a.lastIndexOf(null));
		assertEquals(-1, a.lastIndexOf(0));
	}

	@Test
	void testListIterator() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		ListIterator<Integer> li;
		
		a.add(10);
		a.add(20);
		a.add(30);
		
		li = a.listIterator();
		
		assertTrue(li.hasNext());
		assertEquals(10, li.next());
		assertTrue(li.hasNext());
		assertEquals(20, li.next());
		assertTrue(li.hasNext());
		assertEquals(30, li.next());
		assertFalse(li.hasNext());
		assertThrows(NoSuchElementException.class, () -> {li.next();});
		assertTrue(li.hasPrevious());
		assertEquals(30, li.previous());
		assertTrue(li.hasPrevious());
		assertEquals(20, li.previous());
		assertTrue(li.hasPrevious());
		assertEquals(10, li.previous());
		assertFalse(li.hasPrevious());
		assertThrows(NoSuchElementException.class, () -> {li.previous();});
		
		ListIterator<Integer> liB = a.listIterator();
		
		assertEquals(0, liB.nextIndex());
		assertEquals(-1, liB.previousIndex());
		assertThrows(IllegalStateException.class, () -> {liB.set(10);});
		assertThrows(IllegalStateException.class, () -> {liB.remove();});
		liB.add(0);
		assertEquals(4, a.size());
		assertEquals(0, a.get(0));
		assertEquals(10, liB.next());
		liB.remove();
		assertEquals(3, a.size());
		assertEquals(0, a.get(0));
		assertThrows(IllegalStateException.class, () -> {liB.set(10);});
		assertThrows(IllegalStateException.class, () -> {liB.remove();});
		assertEquals(20, liB.next());
		liB.set(50);
		assertEquals(50, a.get(1));
		liB.set(60);
		assertEquals(60, a.get(1));
	}

	@Test
	void testListIteratorInt() {
		// Assuming that if regular list iterator works, then a list iterator at a new index will also work.
		// Therefore, we will just smoke test the basic operations to ensure the positioning is correct
		
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		ListIterator<Integer> li;
		
		a.add(0);
		a.add(10);
		a.add(20);
		a.add(30);
		a.add(40);
		
		li = a.listIterator(2);
		assertEquals(2, li.nextIndex());
		assertEquals(1, li.previousIndex());
		assertTrue(li.hasNext());
		assertEquals(20, li.next());
		assertTrue(li.hasPrevious());
		assertEquals(20, li.previous());
		
		li = a.listIterator(5);
		assertFalse(li.hasNext());
		assertTrue(li.hasPrevious());
		assertEquals(40, li.previous());
		assertThrows(IndexOutOfBoundsException.class, () -> {a.listIterator(6);});
		
		
	}

	@Test
	void testRemoveObject() {
AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		a.add(0);
		a.add(10);
		a.add(20);
		a.add(null);
		a.add(40);
		
		assertEquals(5, a.size());
		AmusingLinkedList<Integer>.Node n = a.getNodeAtIndex(1);
		assertEquals(10, n.getData());
		assertTrue(a.remove(Integer.valueOf(10)));
		assertFalse(a.contains(10));
		assertEquals(20, a.getNodeAtIndex(1).getData());
		assertEquals(4, a.size());
		
		String message2;
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		
		assertTrue(a.remove(null));
		assertEquals(3, a.size());
		
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		
		assertFalse(a.remove(Integer.valueOf(-1)));
		
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		
		assertEquals(0, a.get(0));
		assertEquals(20, a.get(1));
		assertEquals(40, a.get(2));
		
	}

	@Test
	void testRemoveInt() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		
		// This just makes sure everything was added correctly
		String message2;
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		
		a.remove(0);
		assertEquals(4, a.size());
		assertEquals(1, a.getNodeAtIndex(0).getData());
		assertEquals(2, a.getNodeAtIndex(1).getData());
		assertEquals(3, a.getNodeAtIndex(2).getData());
		assertEquals(4, a.getNodeAtIndex(3).getData());
		
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		
		
		AmusingLinkedList<Integer> b = new AmusingLinkedList<Integer>();
		assertThrows(IndexOutOfBoundsException.class, () -> {b.remove(0);});
		b.add(10);
		assertThrows(IndexOutOfBoundsException.class, () -> {b.remove(-1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {b.remove(1);});
	}

	@Test
	void testRemoveAll() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		Integer[] data = {0};
		
		
		a.add(0);
		a.add(0);
		a.add(0);
		a.add(0);
		a.add(0);
		
		a.removeAll(Arrays.asList(data));
		assertEquals(0, a.size());
		
		
		Integer[] remove = {1, 13, 14, 15, null, 9};
		a.add(1);
		a.add(1);
		a.add(13);
		a.add(14);
		a.add(15);
		a.add(14);
		a.add(13);
		a.add(0);
		a.add(10);
		a.add(13);
		a.add(14);
		a.add(20);
		a.add(15);
		a.add(30);
		a.add(40);
		
		a.removeAll(Arrays.asList(remove));
		
		assertEquals(00, a.getNodeAtIndex(0).getData());
		assertEquals(10, a.getNodeAtIndex(1).getData());
		assertEquals(20, a.getNodeAtIndex(2).getData());
		assertEquals(30, a.getNodeAtIndex(3).getData());
		assertEquals(40, a.getNodeAtIndex(4).getData());
		assertEquals(5, a.size());
		
		String message2;
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		
	}

	@Test
	void testRetainAll() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		Integer[] retain = {20, 30};
		
		a.add(10);
		a.add(10);
		a.add(10);
		a.add(20);
		a.add(30);
		a.add(40);
		a.add(null);
		a.add(40);
		
		assertEquals(8, a.size());
		assertTrue(a.retainAll(Arrays.asList(retain)));
		assertEquals(20, a.get(0));
		assertEquals(30, a.get(1));
		assertEquals(2, a.size());
		
	}

	@Test
	void testSet() {
		String message = "Value at index %d should be %d";
		String message2;
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		int[] data = {0, 10, 20, 30, 40};
		
		a.add(-1);
		a.add(-2);
		a.add(-3);
		a.add(-4);
		a.add(-5);
		
		
		a.set(0, data[0]);
		assertEquals(0, a.getNodeAtIndex(0).getData(), String.format(message, 0, 0));
		a.set(1, data[1]);
		assertEquals(10, a.getNodeAtIndex(1).getData(), String.format(message, 1, 10));
		a.set(2, data[2]);
		assertEquals(20, a.getNodeAtIndex(2).getData(), String.format(message, 2, 20));
		a.set(3, data[3]);
		assertEquals(30, a.getNodeAtIndex(3).getData(), String.format(message, 3, 30));
		a.set(4, data[4]);
		assertEquals(40, a.getNodeAtIndex(4).getData(), String.format(message, 4, 40));
		
		message2 = "Elem 0->next should point to elem 1";
		assertSame(a.getNodeAtIndex(1), a.getNodeAtIndex(0).getNext(), message2);
		message2 = "Elem 0->prev should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(0).getPrev(), message2);
		message2 = "Elem 1->next should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(1).getNext(), message2);
		message2 = "Elem 1->prev should point to null";
		assertNull(a.getNodeAtIndex(1).getPrev(), message2);
		message2 = "Elem 2->next should point to elem 3";
		assertSame(a.getNodeAtIndex(3), a.getNodeAtIndex(2).getNext(), message2);
		message2 = "Elem 2->prev should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(2).getPrev(), message2);
		message2 = "Elem 3->next should point to elem 4";
		assertSame(a.getNodeAtIndex(4), a.getNodeAtIndex(3).getNext(), message2);
		message2 = "Elem 3->prev should point to null";
		assertNull(a.getNodeAtIndex(3).getPrev(), message2);
		message2 = "Elem 4->next should point to elem 0";
		assertSame(a.getNodeAtIndex(0), a.getNodeAtIndex(4).getNext(), message2);
		message2 = "Elem 4->prev should point to elem 2";
		assertSame(a.getNodeAtIndex(2), a.getNodeAtIndex(4).getPrev(), message2);
		assertEquals(data[4], a.getNodeAtIndex(4).getData(), String.format(message, 4, 40));
		
		// Sanity check all data
		for (int i = 0; i < 5; i++) {
			assertEquals(data[i], a.getNodeAtIndex(i).getData(), String.format(message, i, data[i]));
		}
		
		// NULL CHECKS
		
		a = new AmusingLinkedList<Integer>();
		a.add(0);
		a.add(null);
		a.add(3);
		a.set(2, null);
		assertEquals(0, a.getNodeAtIndex(0).getData(), String.format(message, 0, 0));
		assertNull(a.getNodeAtIndex(1).getData(), String.format(message, 1, 0));
		assertNull(a.getNodeAtIndex(2).getData(), String.format(message, 2, 0));
		
		// Exeption checks
		AmusingLinkedList<Integer> b = new AmusingLinkedList<Integer>();
	    assertThrows(IndexOutOfBoundsException.class, () -> {b.set(0, 0);});
	    assertThrows(IndexOutOfBoundsException.class, () -> {b.set(-1, 0);});
	    b.add(1);
	    assertThrows(IndexOutOfBoundsException.class, () -> {b.set(1, 0);});
	}

	@Test
	void testSize() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		Integer[] addAll = {20, 30, 40};
		
		assertEquals(0, a.size());
		a.add(null);
		assertEquals(1, a.size());
		a.add(1);
		assertEquals(2, a.size());
		a.remove(0);
		assertEquals(1, a.size());
		a.remove(Integer.valueOf(1));
		assertEquals(0, a.size());
		a.add(0, 10);
		assertEquals(1, a.size());
		a.addAll(Arrays.asList(addAll));
		assertEquals(4, a.size());
		a.addAll(0, Arrays.asList(addAll));
		assertEquals(7, a.size());
		a.retainAll(Arrays.asList(addAll));
		assertEquals(6, a.size());
		a.removeAll(Arrays.asList(addAll));
		assertEquals(0, a.size());
	}

	@Test
	void testSubList() {
		// Just testing whether we can create a subList here... to test the sublists functionality, see SubListTests.java
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		assertNotNull(a.subList(0,0));	// Should be able to create a sublist on an empty list
		
		a.add(0);
		a.add(10);
		a.add(20);
		a.add(30);
		a.add(40);
		a.add(50);
		
		assertNotNull(a.subList(0, 6));
		assertNotNull(a.subList(5, 5));
		
		assertThrows(IndexOutOfBoundsException.class, () -> {a.subList(-1, 5);});
		assertThrows(IndexOutOfBoundsException.class, () -> {a.subList(0, 7);});
		assertThrows(IndexOutOfBoundsException.class, () -> {a.subList(5, 4);});
		
	}
	
	@Test
	void testToArray() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		Integer[] expected = {0, 10, 20, 30, 40};
		Object[] out;
		
		a.add(0);
		a.add(10);
		a.add(20);
		a.add(30);
		a.add(40);
		
		out = a.toArray();
		assertArrayEquals(expected, out);
		a.remove(0);
		assertArrayEquals(expected, out);
	}

	@Test
	void testToArrayTArray() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		Integer[] out = {};
		assertArrayEquals(out, a.toArray());
		
		a.add(1);
		a.add(2);
		a.add(3);
		
		out = new Integer[3];
		a.toArray(out);
		Integer[] expected = {1, 2, 3};
		assertArrayEquals(expected, out);
		a.remove(2);
		assertArrayEquals(expected, out);
		a.add(3);
		out = new Integer[1];
		out = a.toArray(out);
		assertArrayEquals(expected, out);
		Integer[] expectedB = {1, 2, 3, null, null, null, null, null, null, null};
		out = new Integer[10];
		Integer[] outB = a.toArray(out);
		assertArrayEquals(expectedB, out);
		assertArrayEquals(expectedB, outB);
		
	}

	@Test
	void testToString() {
		AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
		
		String expected = "0 6 1 0\n"
						+ "1 -1 2 1\n"
						+ "2 0 3 2\n"
						+ "3 -1 4 3\n"
						+ "4 2 5 4\n"
						+ "5 -1 6 5\n"
						+ "6 4 7 6\n"
						+ "7 -1 0 null";
		
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(null);
		
		
		assertEquals(expected, a.toString());
		
		expected = "0 8 1 0\n"
						+ "1 -1 2 1\n"
						+ "2 0 3 2\n"
						+ "3 -1 4 3\n"
						+ "4 2 5 4\n"
						+ "5 -1 6 5\n"
						+ "6 4 7 6\n"
						+ "7 -1 8 null\n"
						+ "8 6 0 null";

		a.add(null);
		
		
		assertEquals(expected, a.toString());
		
	}

}
