package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SumOfAbsolutes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        int[] a = IOUtils.readIntArray(in, size);
        long[] sums = ArrayUtils.partialSums(a);
        for (int i = 0; i < count; i++) {
            int from = in.readInt() - 1;
            int to = in.readInt();
            if ((sums[from] & 1) == (sums[to] & 1)) {
                out.printLine("Even");
            } else {
                out.printLine("Odd");
            }
        }
    }
}
