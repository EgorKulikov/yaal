package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int queryCount = in.readInt();
        int[] array = IOUtils.readIntArray(in, count);
        SpecialIntervalTree tree = new SpecialIntervalTree(array);
        for (int i = 0; i < queryCount; i++) {
            char type = in.readCharacter();
            if (type == '?') {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                int index = in.readInt();
                out.printLine(tree.query(from, to, index));
            } else {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                int delta = in.readInt();
                if (delta < 0)
                    throw new RuntimeException();
                tree.update(from, to, delta);
            }
        }
    }

    static class SpecialIntervalTree {
        private final int[] array;
        protected int size;
        protected long[][] value;
        protected long[] delta;
        protected long[][] power;
        protected long[][] sumPower;
        protected long[][] result;
        protected long[] total = new long[6];
        protected long[][] c = IntegerUtils.generateBinomialCoefficients(6);
        protected long[] shift = new long[6];

        protected SpecialIntervalTree(int[] array) {
            this.array = array;
            this.size = array.length;
            int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
            value = new long[6][nodeCount];
            delta = new long[nodeCount];
            power = new long[6][size];
            Arrays.fill(power[0], 1);
            for (int i = 1; i < 6; i++) {
                for (int j = 0; j < size; j++)
                    power[i][j] = power[i - 1][j] * (j + 1) % MOD;
            }
            sumPower = new long[6][size + 1];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < size; j++) {
                    sumPower[i][j + 1] = sumPower[i][j] + power[i][j];
                    if (sumPower[i][j + 1] >= MOD)
                        sumPower[i][j + 1] -= MOD;
                }
            }
            result = new long[6][nodeCount];
            init();
        }

        protected long initValue(int index) {
            return array[index];
        }

        public void init() {
            init(0, 0, size - 1);
        }

        private void init(int root, int left, int right) {
            if (left == right) {
                for (int i = 0; i < 6; i++)
                    value[i][root] = power[i][left] * initValue(left) % MOD;
                delta[root] = -1;
            } else {
                int middle = (left + right) >> 1;
                init(2 * root + 1, left, middle);
                init(2 * root + 2, middle + 1, right);
                join(root);
                delta[root] = -1;
            }
        }

        private void join(int root) {
            for (int i = 0; i < 6; i++) {
                value[i][root] = value[i][2 * root + 1] + value[i][2 * root + 2];
                if (value[i][root] >= MOD)
                    value[i][root] -= MOD;
            }
        }

        private void apply(int root, int left, int right) {
            if (delta[root] == -1)
                throw new RuntimeException();
            for (int i = 0; i < 6; i++) {
               value[i][root] = (sumPower[i][right + 1] - sumPower[i][left]) * delta[root] % MOD;
                if (value[i][root] < 0)
                    value[i][root] += MOD;
            }
        }

        public void update(int from, int to, long delta) {
            update(0, 0, size - 1, from, to, delta);
        }

        private void update(int root, int left, int right, int from, int to, long delta) {
            if (left > to || right < from)
                return;
            if (left >= from && right <= to) {
                this.delta[root] = delta;
                apply(root, left, right);
                return;
            }
            int middle = (left + right) >> 1;
            if (this.delta[root] != -1) {
                this.delta[2 * root + 1] = this.delta[root];
                apply(2 * root + 1, left, middle);
                this.delta[2 * root + 2] = this.delta[root];
                apply(2 * root + 2, middle + 1, right);
            }
            this.delta[root] = -1;
            update(2 * root + 1, left, middle, from, to, delta);
            update(2 * root + 2, middle + 1, right, from, to, delta);
            join(root);
        }

        public long query(int from, int to, int index) {
            query(0, 0, size - 1, from, to);
            shift[0] = 1;
            total[0] = result[0][0];
            for (int i = 1; i < 6; i++) {
                shift[i] = (shift[i - 1] * from) % MOD;
                total[i] = result[i][0];
                for (int j = 0; j < i; j++)
                    total[i] -= shift[i - j] * total[j] % MOD * c[i][j];
                total[i] %= MOD;
                if (total[i] < 0)
                    total[i] += MOD;
            }
            return total[index];
        }

        private void query(int root, int left, int right, int from, int to) {
            if (left > to || right < from) {
                for (int i = 0; i < 6; i++)
                    result[i][root] = 0;
                return;
            }
            if (left >= from && right <= to) {
                for (int i = 0; i < 6; i++)
                    result[i][root] = value[i][root];
                return;
            }
            int middle = (left + right) >> 1;
            if (this.delta[root] != -1) {
                this.delta[2 * root + 1] = this.delta[root];
                apply(2 * root + 1, left, middle);
                this.delta[2 * root + 2] = this.delta[root];
                apply(2 * root + 2, middle + 1, right);
            }
            this.delta[root] = -1;
            query(2 * root + 1, left, middle, from, to);
            query(2 * root + 2, middle + 1, right, from, to);
            for (int i = 0; i < 6; i++) {
                result[i][root] = result[i][2 * root + 1] + result[i][2 * root + 2];
                if (result[i][root] >= MOD)
                    result[i][root] -= MOD;
            }
        }
    }
}
