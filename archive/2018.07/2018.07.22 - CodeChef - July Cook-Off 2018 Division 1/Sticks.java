package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.ArrayUtils.unique;

public class Sticks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        sort(a);
        int[] uni = unique(a);
        int[] qty = new int[uni.length];
        int at = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != uni[at]) {
                at++;
            }
            qty[at]++;
        }
        int[] next3 = new int[uni.length];
        int current = uni.length;
        for (int i = uni.length - 1; i >= 0; i--) {
            next3[i] = current;
            if (qty[i] >= 3) {
                current = i;
            }
        }
        long answer = MAX_VALUE;
        int taken = uni.length;
        if (taken >= k) {
            answer = -1;
        } else {
            int last = -1;
            int preLast = -1;
            for (int i = 0; i < uni.length; i++) {
                if (k - taken == 2 && next3[i] != uni.length) {
                    answer = Math.min(answer, (long)last * uni[next3[i]]);
                }
                int cur = min(qty[i] - 1, k - taken);
                taken += cur;
                if (cur > 0) {
                    preLast = last;
                    last = uni[i];
                }
                if (cur > 2) {
                    preLast = uni[i];
                }
                if (taken == k) {
                    break;
                }
                if (k - taken == 2 && next3[i] != uni.length) {
                    answer = Math.min(answer, (long)last * uni[next3[i]]);
                }
            }
            answer = Math.min(answer, (long)last * preLast);
            if (preLast < 0) {
                answer = -1;
            }
        }
        if (answer < 0) {
            answer = -1;
        }
        out.printLine(answer);
    }
}
