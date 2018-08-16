package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.order;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int p = in.readInt();
        int[] s = new int[n];
        int[] len = new int[n];
        int[] t = new int[n];
        in.readIntArrays(s, len, t);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (s[i] < s[j] && s[i] + len[i] > s[j]) {
                        out.printLine("NO");
                        return;
                    }
                }
            }
        }
        int[] nextFree = createOrder(10000001);
        int[] was = new int[10000001];
        int[] order = order(s);
        for (int i = n - 1; i >= 0; i--) {
            was[s[i]] = 1 << i;
            int end = s[order[i]] + len[order[i]];
            for (int j = s[order[i]]; j < end; j++) {
                nextFree[j] = nextFree[end];
            }
        }
        for (int i = 1; i <= 10000000; i++) {
            was[i] |= was[i - 1];
        }
        int[] best = new int[1 << n];
        fill(best, MAX_VALUE / 2);
        best[0] = 1;
        int[] last = new int[1 << n];
        last[0] = -1;
        int[] time = new int[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            if (best[i] == MAX_VALUE / 2) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1 || best[i] + t[j] >= s[j]) {
                    continue;
                }
                int nMask = i + (1 << j);
                int cTime = best[i];
                while (cTime + t[j] < s[j] && (was[cTime] & i) != (was[cTime + t[j]] & i)) {
                    for (int k = 0; k < n; k++) {
                        if ((i >> k & 1) == 1 && s[k] > cTime && s[k] <= cTime + t[j]) {
                            cTime = nextFree[s[k]];
                        }
                    }
                }
                if (cTime + t[j] >= s[j]) {
                    continue;
                }
                if (cTime + t[j] < best[nMask]) {
                    best[nMask] = nextFree[cTime + t[j]];
                    last[nMask] = j;
                    time[nMask] = cTime;
                }
            }
        }
        int[] id = new int[n];
        int[] at = new int[n];
        int start = 0;
        int end = (1 << n) - 1;
        if (p == 1) {
            start = end;
        }
        for (int i = start; i <= end; i++) {
            if (best[i] < MAX_VALUE / 2 && best[end - i] < MAX_VALUE / 2) {
                out.printLine("YES");
                int cMask = i;
                while (cMask != 0) {
                    int current = last[cMask];
                    id[current] = 1;
                    at[current] = time[cMask];
                    cMask -= 1 << current;
                }
                cMask = end - i;
                while (cMask != 0) {
                    int current = last[cMask];
                    id[current] = 2;
                    at[current] = time[cMask];
                    cMask -= 1 << current;
                }
                for (int j = 0; j < n; j++) {
                    out.printLine(id[j], at[j]);
                }
                return;
            }
        }
        out.printLine("NO");
    }
}
