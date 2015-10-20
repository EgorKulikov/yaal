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
public interface IntCollection extends IntStream {
	public int size();

	default public boolean isEmpty() {
		return size() == 0;
	}

	default public void add(int value) {
		throw new UnsupportedOperationException();
	}

	default public boolean remove(int value) {
	    for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
	        if (value == it.value()) {
	            it.remove();
	            return true;
	        }
	    }
	    return false;
	}

	default public int[] toArray() {
		int size = size();
		int[] array = new int[size];
		int i = 0;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			array[i++] = it.value();
		}
		return array;
	}

	default public void addAll(IntStream values) {
		for (IntIterator it = values.intIterator(); it.isValid(); it.advance()) {
			add(it.value());
		}
	}

	default public void removeAll(IntStream values) {
		for (IntIterator it = values.intIterator(); it.isValid(); it.advance()) {
			remove(it.value());
		}
	}

	default public void retainAll(IntCollection values) {
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (!values.contains(it.value())) {
				it.remove();
			}
		}
	}

	default public IntCollection compute() {
		return this;
	}
}
