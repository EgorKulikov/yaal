package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MaximizeAlternateSumSubarray {
    State[] data;
    State[] result;

    static class State {
        long max;
        long oddSuff;
        long evenSuff;
        long maxPref;
        long minPref;
        long sum;
        boolean nullState;

        void leaf(int left, long val) {
            if (left % 2 == 0) {
                evenSuff = val;
                oddSuff = Long.MIN_VALUE / 2;
            } else {
                evenSuff = Long.MIN_VALUE / 2;
                oddSuff = val;
            }
            max = Math.max(evenSuff, oddSuff);
            sum = left % 2 == 0 ? val : -val;
            maxPref = Math.max(0, sum);
            minPref = Math.min(0, sum);
            nullState = false;
        }

        void join(State left, State right) {
            if (left.nullState) {
                copyFrom(right);
            } else if (right.nullState) {
                copyFrom(left);
            } else {
                max = Math.max(Math.max(left.max, right.max), Math.max(left.evenSuff + right.maxPref, left.oddSuff - right.minPref));
                oddSuff = Math.max(left.oddSuff - right.sum, right.oddSuff);
                evenSuff = Math.max(left.evenSuff + right.sum, right.evenSuff);
                maxPref = Math.max(left.maxPref, left.sum + right.maxPref);
                minPref = Math.min(left.minPref, left.sum + right.minPref);
                sum = left.sum + right.sum;
            }
            nullState = false;
        }

        void copyFrom(State state) {
            max = state.max;
            oddSuff = state.oddSuff;
            evenSuff = state.evenSuff;
            maxPref = state.maxPref;
            minPref = state.minPref;
            sum = state.sum;
            nullState = false;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] c = IOUtils.readIntArray(in, n);
        data = new State[4 * n];
        result = new State[4 * n];
        init(0, 0, n - 1, c);
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            char type = in.readCharacter();
            if (type == 'U') {
                int position = in.readInt() - 1;
                int value = in.readInt();
                update(0, 0, n - 1, position, value);
            } else {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                query(0, 0, n - 1, from, to);
                out.printLine(result[0].max);
            }
        }
    }

    private void query(int root, int left, int right, int from, int to) {
        if (right < from || left > to) {
            result[root].nullState = true;
            return;
        }
        if (left >= from && right <= to) {
            result[root].copyFrom(data[root]);
            return;
        }
        int middle = (left + right) >> 1;
        query(2 * root + 1, left, middle, from, to);
        query(2 * root + 2, middle + 1, right, from, to);
        result[root].join(result[2 * root + 1], result[2 * root + 2]);
    }

    private void update(int root, int left, int right, int position, int value) {
        if (position < left || position > right) {
            return;
        }
        if (left == right) {
            data[root].leaf(left, value);
            return;
        }
        int middle = (left + right) >> 1;
        update(2 * root + 1, left, middle, position, value);
        update(2 * root + 2, middle + 1, right, position, value);
        data[root].join(data[2 * root + 1], data[2 * root + 2]);
    }

    private void init(int root, int left, int right, int[] c) {
        data[root] = new State();
        result[root] = new State();
        if (left == right) {
            data[root].leaf(left, c[left]);
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, c);
            init(2 * root + 2, middle + 1, right, c);
            data[root].join(data[2 * root + 1], data[2 * root + 2]);
        }
    }
}
