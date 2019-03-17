package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.ArrayUtils.order;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] l = in.readIntArray(n);
        int[] r = in.readIntArray(n);
        int[] answer = new int[n];
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = l[i] + r[i];
        }
        int[] order = order(sum);
        int last = 3 * n;
        int value = n + 1;
        for (int i : order) {
            if (sum[i] != last) {
                last = sum[i];
                value--;
            }
            answer[i] = value;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (answer[i] > answer[j]) {
                    r[j]--;
                } else if (answer[i] < answer[j]) {
                    l[i]--;
                }
            }
        }
        if (count(l, 0) == n && count(r, 0) == n) {
            out.printLine("YES");
            out.printLine(answer);
            return;
        }
        out.printLine("NO");
    }
}
