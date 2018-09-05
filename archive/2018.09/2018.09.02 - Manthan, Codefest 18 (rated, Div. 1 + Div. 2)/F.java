package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;

public class F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        int[] b = new int[n - k + 1];
        int[] queue = new int[n];
        int start = 0;
        int end = 0;
        for (int i = 0; i < k - 1; i++) {
            while (start < end && a[queue[end - 1]] <= a[i]) {
                end--;
            }
            queue[end++] = i;
        }
        for (int i = k - 1; i < n; i++) {
            while (start < end && a[queue[end - 1]] <= a[i]) {
                end--;
            }
            queue[end++] = i;
            if (queue[start] == i - k) {
                start++;
            }
            b[i - k + 1] = a[queue[start]];
        }
        long answer = 0;
        int[] c = new int[n];
        for (int i = 1; i < k; i++) {
            int at = b.length - i;
            int length = 0;
            for (int pos = at; pos >= 0; pos -= k - 1) {
                c[length++] = b[pos];
            }
            end = 0;
            long currentSum = 0;
            for (int j = 0; j < length; j++) {
                while (0 < end && c[queue[end - 1]] <= c[j]) {
                    long last = end == 1 ? -1 : queue[end - 2];
                    currentSum -= c[queue[end - 1]] * (queue[end - 1] - last);
                    currentSum %= MOD7;
                    end--;
                }
                long last = end == 0 ? -1 : queue[end - 1];
                currentSum += c[j] * (j - last);
                currentSum %= MOD7;
                answer += currentSum;
                queue[end++] = j;
            }
        }
        answer %= MOD7;
        if (answer < 0) {
            answer += MOD7;
        }
        out.printLine(answer);
    }
}
