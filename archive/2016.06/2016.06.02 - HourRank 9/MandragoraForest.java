package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.sort;
import static net.egork.io.IOUtils.readIntArray;

public class MandragoraForest {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] h = readIntArray(in, n);
        sort(h);
        long answer = 0;
        long sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += h[i];
            answer = Math.max(answer, sum * (i + 1));
        }
        out.printLine(answer);
    }
}
