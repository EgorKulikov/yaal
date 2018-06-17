package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.order;
import static net.egork.misc.ArrayUtils.orderBy;

public class MashaAndMinions {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        in.readIntArrays(a, b);
        orderBy(a, b);
        int[] order = order(b);
        boolean[] taken = new boolean[n];
        int size = 0;
        long sum = 0;
        int at = 0;
        while (at < n) {
            if ((sum + b[order[at]]) <= (size + 1L) * a[0]) {
                taken[order[at]] = true;
                sum += b[order[at++]];
                size++;
            } else {
                break;
            }
        }
        int answer = size;
        for (int i = 0; i < n - 1; i++) {
            if (taken[i]) {
                size--;
                sum -= b[i];
            }
            while (at < n) {
                if (order[at] <= i) {
                    at++;
                    continue;
                }
                if ((sum + b[order[at]]) <= (size + 1L) * a[i + 1]) {
                    taken[order[at]] = true;
                    sum += b[order[at++]];
                    size++;
                } else {
                    break;
                }
            }
            answer = Math.max(answer, size);
        }
        out.printLine(answer);
    }
}
