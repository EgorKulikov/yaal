package net.egork.string;

import static java.util.Arrays.copyOf;
import static net.egork.string.SimpleStringHash.ensureCapacity;
import static net.egork.string.SimpleStringHash.firstReversePower;
import static net.egork.string.SimpleStringHash.secondReversePower;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ExpandableStringHash extends AbstractStringHash {
    private long[] firstHash;
    private long[] secondHash;
    private long firstPower = 1;
    private long secondPower = 1;
    private int length;
    private StringBuilder s = new StringBuilder();

    public ExpandableStringHash() {
        length = 0;
        ensureCapacity(length);
        firstHash = new long[length + 1];
        secondHash = new long[length + 1];
    }

    private void ensureLocalCapacity(int length) {
        if (firstHash.length > length) {
            return;
        }
        length = Math.max(length + 1, firstHash.length << 1);
        ensureCapacity(length);
        firstHash = copyOf(firstHash, length);
        secondHash = copyOf(secondHash, length);
    }

    public void add(char c) {
        ensureLocalCapacity(length + 1);
        firstHash[length + 1] = (firstHash[length] + c * firstPower) % FIRST_MOD;
        secondHash[length + 1] = (secondHash[length] + c * secondPower) % SECOND_MOD;
        firstPower *= MULTIPLIER;
        firstPower %= FIRST_MOD;
        secondPower *= MULTIPLIER;
        secondPower %= SECOND_MOD;
        s.append(c);
        length++;
    }

    public char charAt(int at) {
        return s.charAt(at);
    }

    public long hash(int from, int to) {
        return (((firstHash[to] - firstHash[from] + FIRST_MOD) * firstReversePower[from] % FIRST_MOD) << 32) +
                ((secondHash[to] - secondHash[from] + SECOND_MOD) * secondReversePower[from] % SECOND_MOD);
    }

    public int length() {
        return length;
    }
}
