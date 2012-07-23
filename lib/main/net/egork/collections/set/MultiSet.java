package net.egork.collections.set;

import net.egork.collections.map.EHashMap;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class MultiSet<K> implements Iterable<K> {
	private final Map<K, Integer> map;
	private int size = 0;

	public MultiSet() {
		this(new EHashMap<K, Integer>());
	}

	public MultiSet(Map<K, Integer> underlying) {
		map = underlying;
	}

	public int size() {
		return size;
	}

	public int entryCount() {
		return map.size();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(K key) {
		return map.containsKey(key);
	}

	public int add(K key) {
		Integer value = map.get(key);
		if (value == null)
			value = 0;
		value++;
		size++;
		map.put(key, value);
		return value;
	}

	public int add(K key, int count) {
		Integer value = map.get(key);
		if (value == null)
			value = 0;
		value += count;
		size += count;
		map.put(key, value);
		return value;
	}

	public int remove(K key) {
		Integer value = map.get(key);
		if (value == null)
			return 0;
		value--;
		size--;
		if (value == 0)
			map.remove(key);
		else
			map.put(key, value);
		return value + 1;
	}

	public int removeAll(K key) {
		int value = map.remove(key);
		size -= value;
		return value;
	}

	public int get(K key) {
		Integer value = map.get(key);
		if (value == null)
			value = 0;
		return value;
	}

	public void clear() {
		map.clear();
		size = 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MultiSet multiSet = (MultiSet) o;

		return !(map != null ? !map.equals(multiSet.map) : multiSet.map != null);

	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	public Iterator<K> iterator() {
		return map.keySet().iterator();
	}

	public Map<K, Integer> getUnderlying() {
		return map;
	}
}
