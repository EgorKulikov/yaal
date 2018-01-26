package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ContestStrategy {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int explanation = in.readInt();
        int[] time = IOUtils.readIntArray(in, count);
        ArrayUtils.sort(time, IntComparator.REVERSE);
        long left = time[0];
        long right = time[0] + (long)(count - 1) * explanation;
        while (left < right) {
            long middle = (left + right) >> 1;
            long curTime = 0;
            int explained = 1;
            int solved = 0;
            while (solved < explained) {
                while (solved < explained && time[solved] + curTime + explanation > middle) {
                    solved++;
                }
                curTime += explanation;
                explained += explained - solved;
                explained = Math.min(explained, count);
            }
            if (solved == count) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine(left);
    }
}
