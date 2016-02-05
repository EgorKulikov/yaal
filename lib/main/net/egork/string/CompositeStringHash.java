package net.egork.string;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public class CompositeStringHash extends AbstractStringHash {
    private static long[] firstPower = new long[0];
    private static long[] secondPower = new long[0];

    private final StringHash first;
    private final StringHash second;

    public CompositeStringHash(StringHash first, StringHash second) {
        this.first = first;
        this.second = second;
        ensureCapacity(first.length() + 1);
    }

    private void ensureCapacity(int length) {
        if (firstPower.length >= length) {
            return;
        }
        length = Math.max(length + 1, firstPower.length << 1);
        long[] oldFirst = firstPower;
        long[] oldSecond = secondPower;
        firstPower = new long[length];
        secondPower = new long[length];
        System.arraycopy(oldFirst, 0, firstPower, 0, oldFirst.length);
        System.arraycopy(oldSecond, 0, secondPower, 0, oldSecond.length);
        firstPower[0] = secondPower[0] = 1;
        for (int i = Math.max(oldFirst.length, 1); i < length; i++) {
            firstPower[i] = firstPower[i - 1] * MULTIPLIER % FIRST_MOD;
            secondPower[i] = secondPower[i - 1] * MULTIPLIER % SECOND_MOD;
        }
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
            long value = first.hash(from, Math.min(to, first.length()));
            firstFirst = value >>> 32;
            firstSecond = value & ((1L << 32) - 1);
        }
        return (((firstFirst + secondFirst * firstPower[Math.max(0, first.length() - from)]) % FIRST_MOD) << 32) +
                ((firstSecond + secondSecond * secondPower[Math.max(0, first.length() - from)]) % SECOND_MOD);
    }

    public int length() {
        return first.length() + second.length();
    }
}
