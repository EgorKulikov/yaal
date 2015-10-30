package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Na2aAndLuckyStone {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        long[] numbers = IOUtils.readLongArray(in, count);
        for (long i : numbers) {
            long current = i;
            while (zeroes(current) < zeroes(4 * current)) {
                current *= 4;
            }
            out.printLine(current);
        }
    }

    private int zeroes(long current) {
        int result = 0;
        while (current % 10 == 0) {
            current /= 10;
            result++;
        }
        return result;
    }
}
