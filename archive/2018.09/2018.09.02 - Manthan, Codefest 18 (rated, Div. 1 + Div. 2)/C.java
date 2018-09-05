package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] a = in.readCharArray(n);
        char[] b = in.readCharArray(n);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                if (i + 1 < n && a[i + 1] != b[i + 1] && a[i] != a[i + 1]) {
                    i++;
                }
                answer++;
            }
        }
        out.printLine(answer);
    }
}
