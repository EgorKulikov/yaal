package on2019_03.on2019_03_16_AtCoder_Grand_Contest_031.B___Reversi;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.misc.MiscUtils.MOD7;

public class BReversi {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] c = in.readIntArray(n);
        long[] add = new long[maxElement(c) + 1];
        long ways = 1;
        for (int i = 0; i < n; i++) {
            if (i != n - 1 && c[i + 1] == c[i]) {
                continue;
            }
            long wasWays = ways;
            ways += add[c[i]];
            add[c[i]] += wasWays;
            add[c[i]] %= MOD7;
            ways %= MOD7;
        }
        out.printLine(ways);
    }
}
