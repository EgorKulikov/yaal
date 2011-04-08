package net.egork.arrays;

import java.util.Iterator;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Array<T> implements Iterable<T> {
	protected final int from;
	protected final int to;
	protected final int size;
	protected final Object underlying;

	public static<T> Array<T> create(T[] array) {
		return new ReferenceArray<T>(array);
	}

	public static<T> Array<T> create(List<T> list) {
		return new ListArray<T>(list);
	}

	public static Array<Integer> create(int[] array) {
		return new IntArray(array);
	}

	public static Array<Long> create(long[] array) {
		return new LongArray(array);
	}

	public static Array<Character> create(char[] array) {
		return new CharArray(array);
	}

	protected Array(Object underlying, int from, int to) {
		this.underlying = underlying;
		this.from = from;
		this.to = to;
		size = to - from;
	}

	protected abstract Array<T> subArrayImpl(int from, int to);

	public Array<T> subArray(int from) {
		return subArray(from, size);
	}

	public Array<T> subArray(int from, int to) {
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

	public boolean containsAll(Iterable<? extends T> c) {
		for (T o : c) {
			if (!contains(o))
				return false;
		}
		return true;
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

	protected static class ReferenceArray<T> extends Array<T> {
		protected final T[] array;

		protected ReferenceArray(T[] array) {
			this(array, 0, array.length);
		}

		protected ReferenceArray(T[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public T get(int index) {
			return array[index + from];
		}

		public void set(int index, T element) {
			array[index + from] = element;
		}

		protected Array<T> subArrayImpl(int fromIndex, int toIndex) {
			return new ReferenceArray<T>(array, fromIndex, toIndex);
		}
	}

	protected static class ListArray<T> extends Array<T> {
		protected final List<T> list;

		protected ListArray(List<T> list) {
			this(list, 0, list.size());
		}

		protected ListArray(List<T> list, int from, int to) {
			super(list, from, to);
			this.list = list;
		}

		public T get(int index) {
			return list.get(index + from);
		}

		public void set(int index, T element) {
			list.set(index + from, element);
		}

		protected Array<T> subArrayImpl(int fromIndex, int toIndex) {
			return new ListArray<T>(list, fromIndex, toIndex);
		}
	}

	protected static class IntArray extends Array<Integer> {
		protected final int[] array;

		protected IntArray(int[] array) {
			this(array, 0, array.length);
		}

		protected IntArray(int[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public Integer get(int index) {
			return array[index + from];
		}

		public void set(int index, Integer element) {
			array[index + from] = element;
		}

		protected Array<Integer> subArrayImpl(int fromIndex, int toIndex) {
			return new IntArray(array, fromIndex, toIndex);
		}
	}

	protected static class LongArray extends Array<Long> {
		protected final long[] array;

		protected LongArray(long[] array) {
			this(array, 0, array.length);
		}

		protected LongArray(long[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public Long get(int index) {
			return array[index + from];
		}

		public void set(int index, Long element) {
			array[index + from] = element;
		}

		protected Array<Long> subArrayImpl(int fromIndex, int toIndex) {
			return new LongArray(array, fromIndex, toIndex);
		}
	}

	protected static class CharArray extends Array<Character> {
		protected final char[] array;

		protected CharArray(char[] array) {
			this(array, 0, array.length);
		}

		protected CharArray(char[] array, int from, int to) {
			super(array, from, to);
			this.array = array;
		}

		public Character get(int index) {
			return array[index + from];
		}

		public void set(int index, Character element) {
			array[index + from] = element;
		}

		protected Array<Character> subArrayImpl(int fromIndex, int toIndex) {
			return new CharArray(array, fromIndex, toIndex);
		}
	}
}
