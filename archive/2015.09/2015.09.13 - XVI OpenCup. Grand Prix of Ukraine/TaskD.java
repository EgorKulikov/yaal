package net.egork;

import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] x = IOUtils.readIntArray(in, n + 1);
        Counter<Integer> counter = new Counter<>();
        int mod = x[n] / k;
        for (int i = 0; i < n; i++) {
            counter.add(x[i] % mod);
        }
        for (long val : counter.values()) {
            if (val >= k) {
                out.printLine(1);
                return;
            }
        }
        out.printLine(0);
    }
}
