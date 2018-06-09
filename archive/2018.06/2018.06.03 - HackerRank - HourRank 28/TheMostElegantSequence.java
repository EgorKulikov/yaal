package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.bitCount;
import static java.lang.Integer.highestOneBit;

public class TheMostElegantSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] b = in.readIntArray(n);
        String[] s = in.readStringArray(n);
        int[][] cost = new int[n][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                cost[i][s[i].charAt(j) - '0']++;
            }
        }
        int[][] answer = new int[1 << n][n];
        int[][] total = new int[1 << n][10];
        boolean[] valid = new boolean[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            int one = bitCount(highestOneBit(i) - 1);
            int remaining = i - (1 << one);
            for (int j = 0; j < 10; j++) {
                total[i][j] = total[remaining][j] + cost[one][j];
                if (total[i][j] > q) {
                    valid[i] = true;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!valid[1 << i]) {
                answer[1 << i][i] = b[i];
                result = Math.max(result, b[i]);
            }
        }
        for (int i = 1; i < (1 << n); i++) {
            if (valid[i] || bitCount(i) == 1) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (j == k || (i >> k & 1) == 0) {
                        continue;
                    }
                    answer[i][j] = Math.max(answer[i][j], answer[i - (1 << j)][k] + (b[j] ^ b[k]));
                }
                result = Math.max(result, answer[i][j]);
            }
        }
        out.printLine(result);
    }
}
