package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.misc.ArrayUtils;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.sumArray;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        if (n < 2 * (n - 1 - m)) {
            out.printLine("Impossible");
            return;
        }
        if (m == n - 1) {
            out.printLine(0);
            return;
        }
        int[] a = in.readIntArray(n);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        for (int i = 0; i < m; i++) {
            setSystem.join(in.readInt(), in.readInt());
        }
        int[] smallest = createArray(n, -1);
        for (int i = 0; i < n; i++) {
            int id = setSystem.get(i);
            if (smallest[id] == -1 || a[smallest[id]] > a[i]) {
                smallest[id] = i;
            }
        }
        boolean[] used = new boolean[n];
        int[] all = new int[2 * (n - 1 - m)];
        int at = 0;
        for (int i = 0; i < n; i++) {
            if (smallest[i] != -1) {
                used[smallest[i]] = true;
                all[at++] = a[smallest[i]];
            }
        }
        int[] order = ArrayUtils.order(a);
        for (int i : order) {
            if (at == all.length) {
                break;
            }
            if (!used[i]) {
                all[at++] = a[i];
            }
        }
        out.printLine(sumArray(all));
    }
}
