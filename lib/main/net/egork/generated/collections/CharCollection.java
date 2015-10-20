package net.egork.generated.collections;

import java.util.NoSuchElementException;
import java.util.Iterator;

import net.egork.generated.collections.iterator.*;
import net.egork.generated.collections.list.*;
import net.egork.generated.collections.function.*;
import net.egork.generated.collections.comparator.*;

/**
 * @author Egor Kulikov
 */
public interface CharCollection extends CharStream {
	public int size();

	default public boolean isEmpty() {
		return size() == 0;
	}

	default public void add(char value) {
		throw new UnsupportedOperationException();
	}

	default public boolean remove(char value) {
	    for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
	        if (value == it.value()) {
	            it.remove();
	            return true;
	        }
	    }
	    return false;
	}

	default public char[] toArray() {
		int size = size();
		char[] array = new char[size];
		int i = 0;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			array[i++] = it.value();
		}
		return array;
	}

	default public void addAll(CharStream values) {
		for (CharIterator it = values.charIterator(); it.isValid(); it.advance()) {
			add(it.value());
		}
	}

	default public void removeAll(CharStream values) {
		for (CharIterator it = values.charIterator(); it.isValid(); it.advance()) {
			remove(it.value());
		}
	}

	default public void retainAll(CharCollection values) {
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			if (!values.contains(it.value())) {
				it.remove();
			}
		}
	}

	default public CharCollection compute() {
		return this;
	}
}
