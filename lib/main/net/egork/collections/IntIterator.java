package net.egork.collections;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public interface IntIterator {
	public boolean hasNext();
	public int next();
	public void remove();
}
