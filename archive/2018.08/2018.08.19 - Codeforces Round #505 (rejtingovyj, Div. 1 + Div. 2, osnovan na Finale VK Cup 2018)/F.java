package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.util.Arrays.sort;

public class F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] x = new long[n];
        long[] y = new long[n];
        in.readLongArrays(x, y);
        long answer = 0;
        for (int i = 0; i < n; i++) {
            double[] angle = new double[n - 1];
            int at = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                angle[at++] = atan2(y[j] - y[i], x[j] - x[i]);
            }
            sort(angle);
            int k = 0;
            for (int j = 0; j < n - 1; j++) {
                k = Math.max(k, j + 1);
                while (k < n - 1 + j && normalize(angle[k % (n - 1)] - angle[j]) < PI) {
                    k++;
                }
                long up = k - j - 1;
                long down = n - 2 - up;
                answer += up * (up - 1) / 2 * down * (down - 1) / 2;
            }
        }
        answer /= 2;
        out.printLine(answer);
    }

    private double normalize(double v) {
        if (v < 0) {
            v += 2 * PI;
        }
        return v;
    }
}
