package net.egork.helper;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Factory<V> {
	public V create();
}
