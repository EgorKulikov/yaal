package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    private static final long MOD = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int length = in.readInt();
        int[] count = IOUtils.readIntArray(in, 10);
        long[][] c = IntegerUtils.generateBinomialCoefficients(length, MOD);
        long answer = 0;
        for (int i = 1; i < 10; i++) {
            int[] curCount = count.clone();
            curCount[i] = Math.max(0, curCount[i] - 1);
            long[] result = new long[length];
            result[0] = 1;
            long[] next = new long[length];
            for (int j = 0; j < 10; j++) {
                Arrays.fill(next, 0);
                for (int k = 0; k < length - curCount[j]; k++) {
                    result[k] %= MOD;
                    for (int l = k + curCount[j]; l < length; l++)
                        next[l] += result[k] * c[l][k] % MOD;
                }
                long[] temp = next;
                next = result;
                result = temp;
            }
            for (int j = 0; j < length; j++)
                answer += result[j] % MOD;
        }
        answer %= MOD;
        out.printLine(answer);
	}
}
