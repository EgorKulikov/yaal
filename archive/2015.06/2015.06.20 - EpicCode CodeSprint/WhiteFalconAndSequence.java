package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class WhiteFalconAndSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        long[] array = IOUtils.readLongArray(in, count);
        long answer = Long.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (i != 0 && j != count - 1) {
                    continue;
                }
                long current = 0;
                long min = 0;
                for (int a = i, b = j; a < b; a++, b--) {
                    current += array[a] * array[b];
                    answer = Math.max(answer, current - min);
                    min = Math.min(min, current);
                }
            }
        }
        out.printLine(answer);
    }
}
