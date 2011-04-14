package net.egork.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Filter<T> {
	public abstract boolean accept(T element);

	public static<T> List<T> filter(Iterable<T> iterable, Filter<T> filter) {
		List<T> result = new ArrayList<T>();
		for (T element : iterable) {
			if (filter.accept(element))
				result.add(element);
		}
		return result;
	}

	public static<T> List<T> filter(T[] array, Filter<T> filter) {
		List<T> result = new ArrayList<T>();
		for (T element : array) {
			if (filter.accept(element))
				result.add(element);
		}
		return result;
	}
}
