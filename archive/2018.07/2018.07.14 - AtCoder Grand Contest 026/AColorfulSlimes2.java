package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class AColorfulSlimes2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int answer = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i - 1]) {
                answer++;
                i++;
            }
        }
        out.printLine(answer);
    }
}
