package adt.list;

import java.util.Iterator;

public interface SortedList<E extends Comparable<E>> extends Iterable<E> {

	public boolean add(E obj);
	public boolean remove(E obj);
	public boolean remove(int index);
	public int removeAll(E obj);
	public E first();
	public E last();
	public E get(int index);
	public void clear();
	public boolean contains(E e);
	public int firstIndex(E e);
	public int lastIndex(E e);
	public int size();
	public boolean isEmpty();
	public Iterator<E> iterator(int index);
	public ReverseIterator<E> reverseIterator();
	public ReverseIterator<E> reverseIterator(int index);

}
