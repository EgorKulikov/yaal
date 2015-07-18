package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndHisFriend {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double[] intervals = IOUtils.readDoubleArray(in, 2);
        double[] wait = IOUtils.readDoubleArray(in, 2);
        double answer = 0;
        for (int i = 0; i < 2; i++) {
            double start = wait[i];
            double end = Math.min(intervals[i] + wait[i], intervals[1 - i]);
            if (start > end) {
                continue;
            }
            double valueAtStart = intervals[1 - i] - wait[i];
            double delta = end - start;
            answer += delta * (2 * valueAtStart - delta) / 2;
        }
        for (double d : intervals) {
            answer /= d;
        }
        out.printLine(1 - answer);
    }
}
