package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        if (n == 0) {
            out.printLine(0);
            return;
        }
        int ones = Long.bitCount(n);
        int zeroes = Long.bitCount(Long.highestOneBit(n) - 1) - ones + 1;
        long answer = (1L << (ones - 1)) - 1 + (1L << (ones + zeroes - 1));
        out.printLine(answer);
    }
}
