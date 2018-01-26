package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.bitCount;
import static java.lang.Long.lowestOneBit;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong() + 1;
        int k = in.readInt();
        while (bitCount(n) > k) {
            n += lowestOneBit(n);
        }
        for (int i = 0; i < k && bitCount(n) < k; i++) {
            if ((n >> i & 1) == 0) {
                n += 1L << i;
            }
        }
        out.printLine(n);
    }
}
