package net.egork.arrays;

import java.util.Iterator;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class IntArrayIterable implements Iterable<Integer> {
	private final int[] array;

	public IntArrayIterable(int[] array) {
		this.array = array;
	}

	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int index = 0;

			public boolean hasNext() {
				return index < array.length;
			}

			public Integer next() {
				return array[index++];
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
