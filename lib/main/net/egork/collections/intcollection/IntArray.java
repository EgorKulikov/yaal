package net.egork.collections.intcollection;

/**
 * @author egorku@yandex-team.ru
 */
public class IntArray extends IntList {
	private final int[] array;

	public IntArray(int[] array) {
		this.array = array;
	}

	public IntArray(IntCollection collection) {
		array = new int[collection.size()];
		int i = 0;
		for (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())
			array[i++] = iterator.value();
	}

	@Override
	public int get(int index) {
		return array[index];
	}

	@Override
	public void set(int index, int value) {
		array[index] = value;
	}

	@Override
	public int size() {
		return array.length;
	}

	@Override
	public void add(int value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int[] toArray() {
		return array;
	}
}
