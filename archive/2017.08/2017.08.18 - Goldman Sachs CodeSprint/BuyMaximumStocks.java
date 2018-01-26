package net.egork;



import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class BuyMaximumStocks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] arr = readIntArray(in, n);
        long k = in.readLong();
        int[] order = ArrayUtils.order(arr);
        long answer = 0;
        for (int i : order) {
            long want = k / arr[i];
            want = Math.min(want, i + 1);
            answer += want;
            k -= want * arr[i];
        }
        out.printLine(answer);
    }
}
