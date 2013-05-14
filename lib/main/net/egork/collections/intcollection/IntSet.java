package net.egork.collections.intcollection;

/**
 * @author egorku@yandex-team.ru
 */
public abstract class IntSet extends IntCollection {
	@Override
	public int count(int value) {
		return contains(value) ? 1 : 0;
	}
}
