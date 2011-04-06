package net.egork.collections;

import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Transformer<T> {
	public abstract T transform(T value);

	public static<T> void transform(List<T> list, Transformer<T> transformer) {
		int size = list.size();
		for (int i = 0; i < size; i++)
			list.set(i, transformer.transform(list.get(i)));
	}

	public static<T> void transform(T[] array, Transformer<T> transformer) {
		int size = array.length;
		for (int i = 0; i < size; i++)
			array[i] = transformer.transform(array[i]);
	}
}
