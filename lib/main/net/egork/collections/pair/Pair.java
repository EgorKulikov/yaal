package net.egork.collections.pair;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Pair<U, V> {
	private final U first;
	private final V second;

	public Pair(U first, V second) {
		this.first = first;
		this.second = second;
	}

	public U getFirst() {
		return first;
	}

	public V getSecond() {
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
