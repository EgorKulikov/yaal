package net.egork.collections.intcollection;

import java.util.NoSuchElementException;

/**
 * @author egorku@yandex-team.ru
 */
public interface IntIterator {
	public int value() throws NoSuchElementException;
	/*
	 * @throws NoSuchElementException only if iterator already invalid
	 */
	public void advance() throws NoSuchElementException;
	public boolean isValid();
}
