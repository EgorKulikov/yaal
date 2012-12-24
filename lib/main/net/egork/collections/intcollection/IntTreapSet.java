package net.egork.collections.intcollection;

import net.egork.collections.comparators.IntComparator;

/**
 * @author egorku@yandex-team.ru
 */
public class IntTreapSet extends IntSortedList {
	public IntTreapSet() {
		this(IntComparator.DEFAULT);
	}

	public IntTreapSet(IntComparator comparator) {
		super(comparator);
	}

	@Override
	public int get(int index) {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int size() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void add(int value) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
