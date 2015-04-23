package adt.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {

	// Internal classes start
	private class Node {
		private E value;
		private Node next;
		private Node prev;

		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrev() {
			return prev;
		}
		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

	private class ListIterator implements Iterator<E> {

		private Node currentNode = header;

		public ListIterator() {
			currentNode = header.getNext();
		}

		public ListIterator(int index) {
			if (index < 0 || index >= size())
				throw new IndexOutOfBoundsException();

			int i = 0;
			for (currentNode = header.getNext(); i < index; currentNode = currentNode.getNext(), ++i);
		}

		@Override
		public boolean hasNext() {
			return currentNode != header;
		}

		@Override
		public E next() {
			if (hasNext()) {
				E result = this.currentNode.getValue();
				this.currentNode = this.currentNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class ReverseListIterator implements ReverseIterator<E> {

		private Node currentNode = header;

		public ReverseListIterator() {
			currentNode = header.getPrev();
		}

		public ReverseListIterator(int index) {
			if (index < 0 || index >= size())
				throw new IndexOutOfBoundsException();

			int i = size() - 1;
			for (currentNode = header.getPrev(); i > index; currentNode = currentNode.getPrev(), --i);
		}

		@Override
		public boolean hasPrevious() {
			return currentNode != header;
		}

		@Override
		public E previous() {
			if (hasPrevious()) {
				E result = currentNode.getValue();
				currentNode = currentNode.getPrev();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}
	// Internal classes end

	private Node header;
	private int currentSize;

	public SortedCircularDoublyLinkedList() {
		this.header = new Node();
		this.header.setNext(header);
		this.header.setPrev(header);
		this.currentSize = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}

	@Override
	public boolean add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		}

		Node temp = null;
		if (this.isEmpty()) {
			temp = header;
		}
		else {
			for (temp = header.getNext(); temp != header && temp.getValue().compareTo(obj) < 0;
					temp = temp.getNext());
		}
		Node newNode = new Node();
		newNode.setValue(obj);
		newNode.setPrev(temp.getPrev());
		temp.getPrev().setNext(newNode);
		newNode.setNext(temp);
		temp.setPrev(newNode);
		this.currentSize++;

		return true;
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		Node temp = null;
		for (temp = header.getNext(); temp != header; temp = temp.getNext()) {
			if (temp.getValue().equals(obj)) {
				// found first copy
				temp.getNext().setPrev(temp.getPrev());
				temp.getPrev().setNext(temp.getNext());
				temp.setValue(null);
				temp.setNext(null);
				temp.setPrev(null);
				this.currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}

		Node temp = null;
		// closer going forward
		if (index < this.size() / 2) {
			int counter = 0;
			for (temp = header.getNext(); counter < index; temp = temp.getNext(), counter++);
		}
		// closer going backwards
		else {
			int counter = this.size() - 1;
			for (temp = header.getPrev(); counter > index; temp = temp.getPrev(), counter--);
		}
		temp.getNext().setPrev(temp.getPrev());
		temp.getPrev().setNext(temp.getNext());
		temp.setValue(null);
		temp.setNext(null);
		temp.setPrev(null);
		this.currentSize--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while (this.remove(obj)) {
			counter++;
		}
		return counter;
	}

	@Override
	public E first() {
		return this.isEmpty() ? null : this.header.getNext().getValue();
	}

	@Override
	public E last() {
		return this.isEmpty() ? null : this.header.getPrev().getValue();
	}

	@Override
	public E get(int index) {
		if ((index < 0) || (index >= this.size())) {
			throw new IndexOutOfBoundsException();
		}

		Node temp = null;
		// closer going forward
		if (index < this.size() / 2) {
			int counter = 0;
			for (temp = header.getNext(); counter < index; temp = temp.getNext(), counter++);
			return temp.getValue();
		}
		// closer going backwards
		else {
			int counter = this.size() - 1;
			for (temp = header.getPrev(); counter > index; temp = temp.getPrev(), counter--);
			return temp.getValue();
		}
	}

	@Override
	public void clear() {
		while (!this.isEmpty()) {
			this.remove(0);
		}
	}

	@Override
	public boolean contains(E e) {
		return this.firstIndex(e) >= 0;
	}

	@Override
	public int firstIndex(E e) {
		if (e == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter = 0;
			Node temp = null;
			for (temp = header.getNext(); temp != header; temp = temp.getNext(), counter++) {
				if (temp.getValue().equals(e)) {
					return counter;
				}
			}
			return -1;
		}
	}

	@Override
	public int lastIndex(E e) {
		if (e == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter = this.size() - 1;
			Node temp = null;
			for (temp = header.getPrev(); temp != header; temp = temp.getPrev(), counter--) {
				if (temp.getValue().equals(e)) {
					return counter;
				}
			}
			return -1;
		}
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
}
