package net.egork;

import net.egork.collections.map.Counter;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        int[] b = new int[n + 1];
        for (int i = 0; i < n; i++) {
            b[i + 1] = b[i] ^ a[i];
        }
        int all = (1 << k) - 1;
        Counter<Integer> counter = new Counter<>();
        for (int i : b) {
            counter.add(min(i, all ^ i));
        }
        long answer = (long)n * (n + 1) / 2;
        for (long value : counter.values()) {
            long v1 = value >> 1;
            long v2 = (value + 1) >> 1;
            answer -= v1 * (v1 - 1) / 2;
            answer -= v2 * (v2 - 1) / 2;
        }
        out.printLine(answer);
    }
}
