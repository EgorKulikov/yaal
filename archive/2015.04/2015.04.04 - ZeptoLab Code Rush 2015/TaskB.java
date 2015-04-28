package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int depth = in.readInt();
        int[] qty = IOUtils.readIntArray(in, (1 << (depth + 1)) - 2);
        int answer = 0;
        for (int i = (1 << depth) - 3; i >= -1; i--) {
            int left = qty[2 * i + 2];
            int right = qty[2 * i + 3];
            answer += Math.abs(left - right);
            if (i >= 0) {
                qty[i] += Math.max(left, right);
            }
        }
        out.printLine(answer);
    }
}
