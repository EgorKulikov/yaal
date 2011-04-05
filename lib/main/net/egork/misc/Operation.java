package net.egork.misc;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Operation<T> extends Factory<T> {
	public T operation(T first, T second);
}
