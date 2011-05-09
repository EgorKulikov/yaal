package net.egork.collections.sequence;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Sequence<T> extends Iterable<T> {
	public Sequence<T> subSequence(int from);
	public Sequence<T> subSequence(int from, int to);
	public int size();
	public boolean isEmpty();
	public T get(int index);
}
