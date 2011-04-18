package net.egork.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Function<A, V> {
	public static<A, V> List<V> apply(Iterable<A> collection, Function<A, V> function) {
		List<V> result = new ArrayList<V>();
		for (A argument : collection)
			result.add(function.value(argument));
		return result;
	}

	public abstract V value(A argument);
}
