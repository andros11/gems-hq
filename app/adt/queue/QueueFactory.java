package adt.queue;

public interface QueueFactory<E> {

	public Queue<E> newInstance();

}
