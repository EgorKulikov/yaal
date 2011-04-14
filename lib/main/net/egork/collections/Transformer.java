package net.egork.collections;

import net.egork.arrays.ArrayWrapper;

import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Transformer<T> {
	public abstract T transform(T value);

	public static<T> List<T> transform(List<T> list, Transformer<T> transformer) {
		int size = list.size();
		for (int i = 0; i < size; i++)
			list.set(i, transformer.transform(list.get(i)));
		return list;
	}

	public static<T> T[] transform(T[] array, Transformer<T> transformer) {
		int size = array.length;
		for (int i = 0; i < size; i++)
			array[i] = transformer.transform(array[i]);
		return array;
	}

	public static<T>ArrayWrapper<T> transform(ArrayWrapper<T> array, Transformer<T> transformer) {
		int size = array.size();
		for (int i = 0; i < size; i++)
			array.set(i, transformer.transform(array.get(i)));
		return array;
	}
}
