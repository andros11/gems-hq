package adt.bst;

public class LongComparator implements KeyComparator<Long> {

	@Override
	public int compareTo(Long key1, Long key2) {
		return safeLongToInt(key1 - key2);
	}

	private static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException
			(l + " cannot be cast to int without changing its value.");
		}
		return (int) l;
	}
}
