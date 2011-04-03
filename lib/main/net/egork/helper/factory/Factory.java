package net.egork.helper.factory;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Factory<V> {
	public V create();
}
