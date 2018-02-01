package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.bitCount;
import static java.util.Arrays.fill;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = in.readIntArray(n);
        int[] qty = new int[1 << m];
        int half = m - (m >> 1);
        int other = m - half;
        int[][][] q = new int[1 << half][half + 1][1 << other];
        int[][] delta = new int[1 << half][1 << half];
        for (int i = 0; i < delta.length; i++) {
            for (int j = 0; j < delta.length; j++) {
                delta[i][j] = bitCount(i ^ j);
            }
        }
        int allOther = 1 << other;
        int[] answer = new int[m + 1];
        for (int i = 0; i < n; i++) {
            fill(answer, 0);
            int head = a[i] & ((1 << half) - 1);
            int tail = a[i] >> half;
            for (int j = 0; j <= half; j++) {
                for (int k = 0; k < allOther; k++) {
                    answer[j + delta[tail][k]] += q[head][j][k];
                }
            }
            out.printLine(answer);
            for (int j = 0; j < q.length; j++) {
                q[j][delta[j][head]][tail]++;
            }
        }
    }
}
