package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        IOUtils.readIntArrays(in, x, y);
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int size = Math.max(Math.abs(x[i] - x[j]), Math.abs(y[i] - y[j]));
                answer = Math.min(answer, size * size);
            }
        }
        out.printLine(answer);
    }
}
