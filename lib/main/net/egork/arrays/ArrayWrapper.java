package net.egork.arrays;

import java.util.Iterator;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class ArrayWrapper<T> implements Iterable<T> {
	protected final int from;
	protected final int to;
	protected final int size;
	protected final Object underlying;

	public static<T> ArrayWrapper<T> wrap(T[] array) {
		return new ReferenceArrayWrapper<T>(array);
	}

	public static<T> ArrayWrapper<T> wrap(List<T> list) {
		return new ListWrapper<T>(list);
	}

	public static ArrayWrapper<Integer> wrap(int[] array) {
		return new IntArrayWrapper(array);
	}

	public static ArrayWrapper<Long> wrap(long[] array) {
		return new LongArrayWrapper(array);
	}

	public static ArrayWrapper<Character> wrap(char[] array) {
		return new CharArrayWrapper(array);
	}

	public static ArrayWrapper<Double> wrap(double[] array) {
		return new DoubleArrayWrapper(array);
	}

	protected ArrayWrapper(Object underlying, int from, int to) {
		this.underlying = underlying;
		this.from = from;
		this.to = to;
		size = to - from;
	}

	protected abstract ArrayWrapper<T> subArrayImpl(int from, int to);

	public ArrayWrapper<T> subArray(int from) {
		return subArray(from, size);
	}

	public ArrayWrapper<T> subArray(int from, int to) {
		if (from < 0)
			from += size;
		if (to < 0)
			to += size;
		return subArrayImpl(from, to);
	}


	public int size() {
		return size;
	}

	public boolean contains(T o) {
		return indexOf(o) != -1;
	}

	public Iterator<T> iterator() {
		return new ArrayIterator();
	}

	public int indexOf(T o) {
		for (int i = 0; i < size; i++) {
			if (get(i).equals(o))
				return i;
		}
		return -1;
	}

	public abstract T get(int index);

	public abstract void set(int index, T value);

	public int lastIndexOf(T o) {
		for (int i = size - 1; i >= 0; i--) {
			if (get(i).equals(o))
				return i;
		}
		return -1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private class ArrayIterator implements Iterator<T> {
		private int index = 0;

		protected ArrayIterator() {
		}

		public boolean hasNext() {
			return index < size;
		}

		public T next() {
			return get(index++);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	protected static class ReferenceArrayWrapper<T> extends ArrayWrapper<T> {
		protected final T[] array;

		protected ReferenceArrayWrapper(T[] array) {
			this(array, 0, array.length);
		}

		protected ReferenceArrayWrapper(T[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public T get(int index) {
			return array[index + from];
		}

		public void set(int index, T element) {
			array[index + from] = element;
		}

		protected ArrayWrapper<T> subArrayImpl(int fromIndex, int toIndex) {
			return new ReferenceArrayWrapper<T>(array, fromIndex + from, toIndex + from);
		}
	}

	protected static class ListWrapper<T> extends ArrayWrapper<T> {
		protected final List<T> list;

		protected ListWrapper(List<T> list) {
			this(list, 0, list.size());
		}

		protected ListWrapper(List<T> list, int from, int to) {
			super(list, from, to);
			this.list = list;
		}

		public T get(int index) {
			return list.get(index + from);
		}

		public void set(int index, T element) {
			list.set(index + from, element);
		}

		protected ArrayWrapper<T> subArrayImpl(int fromIndex, int toIndex) {
			return new ListWrapper<T>(list, fromIndex + from, toIndex + from);
		}
	}

	protected static class IntArrayWrapper extends ArrayWrapper<Integer> {
		protected final int[] array;

		protected IntArrayWrapper(int[] array) {
			this(array, 0, array.length);
		}

		protected IntArrayWrapper(int[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public Integer get(int index) {
			return array[index + from];
		}

		public void set(int index, Integer element) {
			array[index + from] = element;
		}

		protected ArrayWrapper<Integer> subArrayImpl(int fromIndex, int toIndex) {
			return new IntArrayWrapper(array, fromIndex + from, toIndex + from);
		}
	}

	protected static class LongArrayWrapper extends ArrayWrapper<Long> {
		protected final long[] array;

		protected LongArrayWrapper(long[] array) {
			this(array, 0, array.length);
		}

		protected LongArrayWrapper(long[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public Long get(int index) {
			return array[index + from];
		}

		public void set(int index, Long element) {
			array[index + from] = element;
		}

		protected ArrayWrapper<Long> subArrayImpl(int fromIndex, int toIndex) {
			return new LongArrayWrapper(array, fromIndex + from, toIndex + from);
		}
	}

	protected static class CharArrayWrapper extends ArrayWrapper<Character> {
		protected final char[] array;

		protected CharArrayWrapper(char[] array) {
			this(array, 0, array.length);
		}

		protected CharArrayWrapper(char[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public Character get(int index) {
			return array[index + from];
		}

		public void set(int index, Character element) {
			array[index + from] = element;
		}

		protected ArrayWrapper<Character> subArrayImpl(int fromIndex, int toIndex) {
			return new CharArrayWrapper(array, fromIndex + from, toIndex + from);
		}
	}

	protected static class DoubleArrayWrapper extends ArrayWrapper<Double> {
		protected final double[] array;

		protected DoubleArrayWrapper(double[] array) {
			this(array, 0, array.length);
		}

		protected DoubleArrayWrapper(double[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public Double get(int index) {
			return array[index + from];
		}

		public void set(int index, Double element) {
			array[index + from] = element;
		}

		protected ArrayWrapper<Double> subArrayImpl(int fromIndex, int toIndex) {
			return new DoubleArrayWrapper(array, fromIndex + from, toIndex + from);
		}
	}
}
