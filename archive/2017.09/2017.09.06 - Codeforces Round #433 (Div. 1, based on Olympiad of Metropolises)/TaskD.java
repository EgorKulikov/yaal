package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.partialSums;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int[] last1K = new int[n];
        int current = -1;
        for (int i = 0; i < n; i++) {
            last1K[i] = current;
            if (a[i] == 1000) {
                current = i;
            }
        }
        int[] next1K = new int[n];
        current = -1;
        for (int i = n - 1; i >= 0; i--) {
            next1K[i] = current;
            if (a[i] == 1000) {
                current = i;
            }
        }
        long[] sums = partialSums(a);
        long answer = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            //No partial
            long cost = sums[i + 1];
            long bonus = cost / 10;
            long pwb = sums[n] - sums[i + 1];
            if (bonus >= pwb) {
                answer = Math.min(answer, cost);
            }
            if (a[i] != 1000 && last1K[i] != -1) {
                cost = sums[i + 1] - 1000;
                bonus = cost / 10;
                pwb = sums[n] - sums[i + 1] + 1000;
                if (bonus >= pwb && sums[last1K[i]] >= 10000) {
                    answer = Math.min(answer, cost);
                }
            }
            if (next1K[i] != i + 1 && next1K[i] != -1) {
                cost = sums[i + 1] + 1000;
                bonus = cost / 10;
                pwb = sums[n] - sums[i + 1] - 1000;
                if (bonus >= pwb && sums[i + 1] / 10 >= (next1K[i] - i - 1) * 2000) {
                    answer = Math.min(answer, cost);
                }
            }
            //Partial
            cost = sums[i];
            bonus = cost / 10;
            pwb = sums[n] - sums[i + 1];
            int partial = i;
            if (bonus >= pwb) {
                long remaining = bonus - pwb;
                cost += max(0, a[partial] - remaining);
                answer = Math.min(answer, cost);
            }
            if (a[i] != 1000 && last1K[i] != -1) {
                cost = sums[i + 1] - 1000;
                bonus = cost / 10;
                pwb = sums[n] - sums[i + 1];
                partial = last1K[i];
                if (bonus >= pwb) {
                    long remaining = bonus - pwb;
                    remaining = Math.min(remaining, sums[partial] / 10);
                    cost += max(0, a[partial] - remaining);
                    answer = Math.min(answer, cost);
                }
                cost = sums[i] - 1000;
                bonus = cost / 10;
                pwb = sums[n] - sums[i + 1] + 1000;
                partial = i;
                if (bonus >= pwb && sums[last1K[i]] >= 10000) {
                    long remaining = bonus - pwb;
                    cost += max(0, a[partial] - remaining);
                    answer = Math.min(answer, cost);
                }
            }
            if (next1K[i] != i + 1 && next1K[i] != -1 && next1K[next1K[i]] != -1) {
                cost = sums[i + 1] + 1000;
                bonus = cost / 10;
                pwb = sums[n] - sums[i + 1] - 2000;
                partial = next1K[next1K[i]];
                if (bonus >= pwb && sums[i + 1] / 10 >= (next1K[i] - i - 1) * 2000) {
                    long remaining = bonus - pwb;
                    cost += max(0, a[partial] - remaining);
                    answer = Math.min(answer, cost);
                }
            }
        }
        out.printLine(answer);
    }
}
