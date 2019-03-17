package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.misc.ArrayUtils;

import static java.lang.Long.MAX_VALUE;
import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.concatenate;
import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.maxElement;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        in.readIntArrays(a, b);
        int[] all = concatenate(a, b);
        int[] order = ArrayUtils.order(all);
        int[] qty = new int[n];
        long answer = MAX_VALUE;
        int[][] variants = new int[4][];
        variants[0] = createOrder(n);
        variants[1] = createOrder(n);
        variants[1][n - 1] = n;
        variants[2] = createOrder(n);
        variants[2][n - 1] = n;
        variants[2][n - 2] = n - 1;
        variants[3] = createOrder(n);
        variants[3][n - 1] = n + 1;
        for (int[] arr : variants) {
            int left = 0;
            int right = 0;
            fill(qty, 0);
            for (int j : arr) {
                if (order[j] < n) {
                    left++;
                } else {
                    right++;
                }
                qty[order[j] % n]++;
            }
            if (left != 0 && right != 0 && maxElement(qty) == 1) {
                continue;
            }
            long current = 0;
            for (int j : arr) {
                current += all[order[j]];
            }
            answer = Math.min(answer, current);
        }
        out.printLine(answer);
    }
}
