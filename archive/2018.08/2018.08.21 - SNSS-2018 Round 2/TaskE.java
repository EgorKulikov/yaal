package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.createArray;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] b = new int[n];
        int[] p = new int[n];
        in.readIntArrays(b, p);
        int[] answer = createArray(m + 2, MAX_VALUE / 2);
        answer[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= 0; j--) {
                answer[min(j + b[i], m + 1)] = min(answer[min(j + b[i], m + 1)], answer[j] + p[i]);
            }
        }
        out.printLine(answer[m + 1]);
    }
}
