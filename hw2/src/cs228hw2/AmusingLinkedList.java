package cs228hw2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The implementation for a linkedlist that always has a next pointer, but only
 * has previous pointers for all even indexed nodes (starting indexed at zero).
 * All odd nodes have previous pointers that are null. The linked list is also
 * looped at the end.
 * 
 * @author Kylus Pettit-Ehler
 *
 * @param <E> The generic argument / data to be stored in this
 *            AmusingLinkedList.
 */
public class AmusingLinkedList<E> implements List<E> {
	/**
	 * The class that is used to store pointers to other nodes and store data within
	 * nodes.
	 * 
	 * @author Kylus Pettit-Ehler
	 *
	 */
	public class Node {
		/**
		 * Used to store some kind of object or data in the Node.
		 */
		private E data;
		/**
		 * Used to store the next Node that this node is pointing to.
		 */
		private Node next;
		/**
		 * Used to store the previous Node that this node is pointing to.
		 */
		private Node prev;

		/**
		 * Constructor for a Node object.
		 * 
		 * @param data The data to be stored in this node.
		 * @param next The next node that is being pointed at by this Node.
		 */
		Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Return the data from a specific Node.
		 * 
		 * @return The data instance for this node.
		 */
		public E getData() {
			return data;
		}

		/**
		 * Return what next Node the current Node is pointing at.
		 * 
		 * @return The next Node that the current Node is pointing at.
		 */
		public Node getNext() {
			return next;
		}

		/**
		 * Return what previous Node the current Node is pointing at.
		 * 
		 * @return The previous Node that the current Node is pointing at.
		 */
		public Node getPrev() {
			return prev;
		}
	}

	/**
	 * The class that is used to iterate through the specific implementation of
	 * linkedlist that is "AmusingLinkedList."
	 * 
	 * @author Kylus Pettit-Ehler
	 *
	 */
	public class AmusingListIterator implements ListIterator<E> {
		/**
		 * used to track the position of the iterator.
		 */
		private int pos;
		/**
		 * Used to track the last index accessed by previous.
		 */
		private int lastPrevious;
		/**
		 * Used to track the last index accessed by next.
		 */
		private int lastNext;
		/**
		 * Used to track whether the last method call was add.
		 */
		private boolean lastCallAdd = false;
		/**
		 * Used to track whether the last method call was previous.
		 */
		private boolean lastCallPrev = false;
		/**
		 * Used to track whether the last method call was next.
		 */
		private boolean lastCallNext = false;
		/**
		 * Used to track whether the last method call was remove.
		 */
		private boolean lastCallRemove = false;

		/**
		 * The constructor for an AmusingListIterator object. This constructor will
		 * start at index -1.
		 */
		public AmusingListIterator() {
			pos = -1;
		}

		/**
		 * The constructor for an AmusingListIterator object at a specific position.
		 * 
		 * @param index The index that the user wishes to start at in the
		 *              AmusingLinkedList.
		 */
		public AmusingListIterator(int index) {
			if (index > size) {
				throw new IndexOutOfBoundsException();
			} else {
				pos = index - 1;
			}
		}

		/**
		 * Add this data at the current position.
		 */
		@Override
		public void add(E data) {
			setLastCallAdd(true);
			pos++;
			AmusingLinkedList.this.add(pos, data);
		}

		/**
		 * Determine if the iterator has a next element at the current position.
		 */
		@Override
		public boolean hasNext() {
			if (pos + 1 >= size) {
				return false;
			}
			return true;
		}

		/**
		 * Determine if the iterator has a previous element at the current position.
		 */
		@Override
		public boolean hasPrevious() {
			if (pos < 0) {
				return false;
			}
			return true;
		}

		/**
		 * Iterate through the list; proceed to the next element if the iterator has a
		 * next element.
		 */
		@Override
		public E next() {
			if (hasNext()) {
				lastCallAdd = false;
				lastCallRemove = false;
				lastCallNext = true;
				lastCallPrev = false;
				pos++;
				lastNext = pos;
				int index = pos % size;
				return get(index);
			} else {
				throw new NoSuchElementException();
			}
		}

		/**
		 * Return the index that would be accesed by a next call.
		 */
		@Override
		public int nextIndex() {
			if (pos + 1 >= size) {
				return size;
			}
			return pos + 1;
		}

		/**
		 * Iterate through the list; go back to the previous element if the iterator has
		 * a previous element.
		 */
		@Override
		public E previous() {
			if (hasPrevious()) {
				lastCallAdd = false;
				lastCallRemove = false;
				lastCallPrev = true;
				lastCallNext = false;
				lastCallRemove = false;
				Integer index = new Integer(pos % (size));
				pos--;
				lastPrevious = pos;
				return get(index);
			} else {
				throw new NoSuchElementException();
			}

		}

		/**
		 * Return the element that would be accessed by a previous call.
		 */
		@Override
		public int previousIndex() {
			if (pos == 0) {
				return -1;
			}
			return pos;
		}

		/**
		 * Remove the element that was last accessed by previous or next. If the last
		 * call as add or remove is called twice in a row, an exception is thrown. If
		 * neither previous or next have been called, an exception will also be thrown.
		 * This method can only modify things the list has iterated over.
		 */
		@Override
		public void remove() {
			if (!lastCallAdd && !lastCallRemove) {
				if (lastCallPrev) {
					lastCallRemove = true;
					AmusingLinkedList.this.remove(lastPrevious);
				} else if (lastCallNext) {
					lastCallRemove = true;
					AmusingLinkedList.this.remove(lastNext);
				} else {
					throw new IllegalStateException();
				}
			} else {
				throw new IllegalStateException();
			}
			pos--;
		}

		/**
		 * Set the element that was last accessed by previous or next. If the last call
		 * was add or remove, an exception is thrown. If neither next have been called,
		 * an exception will also be thrown. This method can only set things that have
		 * been iterated over.
		 */
		@Override
		public void set(E arg0) {
			// As long as the position desired is within the bounds of the list, add the
			// element.
			if (pos > -1) {
				AmusingLinkedList.this.set(pos, arg0);
			} else {
				throw new IllegalStateException();
			}
			if (!lastCallAdd && !lastCallRemove) {
				if (lastCallPrev) {
					AmusingLinkedList.this.set(lastPrevious, arg0);
				} else if (lastCallNext) {
					AmusingLinkedList.this.set(lastNext, arg0);
				} else {
					throw new IllegalStateException();
				}
			} else {
				throw new IllegalStateException();
			}
		}

		/**
		 * Get the current position of the iterator.
		 * 
		 * @return
		 */
		public int getPos() {
			return pos;
		}

		/**
		 * Increment the current position of the iterator.
		 */
		public void incPos() {
			pos++;
		}

		/**
		 * Decrement the current position of the iterator.
		 */
		public void decPos() {
			pos--;
		}

		/**
		 * Determine if the last call was previous.
		 * 
		 * @return if the last call was previous.
		 */
		public boolean isLastCallPrev() {
			return lastCallPrev;
		}

		/**
		 * Set whether the last call was previous.
		 * 
		 * @param lastCallPrev whether the last call was previous.
		 */
		public void setLastCallPrev(boolean lastCallPrev) {
			this.lastCallPrev = lastCallPrev;
		}

		/**
		 * Return if the last call was next.
		 * 
		 * @return if the last call was next.
		 */
		public boolean isLastCallNext() {
			return lastCallNext;
		}

		/**
		 * See whether the last call was next.
		 * 
		 * @param lastCallNext whether the last call was next.
		 */
		public void setLastCallNext(boolean lastCallNext) {
			this.lastCallNext = lastCallNext;
		}

		/**
		 * Get the last previous index.
		 * 
		 * @return The last index accessed by previous.
		 */
		public int getLastPrevious() {
			return lastPrevious;
		}

		/**
		 * Set the index last accessed by previous.
		 * 
		 * @param lastPrevious The index last accessed by previous.
		 */
		public void setLastPrevious(int lastPrevious) {
			this.lastPrevious = lastPrevious;
		}

		/**
		 * Determine if the last call was add.
		 * 
		 * @return Whether that last call was add.
		 */
		public boolean isLastCallAdd() {
			return lastCallAdd;
		}

		/**
		 * Set whether the last call was add.
		 * 
		 * @param lastCallAdd whether the last call was add.
		 */
		public void setLastCallAdd(boolean lastCallAdd) {
			this.lastCallAdd = lastCallAdd;
		}

		/**
		 * Get the last index accessed by next.
		 * 
		 * @return the last index accessed by next.
		 */
		public int getLastNext() {
			return lastNext;
		}

		/**
		 * Set the last index accessed by next.
		 * 
		 * @param lastNext The last index accessed by next.
		 */
		public void setLastNext(int lastNext) {
			this.lastNext = lastNext;
		}
	}

	/**
	 * Class made to be returned to the Deque228 class when a reverse iterator is
	 * requested.
	 * 
	 * @author Kylus Pettit-Ehler
	 *
	 */
	public class ReverseAmusingListIterator extends AmusingListIterator {

		public ReverseAmusingListIterator() {
			super(size);
		}

		@Override
		public boolean hasNext() {
			if (getPos() < 0) {
				return false;
			}
			return true;
		}

		@Override
		public boolean hasPrevious() {
			if (getPos() + 1 >= size) {
				return false;
			}
			return true;
		}

		@Override
		public int nextIndex() {
			if (getPos() + 1 >= size) {
				return size;
			}
			return getPos() + 1;
		}

		@Override
		public E previous() {
			if (hasNext()) {
				setLastCallAdd(false);
				setLastCallNext(true);
				setLastCallPrev(false);
				incPos();
				setLastNext(getPos());
				int index = getPos() % size;
				return getNodeAtIndex(index).data;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public E next() {
			if (hasPrevious()) {
				setLastCallAdd(false);
				setLastCallPrev(true);
				setLastCallNext(false);
				Integer index = new Integer(getPos() % (size));
				decPos();
				setLastPrevious(getPos());
				return getNodeAtIndex(index).data;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public int previousIndex() {
			if (getPos() == 0) {
				return -1;
			}
			return getPos();
		}

	}

	/**
	 * The Node head points at the first index in the list.
	 */
	private Node head;
	/**
	 * The Node tail points at the last index in the list.
	 */
	private Node tail;
	/**
	 * The Node prevPointer points at the last even indexed node in the list.
	 */
	private Node prevPointer;

	/**
	 * Used to store the size of AmusingLinkedList.
	 */
	private int size;

	/**
	 * Constructor for an AmusingLinkedList object. Head, tail, iter, and
	 * prevPointer are initialized to null, and size starts out as 0.
	 */
	public AmusingLinkedList() {
		head = null;
		tail = null;
		size = 0;
		prevPointer = null;
	}

	/**
	 * Return the current state of the AmusingLinkedList in the form of a string.
	 */
	@Override
	public String toString() {
		String listInfo = "";
		boolean firstIter = true;
		for (int i = 0; i < size; i++) {
			if (!firstIter) {
				listInfo += "\n";
			}
			firstIter = false;
			listInfo += i + " ";
			if (i == 0) {
				if (size % 2 == 0) {
					listInfo += size - 2 + " ";
				} else {
					listInfo += (size - 1) + " ";
				}
			} else {
				if (isEvenIndexNode(getNodeAtIndex(i))) {
					listInfo += i - 2 + " ";
				} else {
					listInfo += -1 + " ";
				}
			}

			listInfo += (i + 1) % size + " ";
			listInfo += get(i);
		}
		return listInfo;
	}

	/**
	 * Return the Node at the given index.
	 * 
	 * @param index The index of the Node that shall be returned.
	 * @return The Node at the index given as an argument.
	 */
	public Node getNodeAtIndex(int index) {
		Node cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur;
	}

	/**
	 * Add this item to the end of the list and reassign all pointers.
	 */
	@Override
	public boolean add(E data) {
		if (head == null) {
			head = new Node(data, null);
			tail = head;
			tail.next = head;
			size++;
			prevPointer = head;
			head.prev = tail;
			return true;
		}
		tail.next = new Node(data, null);
		tail = tail.next;
		size++;
		if ((size - 1) % 2 == 0) {
			tail.prev = prevPointer;
			prevPointer = tail;
			tail.next = head;
		} else {
			tail.next = head;
			tail.prev = null;
		}
		head.prev = prevPointer;
		return true;
	}

	/**
	 * Add this item at the specified index and reassign all pointers. If the user
	 * tries to add at an invalid index, an exception is thrown.
	 */
	@Override
	public void add(int index, E element) {
		int position;
		Node cur = head;
		Node prev = null;
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			if (index <= size) {
				if ((index == size) || (index == 0) && (size == 0)) {
					add(element);
				} else {
					prevPointer = head;
					for (position = 0; position < index; position++) {
						if ((position % 2) == 0) {
							prevPointer = cur;
						}
						prev = cur;
						cur = cur.next;
					}
					if (position == 0) {
						Node tempHead = head;
						head = new Node(element, null);
						head.next = tempHead;
						cur = head;
						prev = tail;
						head.prev = prev;
					}
					Node temp = cur;
					Node toAdd = new Node(element, temp);
					prev.next = toAdd;
					size++;
					if (position % 2 == 0) {
						toAdd.prev = prevPointer;
						prevPointer = toAdd;
					} else {
						toAdd.prev = null;
					}
					cur = toAdd.next;
					if (position == 0) {
						cur = cur.next;
					}
					if (position % 2 == 0) {
						int place = position + 1;
						while (place < size) {
							cur.prev = null;
							place++;
							if (place >= size) {
								break;
							}
							cur = cur.next;
							cur.prev = prevPointer;
							prevPointer = cur;
							cur = cur.next;
							place++;
						}
					} else {
						int place = position + 1;
						while (place < size) {
							cur.prev = prevPointer;
							prevPointer = cur;
							cur = cur.next;
							place++;
							if (place >= size) {
								break;
							}
							cur.prev = null;
							cur = cur.next;
							place++;
						}
					}
				}
				head.prev = prevPointer;
			}
		}

	}

	/**
	 * Add all of the elements of this collection to the AmusingLinkedList.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (c.isEmpty()) {
			return false;
		}
		for (E e : c) {
			add(e);
		}
		return true;
	}

	/**
	 * Add all of the elements of this collection to the AmusingLinkedList, starting
	 * at a certain index.
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int i = index;
		if (c.isEmpty()) {
			return false;
		}
		for (E e : c) {
			add(i, e);
			i++;
		}
		return true;
	}

	/**
	 * Clear the AmusingLinkedList of its contents.
	 */
	@Override
	public void clear() {
		size = 0;
		head = null;
	}

	/**
	 * Check if the AmusingLinkedList contains this object.
	 */
	@Override
	public boolean contains(Object o) {
		Node cur = head;
		for (int i = 0; i < size; i++) {
			if (o == null || cur.data == null) {
				if (cur.data == o) {
					return true;
				}
			} else {
				if (cur.data.equals(o)) {
					return true;
				}
			}
			cur = cur.next;
		}
		return false;
	}

	/**
	 * Check if the AmusingLinkedList contains all of the elements in this
	 * collection.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		int numberItems = c.size();
		int numberMatches = 0;
		for (Object e : c) {
			if (contains(e)) {
				numberMatches++;
			}
		}
		return numberMatches == numberItems;
	}

	/**
	 * Get the data from a specific node index.
	 */
	@Override
	public E get(int index) {
		Node cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.data;
	}

	/**
	 * Find the index of a certain object within AmusingLinkedList. Return -1 if it
	 * doesn't exist.
	 */
	@Override
	public int indexOf(Object o) {
		if (!contains(o)) {
			return -1;
		}
		Node cur = head;
		int i;
		for (i = 0; i < size; i++) {
			if (o == null || cur.data == null) {
				if (cur.data == o) {
					break;
				}
			} else {
				if (cur.data.equals(o)) {
					break;
				}
			}
			cur = cur.next;
		}
		return i;
	}

	/**
	 * Return if this list is empty.
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Return an iterator over AmusingLinkedList.
	 */
	@Override
	public Iterator<E> iterator() {
		return new AmusingListIterator();
	}

	/**
	 * Return the last index of this object in AmusingLinkedList. Return -1 if it
	 * doesn't exist.
	 */
	@Override
	public int lastIndexOf(Object o) {
		if (!contains(o)) {
			return -1;
		}
		Node cur = head;
		int i, record = 0;
		for (i = 0; i < size; i++) {
			if (o == null || cur.data == null) {
				if (cur.data == o) {
					record = i;
				}
			} else {
				if (cur.data.equals(o) || cur.data == o) {
					record = i;
				}
			}
			cur = cur.next;
		}
		return record;
	}

	/**
	 * Return an iterator over AmusingLinkedList.
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new AmusingListIterator();
	}

	/**
	 * Return an iterator over AmusingLinkedList starting at a certain index.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new AmusingListIterator(index);
	}

	/**
	 * Return an iterator in reverse order over this AmusingLinkedList.
	 * 
	 * @return
	 */
	public ListIterator<E> reverseIterator() {
		return new ReverseAmusingListIterator();
	}

	/**
	 * Remove this object from AmusingLinkedList, if it isn't empty. Return whether
	 * the list was modified by this method call.
	 */
	@Override
	public boolean remove(Object o) {
		if (!isEmpty()) {
			Node cur = head;
			for (int i = 0; i < size; i++) {
				if (cur.data == null || o == null) {
					if (cur.data == o) {
						remove(i);
						return true;
					}
				} else {
					if (cur.data.equals(o)) {
						remove(i);
						return true;
					}
				}
				cur = cur.next;
			}
		}
		return false;
	}

	/**
	 * Remove this index of node from AmusingLinkedList. Throw an exception if the
	 * used provides an invalid index to remove. Reassign all pointers accordingly.
	 */
	@Override
	public E remove(int index) {
		Node cur = head;
		Node last = null;
		int position;
		E toRemove = null;
		if (index < 0 || index > size - 1 || isEmpty()) {
			throw new IndexOutOfBoundsException();
		} else {
			if (size == 1) {
				toRemove = head.data;
				head = null;
				size--;
			} else {
				for (position = 0; position < index; position++) {
					last = cur;
					cur = cur.next;
				}
				if (index == 0) {
					last = tail;
					cur = head;
					head = head.next;
				}
				if (index == (size - 1)) {
					tail = last;
					head.prev = tail;
				}
				toRemove = cur.data;
				last.next = cur.next;
				size--;
				Node temp = null;
				if (position % 2 == 0) {
					temp = cur.prev;
					cur = cur.next;
					int place = position;
					while (place < size) {
						cur.prev = temp;
						temp = cur;
						cur = cur.next;
						place++;
						if (place >= size) {
							break;
						}
						cur.prev = null;
						place++;
						cur = cur.next;
					}
				} else {
					temp = last;
					cur = cur.next;
					int place = position;
					while (place < size) {
						cur.prev = null;
						cur = cur.next;
						place++;
						if (place >= size) {
							break;
						}
						cur.prev = temp;
						temp = cur;
						cur = cur.next;
						place++;
					}
				}
				head.prev = temp;
			}
			return toRemove;
		}
	}

	/**
	 * Remove all of the elements in this collection from the list. Return if
	 * AmusingLinkedList was modified by this call.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		Integer holdSize = new Integer(size);
		for (Object e : c) {
			for (int i = 0; i < holdSize; i++) {
				if (get(i) == null || e == null) {
					if (get(i) == e) {
						remove(i);
						i--;
						modified = true;
					}
				}
				if (get(i).equals(e)) {
					remove(i);
					i--;
					modified = true;
				}
				if (isEmpty()) {
					break;
				}
			}
		}
		return modified;
	}

	/**
	 * Retain all of the elements in this collection, removing all others that do
	 * not match the contents of collection.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Boolean retain = false;
		Boolean modified = false;
		Integer holdSize = new Integer(size);
		for (int i = 0; i < holdSize; i++) {
			for (Object e : c) {
				if (e == null || get(i) == null) {
					if (get(i) == e) {
						retain = true;
					}
				} else {
					if (get(i).equals(e)) {
						retain = true;
					}
				}

			}
			if (!retain) {
				remove(i);
				i--;
				modified = true;
			}
			retain = false;
		}
		return modified;
	}

	/**
	 * Set a certain indexed node to the given argument. Throw an exception if the
	 * user provides an invalid index.
	 */
	@Override
	public E set(int index, E element) {
		if ((index >= size) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		} else {
			Node cur = head;
			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}
			Node temp = cur;
			cur.data = element;
			return temp.data;
		}
	}

	/**
	 * Return the size of this list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Create a sublist of this AmusingLinkedList. Throw an exception if the user
	 * provides invalid bounds.
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if ((fromIndex < 0) || (toIndex > size) || (fromIndex > toIndex)) {
			throw new IndexOutOfBoundsException();
		} else {
			AmusingLinkedList<E> toReturn = new AmusingLinkedList<>();
			for (int i = fromIndex + 1; i < toIndex; i++) {
				toReturn.add(get(i));
			}
			return toReturn;
		}
	}

	/**
	 * Return an array representation of the data currently in AmusingLinkedList.
	 */
	@Override
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		E[] toReturn = (E[]) new Object[size];
		Node cur = head;
		for (int i = 0; i < size; i++) {
			toReturn[i] = cur.data;
			cur = cur.next;
		}
		return toReturn;
	}

	/**
	 * Put the data currently in AmusingLinkedList into the given array, make a new
	 * one if the given array is too small to fit the size of AmusingLinkedList.
	 * Assign all remaining elements to null if the given array is bigger than
	 * AmusingLinkedList.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		Node cur = head;
		int i = 0;
		if (a.length >= size) {
			while (i < size) {
				a[i] = (T) cur.data;
				cur = cur.next;
				i++;
			}
			while (i < a.length) {
				a[i] = null;
				i++;
			}
		} else {
			a = Arrays.copyOf(a, size);
			for (i = 0; i < size; i++) {
				a[i] = (T) cur.data;
				cur = cur.next;
			}
		}
		return a;
	}

	/**
	 * Method used to find and diagnose problems within the AmusingLinkedList
	 * implementation.
	 * 
	 * @return A string representing what Nodes and data are currently stored in the
	 *         AmusingLinkedList object, as well as what each node is pointing to.
	 */
	public String debugString() {
		StringBuilder ss = new StringBuilder();
		Node x = head;
		ss.append(x == null ? "null" : x.data).append(" | ");
		for (int curr_index = 0; curr_index < size; curr_index++, x = x.next) {
			ss.append(x.data).append(" (").append(isEvenIndexNode(x) ? x.prev.data : "null").append(", ")
					.append(x.next.data).append(")");
			if (curr_index != size - 1)
				ss.append(" -> ");
		}
		return ss.toString();
	}

	/**
	 * Returns if the node in question is an even indexed node.
	 * 
	 * @param x The Node to be observed.
	 * @return Whether the previous pointer of the current Node is null or not. If
	 *         the pointer is not null, this method will return true.
	 */
	private boolean isEvenIndexNode(AmusingLinkedList<E>.Node x) {
		return x.prev != null;
	}

}
