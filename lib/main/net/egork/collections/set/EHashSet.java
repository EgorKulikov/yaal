package net.egork.collections.set;

import java.util.*;

/**
 * @author egor@egork.net
 */
public class EHashSet<E> extends AbstractSet<E> {
	private static final int[] shifts = new int[10];

	private int size;
	private Object[] keys;
	private int capacity;
	private int shift;
	private int[] indices;

	static {
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 10; i++)
			shifts[i] = 1 + 3 * i + random.nextInt(3);
	}

	public EHashSet() {
		this(4);
	}

	public EHashSet(int maxSize) {
		setCapacity(maxSize);
	}

	public EHashSet(Collection<E> collection) {
		this(collection.size());
		addAll(collection);
	}

	@Override
	public boolean contains(Object o) {
		if (o == null)
			return false;
		int index = index(o);
		while (keys[index] != null && !keys[index].equals(o))
			index = (index + shift) & (capacity - 1);
		return keys[index] != null;
	}

	@Override
	public boolean add(E e) {
		if (e == null)
			return false;
		int index = index(e);
		while (keys[index] != null && !keys[index].equals(e))
			index = (index + shift) & (capacity - 1);
		if (keys[index] == null)
			size++;
		keys[index] = e;
		if (size * 3 > capacity) {
			Object[] oldKeys = keys;
			setCapacity(size);
			size = 0;
			for (Object key : oldKeys) {
				if (key != null)
					add((E) key);
			}
		}
		return true;
	}

	private int index(Object o) {
		return getHash(o.hashCode()) & (capacity - 1);
	}

	private int getHash(int h) {
		int result = h;
		for (int i : shifts)
			result ^= h >>> i;
		return result;
	}

	@Override
	public boolean remove(Object o) {
		if (o == null)
			return false;
		int index = index(o);
		int indicesSize = 0;
		while (keys[index] != null && !keys[index].equals(o)) {
			indices[indicesSize++] = index;
			index = (index + shift) & (capacity - 1);
		}
		if (keys[index] == null)
			return false;
		size--;
		int lastIndex = indicesSize;
		indices[indicesSize++] = index;
		keys[index] = null;
		index = (index + shift) & (capacity - 1);
		while (keys[index] != null) {
			int curKey = index(keys[index]);
			for (int i = 0; i <= lastIndex; i++) {
				if (indices[i] == curKey) {
					keys[indices[lastIndex]] = keys[index];
					keys[index] = null;
					lastIndex = indicesSize;
				}
			}
			indices[indicesSize++] = index;
			index = (index + shift) & (capacity - 1);
		}
		return true;
	}

	@Override
	public void clear() {
		Arrays.fill(keys, null);
		size = 0;
	}

	private void setCapacity(int size) {
		capacity = Integer.highestOneBit(10 * size);
		keys = new Object[capacity];
		shift = capacity / 3 - 1;
		shift -= 1 - (shift & 1);
		indices = new int[capacity];
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index = 0;
			private E lastReturnedKey;

			public boolean hasNext() {
				while (index < size && keys[index] == null)
					index++;
				return index < size;
			}

			public E next() {
				if (!hasNext())
					throw new NoSuchElementException();
				return (E) keys[index++];
			}

			public void remove() {
				if (lastReturnedKey == null)
					throw new IllegalStateException();
				EHashSet.this.remove(lastReturnedKey);
				lastReturnedKey = null;
			}
		};
	}

	@Override
	public int size() {
		return size;
	}
}
