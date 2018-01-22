package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MIN_VALUE;
import static net.egork.io.InputReader.readIntArray;
import static net.egork.misc.ArrayUtils.concatenate;
import static net.egork.misc.ArrayUtils.sumArray;

public class KConcatenation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        long answer = maxSum(k == 1 ? a : concatenate(a, a));
        long sum = sumArray(a);
        if (sum > 0 && k > 1) {
            long maxPrefix = 0;
            long current = 0;
            for (int i = n - 1; i >= 0; i--) {
                current += a[i];
                maxPrefix = Math.max(maxPrefix, current);
            }
            long maxSuffix = 0;
            current = 0;
            for (int i = 0; i < n; i++) {
                current += a[i];
                maxSuffix = Math.max(maxSuffix, current);
            }
            answer = Math.max(answer, maxPrefix + maxSuffix + (k - 2) * sum);
        }
        out.printLine(answer);
    }

    private long maxSum(int[] array) {
        long current = 0;
        long minPrefix = 0;
        long answer = MIN_VALUE;
        for (int i : array) {
            current += i;
            answer = Math.max(answer, current - minPrefix);
            minPrefix = Math.min(minPrefix, current);
        }
        return answer;
    }
}
