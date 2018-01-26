package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class AditiAndMagicTrick {
    long[] fib = IntegerUtils.generateFibonacci(1000000000000000000L);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        int pos = Arrays.binarySearch(fib, n);
        if (pos < 0) {
            pos = -pos - 2;
        }
        out.printLine(pos);
    }
}
