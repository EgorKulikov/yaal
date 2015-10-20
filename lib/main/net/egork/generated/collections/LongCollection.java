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
public interface LongCollection extends LongStream {
	public int size();

	default public boolean isEmpty() {
		return size() == 0;
	}

	default public void add(long value) {
		throw new UnsupportedOperationException();
	}

	default public boolean remove(long value) {
	    for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
	        if (value == it.value()) {
	            it.remove();
	            return true;
	        }
	    }
	    return false;
	}

	default public long[] toArray() {
		int size = size();
		long[] array = new long[size];
		int i = 0;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			array[i++] = it.value();
		}
		return array;
	}

	default public void addAll(LongStream values) {
		for (LongIterator it = values.longIterator(); it.isValid(); it.advance()) {
			add(it.value());
		}
	}

	default public void removeAll(LongStream values) {
		for (LongIterator it = values.longIterator(); it.isValid(); it.advance()) {
			remove(it.value());
		}
	}

	default public void retainAll(LongCollection values) {
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			if (!values.contains(it.value())) {
				it.remove();
			}
		}
	}

	default public LongCollection compute() {
		return this;
	}
}
