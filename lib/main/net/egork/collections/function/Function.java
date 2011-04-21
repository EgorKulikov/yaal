package net.egork.collections.function;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Function<A, V> {
	public abstract V value(A argument);
}
