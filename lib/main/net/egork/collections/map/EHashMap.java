package net.egork.collections.map;

import java.util.*;

/**
 * @author egor@egork.net
 */
public class EHashMap<E, V> extends AbstractMap<E, V> {
	private static final int[] shifts = new int[10];

	private int size;
	private Object[] keys;
	private Object[] values;
	private int capacity;
	private int shift;
	private int[] indices;
	private Set<Entry<E, V>> entrySet;

	static {
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 10; i++)
			shifts[i] = 1 + 3 * i + random.nextInt(3);
	}

	public EHashMap() {
		this(4);
	}

	private void setCapacity(int size) {
		capacity = Integer.highestOneBit(10 * size);
		keys = new Object[capacity];
		values = new Object[capacity];
		shift = capacity / 3 - 1;
		shift -= 1 - (shift & 1);
		indices = new int[capacity];
	}

	public EHashMap(int maxSize) {
		setCapacity(maxSize);
		entrySet = new AbstractSet<Entry<E, V>>() {
			@Override
			public Iterator<Entry<E, V>> iterator() {
				return new Iterator<Entry<E, V>>() {
					private HashEntry entry = new HashEntry();
					int index = 0;

					public boolean hasNext() {
						while (index < size && keys[index] == null)
							index++;
						return index < size;
					}

					public Entry<E, V> next() {
						if (!hasNext())
							throw new NoSuchElementException();
						entry.key = (E) keys[index];
						entry.value = (V) values[index++];
						return entry;
					}

					public void remove() {
						if (entry.key == null)
							throw new IllegalStateException();
						EHashMap.this.remove(entry.key);
						entry.key = null;
						entry.value = null;
					}
				};
			}

			@Override
			public int size() {
				return size;
			}
		};
	}

	public EHashMap(Map<E, V> map) {
		this(map.size());
		putAll(map);
	}

	@Override
	public Set<Entry<E, V>> entrySet() {
		return entrySet;
	}

	@Override
	public void clear() {
		Arrays.fill(keys, null);
		Arrays.fill(values, null);
		size = 0;
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
	public V remove(Object o) {
		if (o == null)
			return null;
		int index = index(o);
		int indicesSize = 0;
		while (keys[index] != null && !keys[index].equals(o)) {
			indices[indicesSize++] = index;
			index = (index + shift) & (capacity - 1);
		}
		if (keys[index] == null)
			return null;
		size--;
		int lastIndex = indicesSize;
		indices[indicesSize++] = index;
		keys[index] = null;
		V result = (V) values[index];
		values[index] = null;
		index = (index + shift) & (capacity - 1);
		while (keys[index] != null) {
			int curKey = index(keys[index]);
			for (int i = 0; i <= lastIndex; i++) {
				if (indices[i] == curKey) {
					keys[indices[lastIndex]] = keys[index];
					values[indices[lastIndex]] = values[index];
					keys[index] = null;
					values[index] = null;
					lastIndex = indicesSize;
				}
			}
			indices[indicesSize++] = index;
			index = (index + shift) & (capacity - 1);
		}
		return result;
	}

	@Override
	public V put(E e, V value) {
		if (e == null)
			return null;
		int index = index(e);
		while (keys[index] != null && !keys[index].equals(e))
			index = (index + shift) & (capacity - 1);
		if (keys[index] == null)
			size++;
		keys[index] = e;
		values[index] = value;
		if (size * 2 > capacity) {
			Object[] oldKeys = keys;
			Object[] oldValues = values;
			setCapacity(size);
			size = 0;
			for (int i = 0; i < oldKeys.length; i++) {
				if (oldKeys[i] != null)
					put((E) oldKeys[i], (V) oldValues[i]);
			}
		}
		return value;
	}

	@Override
	public V get(Object o) {
		if (o == null)
			return null;
		int index = index(o);
		while (keys[index] != null && !keys[index].equals(o))
			index = (index + shift) & (capacity - 1);
		return (V) values[index];
	}

	@Override
	public boolean containsKey(Object o) {
		if (o == null)
			return false;
		int index = index(o);
		while (keys[index] != null && !keys[index].equals(o))
			index = (index + shift) & (capacity - 1);
		return keys[index] != null;
	}

	@Override
	public int size() {
		return size;
	}

	private class HashEntry implements Entry<E, V> {
		private E key;
		private V value;

		public E getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			put(key, value);
			return this.value = value;
		}
	}
}
