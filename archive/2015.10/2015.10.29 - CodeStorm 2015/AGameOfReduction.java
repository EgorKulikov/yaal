package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class AGameOfReduction {
    private static final long MOD = (long) (1e9 + 7);
    int[] value = new int[1000001];

    long[][] ways = new long[1 << 21][4];
    long[][] calc = new long[1 << 21][4];

    void init(int root, int left, int right) {
        if (left == right) {
            ways[root][value[left]] = 1;
            ways[root][0]++;
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle);
            init(2 * root + 2, middle + 1, right);
            join(ways, root);
        }
    }

    void join(long[][] data, int root) {
        join(data[root], data[2 * root + 1], data[2 * root + 2]);
    }

    void calc(int root, int left, int right, int from, int to) {
        if (left > to || right < from) {
            Arrays.fill(calc[root], 0);
            calc[root][0] = 1;
            return;
        }
        if (left >= from && right <= to) {
            System.arraycopy(ways[root], 0, calc[root], 0, 4);
            return;
        }
        int middle = (left + right) >> 1;
        calc(2 * root + 1, left, middle, from, to);
        calc(2 * root + 2, middle + 1, right, from, to);
        join(calc, root);
    }

    void join(long[] result, long[] first, long[] second) {
        Arrays.fill(result, 0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i ^ j] += first[i] * second[j];
            }
        }
        for (int i = 0; i < 4; i++) {
            result[i] %= MOD;
        }
    }

    {
        for (int i = 10; i <= 1000000; i++) {
            int ten = 10;
            int can = 0;
            while (ten <= i) {
                int up = i / (ten * 10);
                int d1 = i / ten % 10;
                int d2 = i / (ten / 10) % 10;
                int down = i % (ten / 10);
                int number = down + ten * up + ten / 10 * ((d1 + d2) % 10);
                can |= 1 << value[number];
                ten *= 10;
            }
            for (int j = 0; j < 8; j++) {
                if ((can >> j & 1) == 0) {
                    value[i] = j;
                    break;
                }
            }
        }
        init(0, 0, 1000000);
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = IOUtils.readIntArray(in, m);
        Arrays.sort(a);
        int start = 1;
        long[] result = new long[4];
        result[0] = 1;
        long[] next = new long[4];
        int value = 0;
        for (int i : a) {
            if (i != start) {
                calc(0, 0, 1000000, start, i - 1);
                join(next, result, calc[0]);
                long[] temp = result;
                result = next;
                next = temp;
            }
            value ^= this.value[i];
            start = i + 1;
        }
        if (n + 1 != start) {
            calc(0, 0, 1000000, start, n);
            join(next, result, calc[0]);
            long[] temp = result;
            result = next;
            next = temp;
        }
        long answer = result[value];
        if (value == 0) {
            answer += MOD - 1;
            answer %= MOD;
        }
        out.printLine(answer);
    }
}
