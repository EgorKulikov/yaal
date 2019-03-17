package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.partialSums;
import static net.egork.misc.ArrayUtils.reverse;

public class BTreeBurning {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long l = in.readInt();
        int n = in.readInt();
        int[] x = in.readIntArray(n);
        long answer = 0;
        for (int j = 0; j < 2; j++) {
            long[] sums = partialSums(x);
            for (int i = 0; i < n; i++) {
                int last = (n + i) >> 1;
                int forward = (n + i - 1) >> 1;
                long current = 2 * (sums[forward + 1] - sums[i] + (n - forward - 1) * l - (sums[n] - sums[forward + 1]));
                if (((n - i) & 1) == 0) {
                    current -= l - x[last];
                } else {
                    current -= x[last];
                }
                answer = Math.max(answer, current);
            }
            reverse(x);
            for (int i = 0; i < n; i++) {
                x[i] = (int) (l - x[i]);
            }
        }
        out.printLine(answer);
    }
}
