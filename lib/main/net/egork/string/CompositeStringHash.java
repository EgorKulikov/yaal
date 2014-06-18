package net.egork.string;

import net.egork.numbers.IntegerUtils;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public class CompositeStringHash extends AbstractStringHash {
	private final StringHash first;
	private final StringHash second;
	private final long firstPower;
    private final long secondPower;

	public CompositeStringHash(StringHash first, StringHash second) {
		this.first = first;
		this.second = second;
		firstPower = IntegerUtils.power(MULTIPLIER, first.length(), FIRST_MOD);
        secondPower = IntegerUtils.power(MULTIPLIER, first.length(), SECOND_MOD);
    }

	public long hash(int from, int to) {
        long firstFirst;
        long firstSecond;
        long secondFirst;
        long secondSecond;
		if (to <= first.length()) {
            secondFirst = 0;
            secondSecond = 0;
        } else {
            long value = second.hash(Math.max(0, from - first.length()), to - first.length());
            secondFirst = value >>> 32;
            secondSecond = value & ((1L << 32) - 1);
        }
		if (from >= first.length()) {
            firstFirst = 0;
            firstSecond = 0;
        } else {
            long value = first.hash(from, Math.min(to, second.length()));
            firstFirst = value >>> 32;
            firstSecond = value & ((1L << 32) - 1);
        }
        return (((firstFirst + secondFirst * firstPower) % FIRST_MOD) << 32) +
            ((firstSecond + secondSecond * secondPower) % SECOND_MOD);
	}

	public int length() {
		return first.length() + second.length();
	}
}
