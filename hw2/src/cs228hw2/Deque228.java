package cs228hw2;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that implements the Deque interface. This interface is versatile and
 * can be used to implement many operations, such as a calculator.
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <E> The generic type of the class to add to the stack.
 */
public class Deque228<E> implements Deque<E> {

	/**
	 * The backing data structure used to implement this stack / queue.
	 */
	AmusingLinkedList<E> deque;

	/**
	 * Constructor for a Deque228 object.
	 */
	public Deque228() {
		deque = new AmusingLinkedList<>();
	}

	/**
	 * Add all of the contents of this collection to the deque.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return deque.addAll(c);
	}

	/**
	 * Clear the deque of all its contents.
	 */
	@Override
	public void clear() {
		deque.clear();
	}

	/**
	 * Check to see if the deque contains all objects in this collection.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return deque.containsAll(c);
	}

	/**
	 * Check to see if the deque is empty.
	 */
	@Override
	public boolean isEmpty() {
		return deque.isEmpty();
	}

	/**
	 * Remove all elements in this colletion from the deque.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return deque.removeAll(c);
	}

	/**
	 * Remove everything but items in this collection from the deque.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return deque.retainAll(c);
	}

	/**
	 * Return everything currently in deque in array form.
	 */
	@Override
	public Object[] toArray() {
		return deque.toArray();
	}

	/**
	 * Put everything currently in deque into the given array.
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return deque.toArray(a);
	}

	/**
	 * Add this item to the end of deque.
	 */
	@Override
	public boolean add(E e) {
		return deque.add(e);
	}

	/**
	 * Add this element to the front of deque.
	 */
	@Override
	public void addFirst(E e) {
		deque.add(0, e);
	}

	/**
	 * Add this element to the end of deque.
	 */
	@Override
	public void addLast(E e) {
		deque.add(e);
	}

	/**
	 * Check to see if this deque contains the given object.
	 */
	@Override
	public boolean contains(Object o) {
		return deque.contains(o);
	}

	/**
	 * Return a descending iterator over this collection.
	 */
	@Override
	public Iterator<E> descendingIterator() {
		return deque.reverseIterator();
	}

	/*
	 * Return the head of this deck without removing it. If deck is empty, throw an
	 * exception.
	 */
	@Override
	public E element() {
		if (deque.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return deque.get(0);
		}
	}

	/**
	 * Get the first element of this collection, do not remove it.
	 */
	@Override
	public E getFirst() {
		return deque.get(0);
	}

	/**
	 * Get the last element of this collection, do not remove it.
	 */
	@Override
	public E getLast() {
		return deque.get(deque.size() - 1);
	}

	/**
	 * Return an iterator over this collection.
	 */
	@Override
	public Iterator<E> iterator() {
		return deque.iterator();
	}

	/**
	 * Offer this item to the deque.
	 */
	@Override
	public boolean offer(E e) {
		return deque.add(e);
	}

	/**
	 * Offer this item to the deque at the first element.
	 */
	@Override
	public boolean offerFirst(E e) {
		deque.add(0, e);
		return true;
	}

	/**
	 * Offer this item to the deque at the last element.
	 */
	@Override
	public boolean offerLast(E e) {
		return deque.add(e);
	}

	/**
	 * Peek at the first element in deque, or return null if deque is empty.
	 */
	@Override
	public E peek() {
		if (deque.isEmpty()) {
			return null;
		} else {
			return deque.get(0);
		}
	}

	/**
	 * Peek at the first element in deque, or return null if deque is empty.
	 */
	@Override
	public E peekFirst() {
		return peek();
	}

	/**
	 * Peek at the last element in deque, or return null if deque is empty.
	 */
	@Override
	public E peekLast() {
		if (deque.isEmpty()) {
			return null;
		} else {
			return deque.get(deque.size() - 1);
		}
	}

	/**
	 * Return the head of this deque, and also remove the element at the head of the
	 * deque. Return null if deque is empty.
	 */
	@Override
	public E poll() {
		if (deque.isEmpty()) {
			return null;
		} else {
			E temp = deque.get(0);
			deque.remove(0);
			return temp;
		}
	}

	/**
	 * Return the head of this deque, and also remove the element at the head of the
	 * deque. Return null if deque is empty.
	 */
	@Override
	public E pollFirst() {
		return poll();
	}

	/**
	 * Return the tail of this deque, and also remove the element at the tail of the
	 * deque. Return null if deque is empty.
	 */
	@Override
	public E pollLast() {
		if (deque.isEmpty()) {
			return null;
		} else {
			E temp = deque.get(deque.size() - 1);
			deque.remove(deque.size() - 1);
			return temp;
		}
	}

	/**
	 * Pop off the head element of this deque, returning the value that was removed.
	 * Throw an exception if deque is empty.
	 */
	@Override
	public E pop() {
		if (deque.isEmpty()) {
			throw new NoSuchElementException();
		}
		E temp = deque.get(0);
		deque.remove(0);
		return temp;
	}

	/**
	 * Push an element onto the head of this deque, inserting it at index zero.
	 */
	@Override
	public void push(E e) {
		deque.add(0, e);
	}

	/**
	 * Retrieve and remove the head element of this deque. Throw an exception if
	 * deque is empty.
	 */
	@Override
	public E remove() {
		if (deque.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			E temp = deque.get(0);
			deque.remove(0);
			return temp;
		}
	}

	/**
	 * Remove the first occurrence of a specific object from the deque. Return if
	 * deque was modified by this method call.
	 */
	@Override
	public boolean remove(Object o) {
		return deque.remove(o);
	}

	/**
	 * Remove and return the first element of this deque. Throw an exception if
	 * deque is empty.
	 */
	@Override
	public E removeFirst() {
		E temp;
		if (deque.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			temp = deque.get(0);
			deque.remove(0);
		}
		return temp;
	}

	/**
	 * Remove the first occurrence of this object from the deque. Return if deque
	 * was modified by this call.
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		if (deque.indexOf(o) != -1) {
			remove(0);
			return true;
		}
		return false;
	}

	/**
	 * Remove the last element from this deque. Return if deque was modified by this
	 * method call. If deque is empty, throw an exception.
	 */
	@Override
	public E removeLast() {
		E temp;
		if (deque.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			temp = deque.get(deque.size() - 1);
			deque.remove(deque.size() - 1);
		}
		return temp;
	}

	/**
	 * Remove the last occurrence of a specific object from the deque. Return if
	 * deque was modified by this method call.
	 */
	@Override
	public boolean removeLastOccurrence(Object o) {
		if (deque.lastIndexOf(o) != -1) {
			remove(o);
			return true;
		}
		return false;
	}

	/**
	 * Return the size of this deque.
	 */
	@Override
	public int size() {
		return deque.size();
	}
}
