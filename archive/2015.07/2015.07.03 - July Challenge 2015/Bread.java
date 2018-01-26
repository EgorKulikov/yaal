package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Bread {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        long size = in.readLong();
        int[] days = IOUtils.readIntArray(in, count);
        long answer = 0;
        long inCurrent = 0;
        for (int i : days) {
            inCurrent--;
            if (inCurrent <= 0) {
                inCurrent = size;
                answer++;
            }
            if (i <= inCurrent) {
                inCurrent -= i;
            } else {
                answer += (i - inCurrent + size - 1) / size;
                inCurrent = size - (i - inCurrent) % size;
                if (inCurrent == size) {
                    inCurrent = 0;
                }
            }
        }
        out.printLine(answer);
    }
}
