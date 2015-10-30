package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountSubarrays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = IOUtils.readIntArray(in, n);
        long answer = 0;
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                answer += (long)(i - start) * (i - start + 1) / 2;
                start = i;
            }
        }
        answer += (long)(n - start) * (n - start + 1) / 2;
        out.printLine(answer);
    }
}
