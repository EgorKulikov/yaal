package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class FairRations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] b = readIntArray(in, n);
        int answer = 0;
        for (int i = 0; i < n - 1; i++) {
            if (b[i] % 2 == 1) {
                b[i + 1]++;
                answer += 2;
            }
        }
        if (b[n - 1] % 2 == 0) {
            out.printLine(answer);
        } else {
            out.printLine("NO");
        }
    }
}
