package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] h = new int[3];
        int[] m = new int[3];
        in.readIntArrays(h, m);
        int n = in.readInt();
        int[] length = new int[3];
        for (int i = 0; i < 3; i++) {
            length[i] = 60 * h[i] + m[i];
        }
        long numerator = length[0] * length[1] * length[2];
        long denominator = 60 * (length[0] * length[1] + length[0] * length[2] + length[1] * length[2]);
        long[] digit = new long[n + 2];
        for (int i = 0; i < n + 2; i++) {
            digit[i] = numerator / denominator;
            numerator %= denominator;
            numerator *= 10;
        }
        if (digit[n + 1] >= 5) {
            digit[n]++;
        }
        out.print(digit[0]);
        if (n > 0) {
            out.print('.');
            for (int i = 1; i <= n; i++) {
                out.print(digit[i]);
            }
        }
        out.printLine();
    }
}
