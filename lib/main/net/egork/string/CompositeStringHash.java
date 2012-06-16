package net.egork.string;

import net.egork.numbers.IntegerUtils;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public class CompositeStringHash extends AbstractStringHash {
	private final StringHash first;
	private final StringHash second;
	private final long power;

	public CompositeStringHash(StringHash first, StringHash second) {
		this.first = first;
		this.second = second;
		power = IntegerUtils.power(StringHash.MULTIPLIER, first.length());
	}

	public long hash(int from, int to) {
		try {
		if (to <= first.length())
			return first.hash(from, to);
		if (from >= first.length())
			return second.hash(from - first.length(), to - first.length()) * power;
		return first.hash(from) + power * second.hash(0, to - first.length());
		} catch (IndexOutOfBoundsException e) {
			return 0;
		}
	}

	public int length() {
		return first.length() + second.length();
	}
}
