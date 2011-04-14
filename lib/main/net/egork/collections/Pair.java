package net.egork.collections;

import net.egork.utils.io.InputReader;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Pair<U, V> {
	public static Pair<Long, Long>[] readArray(int n, InputReader in) {
		@SuppressWarnings({"unchecked"})
		Pair<Long, Long>[] result = new Pair[n];
		for (int i = 0; i < n; i++)
			result[i] = readPair(in);
		return result;
	}

	public static Pair<Long, Long> readPair(InputReader in) {
		long first = in.readLong();
		long second = in.readLong();
		return new Pair<Long, Long>(first, second);
	}

	public static class Comparator<U extends Comparable<U>, V extends Comparable<V>> implements java.util.Comparator<Pair<U, V>> {
		public int compare(Pair<U, V> o1, Pair<U, V> o2) {
			int result = o1.first.compareTo(o2.first);
			if (result != 0)
				return result;
			return o1.second.compareTo(o2.second);
		}
	}

	private final U first;
	private final V second;

	public Pair(U first, V second) {
		this.first = first;
		this.second = second;
	}

	public U first() {
		return first;
	}

	public V second() {
		return second;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pair pair = (Pair) o;

		return !(first != null ? !first.equals(pair.first) : pair.first != null) && !(second != null ? !second.equals(pair.second) : pair.second != null);

	}

	@Override
	public int hashCode() {
		int result = first != null ? first.hashCode() : 0;
		result = 31 * result + (second != null ? second.hashCode() : 0);
		return result;
	}

	public Pair<V, U> swap() {
		//noinspection unchecked
		return new Pair<V, U>(second, first);
	}

	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}
}
