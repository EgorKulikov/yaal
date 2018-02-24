package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        long[] l = in.readLongArray(n);
        int[] current = new int[n];
        for (int i = 0; i < q; i++) {
            int f = in.readInt() - 1;
            int t = in.readInt() - 1;
            boolean found = false;
            for (int j = f; j <= t; j++) {
                current[j - f] = j;
                int at = j - f;
                while (at > 0) {
                    if (l[j] >= l[current[at - 1]]) {
                        break;
                    }
                    current[at] = current[at - 1];
                    current[at - 1] = j;
                    at--;
                }
                if (at > 1 && l[j] < l[current[at - 1]] + l[current[at - 2]]) {
                    out.printLine(j + 1, current[at - 1] + 1, current[at - 2] + 1);
                    found = true;
                    break;
                }
                if (at > 0 && at < j - f && l[current[at + 1]] < l[j] + l[current[at - 1]]) {
                    out.printLine(j + 1, current[at - 1] + 1, current[at + 1] + 1);
                    found = true;
                    break;
                }
                if (at + 1 < j - f && l[current[at + 2]] < l[j] + l[current[at + 1]]) {
                    out.printLine(j + 1, current[at + 2] + 1, current[at + 1] + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                out.printLine(-1);
            }
        }
    }
}
