package net.egork.string;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SimpleStringHash extends AbstractStringHash {
    private static long[] firstReversePower = new long[0];
    private static long[] secondReversePower = new long[0];

    private final long[] firstHash;
    private final long[] secondHash;

    public SimpleStringHash(CharSequence string) {
        int length = string.length();
        ensureCapacity(length);
		firstHash = new long[length + 1];
        secondHash = new long[length + 1];
        long firstPower = 1;
        long secondPower = 1;
		for (int i = 0; i < length; i++) {
			firstHash[i + 1] = (firstHash[i] + string.charAt(i) * firstPower) % FIRST_MOD;
            secondHash[i + 1] = (secondHash[i] + string.charAt(i) * secondPower) % SECOND_MOD;
			firstPower *= MULTIPLIER;
            firstPower %= FIRST_MOD;
            secondPower *= MULTIPLIER;
            secondPower %= SECOND_MOD;
		}
	}

    private void ensureCapacity(int length) {
        if (firstReversePower.length >= length)
            return;
        length = Math.max(length + 1, firstReversePower.length << 1);
        long[] oldFirst = firstReversePower;
        long[] oldSecond = secondReversePower;
        firstReversePower = new long[length];
        secondReversePower = new long[length];
        System.arraycopy(oldFirst, 0, firstReversePower, 0, oldFirst.length);
        System.arraycopy(oldSecond, 0, secondReversePower, 0, oldSecond.length);
        firstReversePower[0] = secondReversePower[0] = 1;
        for (int i = Math.max(oldFirst.length, 1); i < length; i++) {
            firstReversePower[i] = firstReversePower[i - 1] * FIRST_REVERSE_MULTIPLIER % FIRST_MOD;
            secondReversePower[i]= secondReversePower[i - 1] * SECOND_REVERSE_MULTIPLIER % SECOND_MOD;
        }
    }

    public long hash(int from, int to) {
		return (((firstHash[to] - firstHash[from] + FIRST_MOD) * firstReversePower[from] % FIRST_MOD) << 32) +
            ((secondHash[to] - secondHash[from] + SECOND_MOD) * secondReversePower[from] % SECOND_MOD);
	}

	public int length() {
		return firstHash.length - 1;
	}
}
