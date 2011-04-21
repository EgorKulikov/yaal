package net.egork.collections.sequence;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface WritableSequence<T> extends Sequence<T> {
	public void set(int index, T value);
	public WritableSequence<T> subSequence(int from);
	public WritableSequence<T> subSequence(int from, int to);
}
