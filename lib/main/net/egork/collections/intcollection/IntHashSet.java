package net.egork.collections.intcollection;

import net.egork.numbers.IntegerUtils;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author egorku@yandex-team.ru
 */
public class IntHashSet extends IntCollection {
	private static final Random RND = new Random();
	private static final int[] SHIFTS = new int[4];

	static {
		for (int i = 0; i < 4; i++)
			SHIFTS[i] = RND.nextInt(31) + 1;
	}

	private int size;
	private int[] values;
	private boolean[] present;
	private int step;

	public IntHashSet() {
		this(8);
	}

	public IntHashSet(int capacity) {
		values = new int[capacity];
		present = new boolean[capacity];
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
			private int position = -1;

			public int value() throws NoSuchElementException {
				if (position == -1)
					advance();
				if (position >= values.length)
					throw new NoSuchElementException();
				return values[position];
			}

			public void advance() throws NoSuchElementException {
				if (position >= values.length)
					throw new NoSuchElementException();
				position++;
				while (position < values.length && !present[position])
					position++;
			}

			public boolean isValid() {
				return position < values.length;
			}
		};
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int value) {
		ensureCapacity(2 * (size + 1));
		int current = getHash(value) % values.length;
		while (present[current]) {
			if (values[current] == value)
				return;
			current += step;
			if (current >= values.length)
				current -= values.length;
		}
		present[current] = true;
		values[current] = value;
		size++;
	}

	private int getHash(int value) {
		int result = value;
		for (int i : SHIFTS)
			result ^= value >> i;
		return result;
	}

	private void ensureCapacity(int capacity) {
		if (values.length < capacity) {
			capacity = Math.max(capacity, 2 * values.length);
			initStep(capacity);
			int[] oldValues = values;
			boolean[] oldPresent = present;
			values = new int[capacity];
			present = new boolean[capacity];
			for (int i = 0; i < oldValues.length; i++) {
				if (oldPresent[i])
					add(oldValues[i]);
			}
		}
	}

	@Override
	public void remove(int value) {
		int base = getHash(value) % values.length;
		int current = base;
		int free = -1;
		while (present[current]) {
			if (values[current] == value) {
				free = current;
				break;
			}
			current += step;
			if (current >= values.length)
				current -= values.length;
		}
		if (free == -1)
			return;
		size--;
		present[free] = false;
		current += step;
		if (current >= values.length)
			current -= values.length;
		while (present[current]) {
			int key = base;
			int desired = getHash(values[current]) % values.length;
			boolean found = free == desired;
			if (!found) {
				while (key != free) {
					if (key == desired) {
						found = true;
						break;
					}
					key += step;
					if (key >= values.length)
						key -= values.length;
				}
			}
			if (found) {
				present[current] = false;
				present[free] = true;
				values[free] = values[current];
				free = current;
			}
			current += step;
			if (current >= values.length)
				current -= values.length;
		}
	}
}
