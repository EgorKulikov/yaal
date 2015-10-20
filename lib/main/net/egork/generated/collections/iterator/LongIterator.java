package net.egork.generated.collections.iterator;

import java.util.NoSuchElementException;

/**
 * @author Egor Kulikov
 */
public interface LongIterator {
	public long value() throws NoSuchElementException;
	/**
	 * Returns true if next call to isValid will return true
	 */
	public boolean advance() throws NoSuchElementException;
	public boolean isValid();
	public void remove() throws NoSuchElementException;
}
