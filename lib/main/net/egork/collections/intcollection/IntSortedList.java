package net.egork.collections.intcollection;

import net.egork.collections.comparators.IntComparator;

/**
 * @author egorku@yandex-team.ru
 */
public abstract class IntSortedList extends IntList {
	protected final IntComparator comparator;

	protected IntSortedList(IntComparator comparator) {
		this.comparator = comparator;
	}

	@Override
	public void set(int index, int value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IntSortedList inPlaceSort(IntComparator comparator) {
		if (comparator == this.comparator)
			return this;
		throw new UnsupportedOperationException();
	}

	@Override
	public IntSortedList sort(IntComparator comparator) {
		if (comparator == this.comparator)
			return this;
		return super.sort(comparator);
	}

	@Override
	public boolean contains(int value) {
		int index = lowerBound(value);
		return index != size() && get(index) == value;
	}

	public int lowerBound(int value) {
		int left = 0;
		int right = size();
		while (left < right) {
			int middle = (left + right) >> 1;
			if (comparator.compare(get(middle), value) >= 0)
				right = middle;
			else
				left = middle + 1;
		}
		return left;
	}

	public int upperBound(int value) {
		int left = 0;
		int right = size();
		while (left < right) {
			int middle = (left + right) >> 1;
			if (comparator.compare(get(middle), value) > 0)
				right = middle;
			else
				left = middle + 1;
		}
		return left;
	}

	protected void ensureSorted() {
		int size = size();
		if (size == 0)
			return;
		int last = get(0);
		for (int i = 1; i < size; i++) {
			int current = get(i);
			if (comparator.compare(last, current) > 0)
				throw new IllegalArgumentException();
			last = current;
		}
	}
}
