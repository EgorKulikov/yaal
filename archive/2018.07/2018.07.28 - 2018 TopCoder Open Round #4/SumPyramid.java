package net.egork;

import static java.lang.Math.min;
import static net.egork.misc.MiscUtils.MOD7;

public class SumPyramid {
    public int countPyramids(int levels, int top) {
        int[][] c = new int[levels][levels];
        for (int i = 0; i < levels; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = min(top + 1, c[i - 1][j - 1] + c[i - 1][j]);
            }
        }
        int[] cc = c[levels - 1];
        long[] answer = new long[top + 1];
        answer[0] = 1;
        for (int i = 0; i < levels; i++) {
            for (int j = top; j >= 0; j--) {
                for (int k = j - cc[i]; k >= 0; k -= cc[i]) {
                    answer[j] += answer[k];
                }
                answer[j] %= MOD7;
            }
        }
        return (int) answer[top];
    }
}
