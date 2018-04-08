package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.bitCount;
import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class TaskE {
    int[] sign = new int[1 << 20];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] x = in.readIntArray(n);
        int[] y = in.readIntArray(m);
        for (int i = 0; i < sign.length; i++) {
            if (bitCount(i) % 2 == 1) {
                sign[i] = 1;
            } else {
                sign[i] = -1;
            }
        }
        int[] qtyX = build(x);
        int[] qtyY = build(y);
        int[] answer = new int[20];
        for (int i = 19; i >= 0; i--) {
            int current = n + m;
            for (int j = (1 << i); j < (1 << 20); j += (1 << (i + 1))) {
                int total = min(qtyX[j], qtyY[j]);
                for (int k = i + 1; k < 20; k++) {
                    if ((j >> k & 1) == 1) {
                        total -= answer[k];
                    }
                }
                current = Math.min(current, total);
            }
            answer[i] = current;
        }
        long result = 0;
        for (int i = 0; i < 20; i++) {
            result += (1L << i) * answer[i];
        }
        out.printLine(result);
    }

    int all = (1 << 20) - 1;

    private int[] build(int[] x) {
        int[] result = new int[1 << 20];
        int[] q = new int[1 << 20];
        fill(result, 1, 1 << 20, x.length);
        for (int i : x) {
            q[i]++;
        }
        for (int i = 0; i < (1 << 20); i++) {
            if (q[i] == 0) {
                continue;
            }
            int rev = all - i;
            for (int j = rev; j != 0; j = (j - 1) & rev) {
                result[j] -= q[i];
            }
        }
        return result;
    }
}
