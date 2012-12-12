package net.egork.collections.intcollection;

import java.util.NoSuchElementException;

/**
 * @author egorku@yandex-team.ru
 */
public abstract class IntCollection {
	public abstract IntIterator iterator();
	public abstract int size();
	public abstract void add(int value);
	public abstract void remove(int value);

	public void forEach(IntTask task) {
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
			task.process(iterator.value());
	}

	public int count(int value) {
		int result = 0;
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance()) {
			if (iterator.value() == value)
				result++;
		}
		return result;
	}

	public int min() {
		if (size() == 0)
			throw new NoSuchElementException();
		int result = Integer.MAX_VALUE;
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
			result = Math.min(result, iterator.value());
		return result;
	}

	public int max() {
		if (size() == 0)
			throw new NoSuchElementException();
		int result = Integer.MIN_VALUE;
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
			result = Math.max(result, iterator.value());
		return result;
	}
}
