package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Arrays.sort;
import static net.egork.numbers.IntegerUtils.factorize;

public class WaysToWork {
    long[] divisors;
    int[] answer;
    Set<Key> cannot = new HashSet<>();

    static class Key {
        final int position;
        final long value;
        final long max;

        public Key(int position, long value, long max) {
            this.position = position;
            this.value = value;
            this.max = max;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Key key = (Key) o;
            return position == key.position &&
                    value == key.value &&
                    max == key.max;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, value, max);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int c = in.readInt();
        divisors = IntegerUtils.getDivisors(c).toArray();
        sort(divisors);
        int primeCount = factorize(c).size();
        answer = new int[n];
        for (long i : divisors) {
            if (can(n - 1, c, i)) {
                out.printLine(answer);
                return;
            }
        }
    }

    private boolean can(int position, long value, long max) {
        Key key = new Key(position, value, max);
        if (cannot.contains(key)) {
            return false;
        }
        if (value == 1) {
            for (int i = 0; i <= position; i++) {
                answer[i] = i + 1;
            }
            return true;
        }
        if (position == -1) {
            cannot.add(key);
            return false;
        }
        for (long i : divisors) {
            if (i > max) {
                break;
            }
            if (i == 1 && max != 1 || value % i != 0) {
                continue;
            }
            answer[position] = (int) (i + position);
            if (can(position - 1, value / i, i + 1)) {
                return true;
            }
        }
        cannot.add(key);
        return false;
    }
}
