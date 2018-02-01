package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.ArrayUtils.maxElement;

public class Road {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[][] answer = new int[51][k + 1];
        fill(answer, MIN_VALUE);
        for (int i = 0; i < n; i++) {
            int a = in.readInt();
            if (i == 0) {
                answer[a][0] = 1;
                continue;
            }
            for (int l = 0; l <= k; l++) {
                answer[a][l]++;
                for (int j = 0; j <= 50; j++) {
                    if (j != a && l >= abs(j - a)) {
                        answer[a][l] = Math.max(answer[a][l], answer[j][l - abs(j - a)] + 1);
                    }
                }
            }
        }
        int result = 0;
        for (int[] row : answer) {
            result = Math.max(result, maxElement(row));
        }
        out.printLine(result);
    }
}
