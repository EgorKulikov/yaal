package net.egork.collections;

import java.util.Iterator;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class ReadOnlyIterator<T> implements Iterator<T>  {
	public final void remove() {
		throw new UnsupportedOperationException();
	}
}
