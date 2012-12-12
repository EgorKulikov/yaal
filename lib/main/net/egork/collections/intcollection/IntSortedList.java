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
}
