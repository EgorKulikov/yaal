package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class AIlyaIKrasochnayaProgulka {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] c = in.readIntArray(n);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] != c[0]) {
                answer = Math.max(answer, i);
            }
            if (c[i] != c[n - 1]) {
                answer = Math.max(answer, n - 1 - i);
            }
        }
        out.printLine(answer);
    }
}
