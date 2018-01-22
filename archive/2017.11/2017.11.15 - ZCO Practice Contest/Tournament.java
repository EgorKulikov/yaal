package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.InputReader.readIntArray;
import static net.egork.misc.ArrayUtils.sort;

public class Tournament {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] strength = in.readIntArray(n);
        long answer = 0;
        sort(strength);
        for (int i = 0; i < n; i++) {
            answer += (2L * i - n + 1) * strength[i];
        }
        out.printLine(answer);
    }
}
