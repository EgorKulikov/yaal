package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int by = 1;
        long answer = 1;
        for (int i = n - 2; i >= 0; i--) {
            int x = (int) (((long)a[i] * by + a[i + 1] - 1) / a[i + 1]);
            answer += x;
            by = x;
        }
        out.printLine(answer);
    }
}
