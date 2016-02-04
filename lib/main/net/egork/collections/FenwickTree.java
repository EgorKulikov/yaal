package net.egork.collections;

/**
 * @author egor@egork.net
 */
public class FenwickTree {
    private final long[] value;

    public FenwickTree(int size) {
        value = new long[size];
    }

    public long get(int from, int to) {
        return get(to) - get(from - 1);
    }

    private long get(int to) {
        to = Math.min(to, value.length - 1);
        long result = 0;
        while (to >= 0) {
            result += value[to];
            to = (to & (to + 1)) - 1;
        }
        return result;
    }

    public void add(int at, long value) {
        while (at < this.value.length) {
            this.value[at] += value;
            at = at | (at + 1);
        }
    }
}
