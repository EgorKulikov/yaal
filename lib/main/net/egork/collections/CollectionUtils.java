package net.egork.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class CollectionUtils {
	public static int[] toArray(Collection<Integer> collection) {
		int[] array = new int[collection.size()];
		int index = 0;
		for (int element : collection)
			array[index++] = element;
		return array;
	}

	public static List<Integer> range(int from, int to) {
		List<Integer> result = new ArrayList<Integer>(Math.max(from, to) - Math.min(from, to) + 1);
		if (to > from) {
			for (int i = from; i <= to; i++)
				result.add(i);
		} else {
			for (int i = from; i >= to; i--)
				result.add(i);
		}
		return result;
	}

	public static void rotate(List<Integer> list) {
		list.add(list.remove(0));
	}

	public static void increment(List<Integer> path) {
		Transformer.transform(path, new Transformer<Integer>() {
			@Override
			public Integer transform(Integer value) {
				return value + 1;
			}
		});
	}
}
