package net.egork.arrays;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Array<T> implements List<T> {
	protected final int from;
	protected final int to;
	protected final int size;

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

	protected Array(int from, int to) {
		this.from = from;
		this.to = to;
		size = to - from;
	}

	protected abstract Array<T> subArray(int from, int to);

	public Array<T> subList(int from) {
		return subList(from, size);
	}

	public Array<T> subList(int from, int to) {
		if (from < 0)
			from += size;
		if (to < 0)
			to += size;
		return subArray(from, to);
	}


	public int size() {
		return size;
	}

	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	public Iterator<T> iterator() {
		return listIterator();
	}

	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o))
				return false;
		}
		return true;
	}

	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (get(i) == o)
				return i;
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i--) {
			if (get(i) == o)
				return i;
		}
		return -1;
	}

	public ListIterator<T> listIterator() {
		return listIterator(0);
	}

	public ListIterator<T> listIterator(int index) {
		return new ArrayIterator(index);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean add(T t) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}

	public T remove(int index) {
		throw new UnsupportedOperationException();
	}

	private class ArrayIterator implements ListIterator<T> {
		private int index;

		protected ArrayIterator(int index) {
			this.index = index;
		}

		public boolean hasNext() {
			return index < size;
		}

		public T next() {
			return get(index++);
		}

		public boolean hasPrevious() {
			return index >= 0;
		}

		public T previous() {
			return get(index--);
		}

		public int nextIndex() {
			return index + 1;
		}

		public int previousIndex() {
			return index - 1;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public void set(T t) {
			Array.this.set(index, t);
		}

		public void add(T t) {
			throw new UnsupportedOperationException();
		}
	}

	private static class ReferenceArray<T> extends Array<T> {
		private final T[] array;

		protected ReferenceArray(T[] array) {
			super(0, array.length);
			this.array = array;
		}

		protected ReferenceArray(T[] array, int from, int to) {
			super(from, to);
			this.array = array;
		}

		public Object[] toArray() {
			return array;
		}

		public <T> T[] toArray(T[] a) {
			//noinspection unchecked
			return (T[]) array;
		}

		public T get(int index) {
			return array[index + from];
		}

		public T set(int index, T element) {
			T result = array[index + from];
			array[index + from] = element;
			return result;
		}

		protected Array<T> subArray(int fromIndex, int toIndex) {
			return new ReferenceArray<T>(array, fromIndex, toIndex);
		}
	}

	private static class ListArray<T> extends Array<T> {
		private final List<T> list;

		protected ListArray(List<T> list) {
			super(0, list.size());
			this.list = list;
		}

		protected ListArray(List<T> list, int from, int to) {
			super(from, to);
			this.list = list;
		}

		public Object[] toArray() {
			return list.toArray();
		}

		public <T> T[] toArray(T[] a) {
			//noinspection SuspiciousToArrayCall
			return list.toArray(a);
		}

		public T get(int index) {
			return list.get(index + from);
		}

		public T set(int index, T element) {
			return list.set(index + from, element);
		}

		protected Array<T> subArray(int fromIndex, int toIndex) {
			return new ListArray<T>(list, fromIndex, toIndex);
		}
	}

	private static class IntArray extends Array<Integer> {
		private final int[] array;

		protected IntArray(int[] array) {
			super(0, array.length);
			this.array = array;
		}

		protected IntArray(int[] array, int from, int to) {
			super(from, to);
			this.array = array;
		}

		public Object[] toArray() {
			Integer[] result = new Integer[size];
			for (int i = 0; i < size; i++)
				result[i] = get(i);
			return result;
		}

		public <T> T[] toArray(T[] a) {
			//noinspection unchecked
			return (T[]) toArray();
		}

		public Integer get(int index) {
			return array[index + from];
		}

		public Integer set(int index, Integer element) {
			int result = array[index + from];
			array[index + from] = element;
			return result;
		}

		protected Array<Integer> subArray(int fromIndex, int toIndex) {
			return new IntArray(array, fromIndex, toIndex);
		}
	}

	private static class LongArray extends Array<Long> {
		private final long[] array;

		protected LongArray(long[] array) {
			super(0, array.length);
			this.array = array;
		}

		protected LongArray(long[] array, int from, int to) {
			super(from, to);
			this.array = array;
		}

		public Object[] toArray() {
			Long[] result = new Long[size];
			for (int i = 0; i < size; i++)
				result[i] = get(i);
			return result;
		}

		public <T> T[] toArray(T[] a) {
			//noinspection unchecked
			return (T[]) toArray();
		}

		public Long get(int index) {
			return array[index + from];
		}

		public Long set(int index, Long element) {
			long result = array[index + from];
			array[index + from] = element;
			return result;
		}

		protected Array<Long> subArray(int fromIndex, int toIndex) {
			return new LongArray(array, fromIndex, toIndex);
		}
	}

	private static class CharArray extends Array<Character> {
		private final char[] array;

		protected CharArray(char[] array) {
			super(0, array.length);
			this.array = array;
		}

		protected CharArray(char[] array, int from, int to) {
			super(from, to);
			this.array = array;
		}

		public Object[] toArray() {
			Character[] result = new Character[size];
			for (int i = 0; i < size; i++)
				result[i] = get(i);
			return result;
		}

		public <T> T[] toArray(T[] a) {
			//noinspection unchecked
			return (T[]) toArray();
		}

		public Character get(int index) {
			return array[index + from];
		}

		public Character set(int index, Character element) {
			char result = array[index + from];
			array[index + from] = element;
			return result;
		}

		protected Array<Character> subArray(int fromIndex, int toIndex) {
			return new CharArray(array, fromIndex, toIndex);
		}
	}
}
