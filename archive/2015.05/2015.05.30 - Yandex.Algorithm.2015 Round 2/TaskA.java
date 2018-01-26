package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int maxStep = in.readInt();
        int[] heights = IOUtils.readIntArray(in, count);
        long[] at = new long[count + 2];
        long[] qty = new long[count + 2];
        long[] cumulative = new long[count + 2];
        int pos = 0;
        qty[1] = 1;
        cumulative[1] = 1;
        for (int i = 0; i < count; i++) {
            at[i + 2] = at[i + 1] + heights[i];
            while (at[i + 2] - at[pos + 1] > maxStep) {
                pos++;
            }
            qty[i + 2] = cumulative[i + 1] - cumulative[pos];
            if (qty[i + 2] < 0) {
                qty[i + 2] += MOD;
            }
            cumulative[i + 2] = cumulative[i + 1] + qty[i + 2];
            if (cumulative[i + 2] >= MOD) {
                cumulative[i + 2] -= MOD;
            }
        }
        out.printLine(qty[count + 1]);
    }
}
