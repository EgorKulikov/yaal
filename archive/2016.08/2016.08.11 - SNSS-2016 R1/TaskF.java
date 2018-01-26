package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] c = readIntArray(in, n);
        for (int i = 0; i < n; i++) {
            int at = i;
            for (int j = i + k; j < n; j += k) {
                if (c[at] > c[j]) {
                    at = j;
                }
            }
            int temp = c[at];
            c[at] = c[i];
            c[i] = temp;
        }
        for (int i = 1; i < n; i++) {
            if (c[i - 1] > c[i]) {
                out.printLine("Unsafe");
                return;
            }
        }
        out.printLine("Safe");
    }
}
