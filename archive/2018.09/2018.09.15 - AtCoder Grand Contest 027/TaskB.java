package net.egork;

import net.egork.collections.FenwickTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Math.max;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long drop = in.readInt();
        int[] x = in.readIntArray(n);
        FenwickTree tree = new FenwickTree(n);
        for (int i = 0; i < n; i++) {
            tree.add(i, x[i]);
        }
        long answer = MAX_VALUE;
        BigInteger infty = BigInteger.valueOf(MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            BigInteger current = BigInteger.valueOf((n + i) * drop);
            for (int j = 0; j * i < n; j++) {
                long by = j == 0 ? 5 : 2 * j + 3;
                current =
                        current.add(BigInteger.valueOf(tree.get(max(0, n - (j + 1) * i), n - j * i - 1)).multiply(BigInteger.valueOf(by)));
            }
            if (current.compareTo(infty) < 0) {
                answer = Math.min(answer, current.longValue());
            }
        }
        out.printLine(answer);
    }
}
