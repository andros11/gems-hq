package adt.list;

public class LinkedListFactory<E> implements ListFactory<E> {

	@Override
	public List<E> newInstance(int initialCapacity) {
		return new LinkedList<E>();
	}

	@Override
	public List<E> newInstance() {
		return new LinkedList<E>();
	}

}
