package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.fill;
import static net.egork.numbers.IntegerUtils.generateBinomialCoefficients;

public class ESequenceGrowingHard {
    int m;
    long[][] ways;
    long[][] sums;
    long[][] c;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        m = in.readInt();
        ways = new long[k][n];
        fill(ways, -1);
        sums = new long[k][n];
        fill(sums, -1);
        c = generateBinomialCoefficients(n, m);
        long[] result = new long[n + 1];
        result[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                result[i] += result[j] * goSums(k - 1, i - j - 1) % m * c[i - 1][j] % m;
            }
            result[i] %= m;
        }
        out.printLine(result[n]);
    }

    private long goSums(int value, int length) {
        if (sums[value][length] != -1) {
            return sums[value][length];
        }
        sums[value][length] = go(value, length);
        if (value != 0) {
            sums[value][length] += goSums(value - 1, length);
            if (sums[value][length] >= m) {
                sums[value][length] -= m;
            }
        }
        return sums[value][length];
    }

    private long go(int value, int length) {
        if (ways[value][length] != -1) {
            return ways[value][length];
        }
        if (length == 0) {
            return ways[value][length] = 1;
        }
        if (value == 0) {
            return ways[value][length] = 0;
        }
        ways[value][length] = 0;
        for (int i = 0; i < length; i++) {
            ways[value][length] += go(value, length - i - 1) * goSums(value - 1, i) % m * c[length - 1][i] % m;
        }
        return ways[value][length] %= m;
    }
}
