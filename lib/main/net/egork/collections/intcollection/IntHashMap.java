package net.egork.collections.intcollection;

import net.egork.numbers.IntegerUtils;

import java.util.NoSuchElementException;
import java.util.Random;

public class IntHashMap extends IntSet {
	private static final Random RND = new Random();
	private static final int[] SHIFTS = new int[4];
	private static final byte PRESENT_MASK = 1;
	private static final byte REMOVED_MASK = 2;

	static {
		for (int i = 0; i < 4; i++)
			SHIFTS[i] = RND.nextInt(31) + 1;
	}

	private int size;
	private int realSize;
	private int[] keys;
	private int[] values;
	private byte[] present;
	private int step;
	private int ratio;

	public IntHashMap() {
		this(3);
	}


	public IntHashMap(int capacity) {
		capacity = Math.max(capacity, 1);
		keys = new int[capacity];
		present = new byte[capacity];
		values = new int[capacity];
		ratio = 2;
		initStep(capacity);
	}

	private void initStep(int capacity) {
		step = RND.nextInt(capacity - 2) + 1;
		while (IntegerUtils.gcd(step, capacity) != 1)
			step++;
	}

	@Override
	public IntIterator iterator() {
		return new IntIterator() {
			private int position = size == 0 ? keys.length : -1;

			public int value() throws NoSuchElementException {
				if (position == -1)
					advance();
				if (position >= keys.length)
					throw new NoSuchElementException();
				return keys[position];
			}

			public void advance() throws NoSuchElementException {
				if (position >= keys.length)
					throw new NoSuchElementException();
				position++;
				while (position < keys.length && (present[position] & PRESENT_MASK) == 0)
					position++;
			}

			public boolean isValid() {
				return position < keys.length;
			}
		};
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int value) {
		throw new UnsupportedOperationException();
	}

	public void put(int key, int value) {
		ensureCapacity((realSize + 1) * ratio + 2);
		int current = getHash(key);
		while (present[current] != 0) {
			if ((present[current] & PRESENT_MASK) != 0 && keys[current] == key) {
				values[current] = value;
				return;
			}
			current += step;
			if (current >= values.length)
				current -= values.length;
		}
		while ((present[current] & PRESENT_MASK) != 0) {
			current += step;
			if (current >= keys.length)
				current -= keys.length;
		}
		if (present[current] == 0) {
			realSize++;
		}
		present[current] = PRESENT_MASK;
		keys[current] = key;
		values[current] = value;
		size++;
	}

	private int getHash(int key) {
		int result = key;
		for (int i : SHIFTS)
			result ^= key >> i;
		result %= keys.length;
		if (result < 0)
			result += keys.length;
		return result;
	}

	private void ensureCapacity(int capacity) {
		if (keys.length < capacity) {
			capacity = Math.max(capacity * 2, keys.length);
			rebuild(capacity);
		}
	}

	private void squish() {
		if (keys.length > size * ratio * 2 + 10) {
			rebuild(size * ratio + 3);
		}
	}

	private void rebuild(int capacity) {
		initStep(capacity);
		int[] oldKeys = keys;
		byte[] oldPresent = present;
		int[] oldValues = values;
		keys = new int[capacity];
		present = new byte[capacity];
		values = new int[capacity];
		size = 0;
		realSize = 0;
		for (int i = 0; i < oldKeys.length; i++) {
			if ((oldPresent[i] & PRESENT_MASK) == PRESENT_MASK)
				put(oldKeys[i], oldValues[i]);
		}
	}

	@Override
	public void remove(int value) {
		int current = getHash(value);
		while (present[current] != 0) {
			if (keys[current] == value && (present[current] & PRESENT_MASK) != 0) {
				present[current] = REMOVED_MASK;
				size--;
				squish();
				return;
			}
			current += step;
			if (current >= keys.length)
				current -= keys.length;
		}
	}

	@Override
	public boolean contains(int key) {
		int current = getHash(key);
		while (present[current] != 0) {
			if (keys[current] == key && (present[current] & PRESENT_MASK) != 0)
				return true;
			current += step;
			if (current >= keys.length)
				current -= keys.length;
		}
		return false;
	}

	public int get(int key) {
		int current = getHash(key);
		while (present[current] != 0) {
			if (keys[current] == key && (present[current] & PRESENT_MASK) != 0)
				return values[current];
			current += step;
			if (current >= keys.length)
				current -= keys.length;
		}
		throw new NoSuchElementException();
	}
}
