package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class MaximumProfit {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] price = IOUtils.readIntArray(in, count);
        int min = Integer.MAX_VALUE;
        int answer = 0;
        for (int i : price) {
            min = Math.min(min, i);
            answer = Math.max(answer, i - min);
        }
        out.printLine(answer);
    }
}
