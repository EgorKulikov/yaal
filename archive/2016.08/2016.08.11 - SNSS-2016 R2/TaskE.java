package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

import static java.lang.Math.abs;
import static java.math.BigInteger.valueOf;
import static net.egork.io.IOUtils.readIntArray;

public class TaskE {
    static final long MOD = 998244353;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] h = readIntArray(in, n);
        int[] k = readIntArray(in, q);
        long[] result = new long[n];
        long left = 0;
        long right = 0;
        int at = 0;
        int height = 0;
        long answer = 0;
        for (int i = 0; i < n; i++) {
            long dx = h[i] - height;
            right += dx;
            answer += dx * (dx + 1) / 2;
            answer %= MOD;
            while (right - left > (i + 1L) * (h[at] - height)) {
                long x = h[at] - height;
                answer += left % MOD * x % MOD;
                answer += x * (x - 1) / 2 % MOD * (i + 1) % MOD;
                answer -= right % MOD * x % MOD;
                answer %= MOD;
                if (answer < 0) {
                    answer += MOD;
                }
                left += at * x;
                right -= (i + 1 - at) * x;
                left++;
                at++;
                height += x;
            }
            long x = (right - left + i) / (i + 1);
            answer += left % MOD * x % MOD;
            answer += x * (x - 1) / 2 % MOD * (i + 1) % MOD;
            answer -= right % MOD * x % MOD;
            answer %= MOD;
            if (answer < 0) {
                answer += MOD;
            }
            left += at * x;
            right -= (i + 1 - at) * x;
            height += x;
            result[i] = answer;
        }
        for (int i : k) {
            out.printLine(result[i - 1]);
        }
    }

    BigInteger check(int[] h, int height) {
        BigInteger result = BigInteger.ZERO;
        for (int i : h) {
            long dx = abs(i - height);
            result = result.add(valueOf(dx * (dx + 1) / 2));
        }
        return result;
    }
}
