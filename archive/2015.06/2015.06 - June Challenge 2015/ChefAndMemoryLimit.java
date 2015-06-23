package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndMemoryLimit {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] memory = IOUtils.readIntArray(in, count);
        long answer = memory[0];
        for (int i = 1; i < count; i++) {
            answer += Math.max(memory[i] - memory[i - 1], 0);
        }
        out.printLine(answer);
    }
}
