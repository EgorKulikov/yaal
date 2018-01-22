package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static net.egork.io.InputReader.readIntTable;

public class MaximumScore {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] a = in.readIntTable(n, n);
        int max = MAX_VALUE;
        long answer = 0;
        for (int i = n - 1; i >= 0; i--) {
            int best = 0;
            for (int j : a[i]) {
                if (j < max && j > best) {
                    best = j;
                }
            }
            if (best == 0) {
                out.printLine(-1);
                return;
            }
            max = best;
            answer += best;
        }
        out.printLine(answer);
    }
}
